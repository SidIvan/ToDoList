package application;

import application.entities.BirthdayEntity;
import application.entities.EventEntity;
import application.urlsend.BirthdayURLSender;
import application.urlsend.EventURLSender;
import application.utils.JsonUtil;
import application.utils.Utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

@Deprecated
public class QuerySender {
    private final static JsonUtil jsonUtil = new JsonUtil();
    private final static QueryReader queryReader = new QueryReader();
    private final static EventURLSender eventUrlSender = new EventURLSender();
    private final static BirthdayURLSender birthdayUrlSender = new BirthdayURLSender();

    void sendCreateQuery(HashMap<String, String> eventInfo) {
        String type = eventInfo.remove("repeatType");
        eventInfo.put("prevId", "-1");
        eventInfo.put("nextId", "-1");
        if (type.equals("nonRepeatable")) {
            String jsonString = JsonUtil.buildEventJson(eventInfo);
            int id = eventUrlSender.sendPost(jsonString);
            eventInfo.put("id", String.valueOf(id));
        }

        else if (type.equals("intervaly")) {
            int period = parseInt(eventInfo.remove("every")),
                    prevEventId = -1,
                    curEventId = -1;
            eventInfo.put("nextId", "-1");
            Date curDate = Date.valueOf(eventInfo.get("startDate"));
            Date endDate = Date.valueOf(eventInfo.get("endDate"));
            if (!eventInfo.get("times").contains("-")) {
                int times = parseInt(eventInfo.remove("times"));
                for (int i = 0; i < times; ++i) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = eventUrlSender.sendPost(jsonString);
                    if (prevEventId != -1) {
                        eventUrlSender.sendPutNextId(prevEventId, curEventId);
                    }
                    curDate.setTime(curDate.getTime() + Utils.getMsInDay() * period);
                    endDate.setTime(endDate.getTime() + Utils.getMsInDay() * period);
                    eventInfo.put("startDate", curDate.toString());
                    eventInfo.put("endDate", endDate.toString());
                    prevEventId = curEventId;
                }
            } else {
                Date borderDate = Date.valueOf(eventInfo.get("times"));
                while (curDate.compareTo(borderDate) < 1) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = eventUrlSender.sendPost(jsonString);
                    if (prevEventId != -1) {
                        eventUrlSender.sendPutNextId(prevEventId, curEventId);
                    }
                    curDate.setTime(curDate.getTime() + Utils.getMsInDay() * period);
                    endDate.setTime(endDate.getTime() + Utils.getMsInDay() * period);
                    eventInfo.put("startDate", curDate.toString());
                    eventInfo.put("endDate", endDate.toString());
                    prevEventId = curEventId;
                }
            }
        }

        else {
            int prevEventId = -1,
                    curEventId = -1;
            boolean[] isNeedToDoThisDay = {false, false, false, false, false, false, false};
            for (int i : stream(eventInfo.remove("every").split(" ")).mapToInt(Integer::parseInt).toArray()) {
                isNeedToDoThisDay[i - 1] = true;
            }
            int curDayOfWeek = (int) (Date.valueOf(eventInfo.get("startDate")).getTime() %
                    (7 * Utils.getMsInDay()) / Utils.getMsInDay() + 4) % 7;
            Date curDate = Date.valueOf(eventInfo.get("startDate"));
            Date endDate = Date.valueOf(eventInfo.get("endDate"));
            Date borderDate = Date.valueOf(eventInfo.remove("times"));
            while (curDate.compareTo(borderDate) < 1) {
                if (isNeedToDoThisDay[curDayOfWeek]) {
                    eventInfo.put("prevId", String.valueOf(prevEventId));
                    String jsonString = JsonUtil.buildEventJson(eventInfo);
                    curEventId = eventUrlSender.sendPost(jsonString);
                    if (prevEventId != -1) {
                        eventUrlSender.sendPutNextId(prevEventId, curEventId);
                    }
                }
                curDate.setTime(curDate.getTime() + Utils.getMsInDay());
                endDate.setTime(endDate.getTime() + Utils.getMsInDay());
                ++curDayOfWeek;
                curDayOfWeek %= 7;
                eventInfo.put("startDate", curDate.toString());
                eventInfo.put("endDate", endDate.toString());
                prevEventId = curEventId;
            }
        }
    }

    List<BirthdayEntity> sendGetBirthdaysQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.get("type").equals("single") && eventInfo.containsKey("id")) {
            return birthdayUrlSender.sendGetById(eventInfo.get("id"));
        } else if (eventInfo.containsKey("newborn name") && eventInfo.get("type").equals("allSame")) {
            return birthdayUrlSender.sendGetByName(eventInfo.get("newborn name"));
        }
        return new ArrayList<>();
    }
    List<EventEntity> sendGetEventsQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.containsKey("id")) {
            return eventUrlSender.sendGetById(parseInt(eventInfo.get("id")));
        } else if (eventInfo.containsKey("title")) {
            return eventUrlSender.sendGetByTitle(eventInfo.get("title"));
        } else if (eventInfo.containsKey("date")) {
            return eventUrlSender.sendGetByDate(eventInfo.get("date"));
        }
        return new ArrayList<EventEntity>();
    }

    /*
     DELETE section
     */
    void sendDeleteQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.get("type").equals("all")) {
            eventUrlSender.sendDeleteAll();
        } else if (eventInfo.containsKey("title")) {
            eventUrlSender.sendDeleteByTitle(eventInfo.get("title"));
        } else if (eventInfo.containsKey("id")) {
            EventEntity event = eventUrlSender.sendGetById(parseInt(eventInfo.get("id"))).get(0);
            if (event.getNextId() != -1) {
                eventUrlSender.sendPutPrevId(event.getNextId(), event.getPrevId());
            }
            if (event.getPrevId() != -1) {
                eventUrlSender.sendPutNextId(event.getPrevId(), event.getNextId());
            }
            eventUrlSender.sendDeleteById(parseInt(eventInfo.get("id")));
            if (eventInfo.get("type").equals("row")) {
                EventEntity eventCopy = event.clone();
                while (eventCopy.getPrevId() != -1) {
                    eventCopy = eventUrlSender.sendGetById(eventCopy.getPrevId()).get(0);
                    eventUrlSender.sendDeleteById(eventCopy.getId());
                }
                while (event.getNextId() != -1) {
                    event = eventUrlSender.sendGetById(event.getNextId()).get(0);
                    eventUrlSender.sendDeleteById(event.getId());
                }
            }
        }
    }

    /*
     Put section
     */
    void changeEvent(int id, HashMap<String, String> eventInfo) {
        for (String key : eventInfo.keySet()) {
            if (key.equals("title")) {
                eventUrlSender.sendPutTitle(id, eventInfo.get("title"));
            } else if (key.equals("text")) {
                eventUrlSender.sendPutText(id, eventInfo.get("text"));
            } else if (key.equals("place")) {
                eventUrlSender.sendPutPlace(id, eventInfo.get("place"));
            } else if (key.equals("startDate")) {
                eventUrlSender.sendPutStartDate(id, eventInfo.get("startDate"));
            } else if (key.equals("endDate")) {
                eventUrlSender.sendPutEndDate(id, eventInfo.get("endDate"));
            } else if (key.equals("startTime")) {
                eventUrlSender.sendPutStartTime(id, eventInfo.get("startTime"));
            } else if (key.equals("endTime")) {
                eventUrlSender.sendPutEndTime(id, eventInfo.get("endTime"));
            } else if (key.equals("newborn name")) {
                birthdayUrlSender.sendPutName(id, eventInfo.get("newborn name"));
            }
        }
    }

    private void changeBirthday(int id, HashMap<String, String> eventInfo) {
        for (String key : eventInfo.keySet()) {
            if (key.equals("newborn name")) {
                birthdayUrlSender.sendPutName(id, eventInfo.get(key));
            }
        }
    }
    void sendPutQuery(HashMap<String, String> eventInfo) {
        if (eventInfo.containsKey("newborn name")) {
            List<BirthdayEntity> birthdayEntities = birthdayUrlSender.sendGetByName(eventInfo.get("old name"));
            for (BirthdayEntity birthdayEntity : birthdayEntities) {
                int id = birthdayEntity.getId();
                changeBirthday(id, eventInfo);
            }
            return;
        }
        String type = eventInfo.get("type");
        eventInfo.remove("type");
        EventEntity event = new EventEntity();
        int id = parseInt(eventInfo.get("id"));
        if (type.equals("row")) {
            event = eventUrlSender.sendGetById(id).get(0);
        }

        changeEvent(id, eventInfo);
        if (type.equals("row")) {
            EventEntity eventCopy = event.clone();
            while (eventCopy.getPrevId() != -1) {
                eventCopy = eventUrlSender.sendGetById(eventCopy.getPrevId()).get(0);
                changeEvent(eventCopy.getId(), eventInfo);
            }
            while (event.getNextId() != -1) {
                event = eventUrlSender.sendGetById(event.getNextId()).get(0);
                changeEvent(event.getId(), eventInfo);
            }
        }

    }
}
