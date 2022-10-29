package application;


import application.entities.BirthdayEntity;
import application.entities.EventEntity;
import application.repositories.BirthdayRepository;
import application.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
public class ToDoListController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BirthdayRepository birthdayRepository;

    /*
     Get section
     */

    @GetMapping("/getById/{id}/")
    public List<EventEntity> getById(@PathVariable(value = "id") String id) {
        return eventRepository.findById(parseInt(id));
    }

    @GetMapping("/getByTitle/{title}/")
    public List<EventEntity> getByTitle(@PathVariable(value = "title") String title) {
        title = title.replaceAll("_", " ");
        List<EventEntity> events = eventRepository.findByTitle(title);
        return events;
    }

    @GetMapping("/getByDate/{date}/")
    public List<EventEntity> getByDate(@PathVariable(value = "date") String date) {
        List<EventEntity> events = eventRepository.findByDate(Date.valueOf(date.replace('&', '-')));
        return events;
    }

    @GetMapping("/getByName/{name}")
    public List<BirthdayEntity> getByName(@PathVariable(value = "name") String name) {
        List<BirthdayEntity> birthdays = birthdayRepository.findByName(name);
        return birthdays;
    }

    @GetMapping("/getBirthdayById/{id}")
    public List<BirthdayEntity> getBirthdayById(@PathVariable(value = "id") String id) {
        List<BirthdayEntity> birthdays = birthdayRepository.findById(parseInt(id));
        return birthdays;
    }

    @GetMapping("getAll/")
    public List<EventEntity> getAll() {
        return eventRepository.findAll();
    }


    /*
     * POST section
     */
    @PostMapping("/createEvent/")
    int createEvent(@RequestBody String jsonString) {
        EventEntity event = eventRepository.saveAndFlush(new EventEntity(jsonString));
        BirthdayEntity birthdayEntity = new BirthdayEntity(jsonString);
        birthdayEntity.setId(event.getId());
        birthdayRepository.saveAndFlush(birthdayEntity);
        return event.getId();
    }

    /*
     * Delete section
     */
    @DeleteMapping("/deleteById/{id}/")
    public void deleteById(@PathVariable(value = "id") String id) {
        eventRepository.deleteById(parseInt(id));
        birthdayRepository.deleteById(parseInt(id));
    }

    @DeleteMapping("/deleteByTitle/{title}/")
    public void deleteByTitle(@PathVariable(value = "title") String title) {
        title = title.replaceAll("_", " ");
        List<EventEntity> deletingQueue = eventRepository.findByTitle(title);
        for (EventEntity event : deletingQueue) {
            birthdayRepository.deleteById(event.getId());
        }
        eventRepository.deleteByTitle(title);
    }
    @DeleteMapping("/deleteAll/")
    void deleteAllEvents() {
        birthdayRepository.deleteAll();
        eventRepository.deleteAll();
    }



    @PutMapping("/updateNextId/{id}/{nextId}/")
    void updateNextId(@PathVariable(value = "id") String id, @PathVariable(value = "nextId") String nextId) {
        eventRepository.updateNextId(parseInt(id), parseInt(nextId));
    }

    @PutMapping("/updatePrevId/{id}/{prevId}/")
    void updatePrevId(@PathVariable(value = "id") String id, @PathVariable(value = "prevId") String prevId) {
        eventRepository.updatePrevId(parseInt(id), parseInt(prevId));
    }

    @PutMapping("/updateTitle/{id}/")
    void updateTitle(@PathVariable(value = "id") String id, @RequestBody String title) {
        eventRepository.updateTitle(parseInt(id), title);
    }

    @PutMapping("/updateText/{id}/")
    void updateText(@PathVariable(value = "id") String id, @RequestBody String text) {
        eventRepository.updateText(parseInt(id), text);
    }

    @PutMapping("/updatePlace/{id}/")
    void updatePlace(@PathVariable(value = "id") String id, @RequestBody String place) {
        eventRepository.updatePlace(parseInt(id), place);
    }

    @PutMapping("/updateStartDate/{id}/")
    void updateStartDate(@PathVariable(value = "id") String id, @RequestBody String startDate) {
        eventRepository.updateStartDate(parseInt(id), Date.valueOf(startDate));
    }

    @PutMapping("/updateEndDate/{id}/")
    void updateEndDate(@PathVariable(value = "id") String id, @RequestBody String endDate) {
        eventRepository.updateEndDate(parseInt(id), Date.valueOf(endDate));
    }

    @PutMapping("/updateStartTime/{id}/")
    void updateStartTime(@PathVariable(value = "id") String id, @RequestBody String startTime) {
        eventRepository.updateStartTime(parseInt(id), Time.valueOf(startTime));
    }

    @PutMapping("/updateEndTime/{id}/")
    void updateEndTime(@PathVariable(value = "id") String id, @RequestBody String endTime) {
        eventRepository.updateEndTime(parseInt(id), Time.valueOf(endTime));
    }

    @PutMapping("/putName/{id}")
    void putName(@PathVariable(value = "id") int id, @RequestBody String name) {
        birthdayRepository.putName(id, name);
    }
}
