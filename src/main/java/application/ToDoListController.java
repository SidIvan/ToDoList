package application;


import application.entities.EventEntity;
import application.repositories.EventRepository;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;


@RestController
public class ToDoListController {
    @Autowired
    private EventRepository todoRepo;

    @GetMapping("/getById/{id}/")
    public Optional<EventEntity> getById(@PathVariable(value = "id") String id) {
        return todoRepo.findById(parseInt(id));
    }

    @GetMapping("/getByTitle/{title}/")
    public List<EventEntity> getByTitle(@PathVariable(value = "title") String title) {
        title = title.replaceAll("_", " ");
        List<EventEntity> events = todoRepo.findByTitle(title);
        return events;
    }

    @GetMapping("/getByDate/{date}/")
    public List<EventEntity> getByDate(@PathVariable(value = "date") String date) {
        List<EventEntity> events = todoRepo.findByDate(Date.valueOf(date.replace('&', '-')));
        return events;
    }

    @GetMapping("getAll/")
    public List<EventEntity> getAll() {
        return todoRepo.findAll();
    }

    @PostMapping("/createEvent/")
    int createEvent(@RequestBody String jsonString) {
        EventEntity event = todoRepo.saveAndFlush(new EventEntity(jsonString));
        return event.getId();
    }

    @DeleteMapping("/deleteById/{id}/")
    public void deleteById(@PathVariable(value = "id") String id) {
        todoRepo.deleteById(parseInt(id));
    }

    @DeleteMapping("/deleteByTitle/{title}/")
    public void deleteByTitle(@PathVariable(value = "title") String title) {
        title = title.replaceAll("_", " ");
        todoRepo.deleteByTitle(title);
    }
    @DeleteMapping("/deleteAll/")
    void deleteAllEvents() {
        todoRepo.deleteAll();
    }

    @PutMapping("/updateNextId/{id}/{nextId}/")
    void updateNextId(@PathVariable(value = "id") String id, @PathVariable(value = "nextId") String nextId) {
        todoRepo.updateNextId(parseInt(id), parseInt(nextId));
    }

    @PutMapping("/updatePrevId/{id}/{prevId}/")
    void updatePrevId(@PathVariable(value = "id") String id, @PathVariable(value = "prevId") String prevId) {
        todoRepo.updatePrevId(parseInt(id), parseInt(prevId));
    }

    @PutMapping("/updateTitle/{id}/")
    void updateTitle(@PathVariable(value = "id") String id, @RequestBody String title) {
        todoRepo.updateTitle(parseInt(id), title);
    }

    @PutMapping("/updateText/{id}/")
    void updateText(@PathVariable(value = "id") String id, @RequestBody String text) {
        todoRepo.updateText(parseInt(id), text);
    }

    @PutMapping("/updatePlace/{id}/")
    void updatePlace(@PathVariable(value = "id") String id, @RequestBody String place) {
        todoRepo.updatePlace(parseInt(id), place);
    }

    @PutMapping("/updateStartDate/{id}/")
    void updateStartDate(@PathVariable(value = "id") String id, @RequestBody String startDate) {
        todoRepo.updateStartDate(parseInt(id), Date.valueOf(startDate));
    }

    @PutMapping("/updateEndDate/{id}/")
    void updateEndDate(@PathVariable(value = "id") String id, @RequestBody String endDate) {
        todoRepo.updateEndDate(parseInt(id), Date.valueOf(endDate));
    }

    @PutMapping("/updateStartTime/{id}/")
    void updateStartTime(@PathVariable(value = "id") String id, @RequestBody String startTime) {
        todoRepo.updateStartTime(parseInt(id), Time.valueOf(startTime));
    }

    @PutMapping("/updateEndTime/{id}/")
    void updateEndTime(@PathVariable(value = "id") String id, @RequestBody String endTime) {
        todoRepo.updateEndTime(parseInt(id), Time.valueOf(endTime));
    }
}
