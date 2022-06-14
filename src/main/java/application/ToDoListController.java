package application;


import application.entities.EventEntity;
import application.repositories.EventRepository;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;


@RestController
public class ToDoListController {
    @Autowired
    EventRepository todoRepo;

    @GetMapping("/get/{id}")
    public Optional<EventEntity> getById(@PathVariable(value = "id") String id) {
        return todoRepo.findById(Integer.parseInt(id));
    }

    @GetMapping("getAll/")
    public List<EventEntity> getAll() {
        return todoRepo.findAll();
    }

    @PostMapping("/createEvent/")
    void createEvent(@RequestBody String jsonString) {
        EventEntity event = new EventEntity(jsonString);
        todoRepo.save(event);
        return;
    }

    @DeleteMapping("deleteEvent/{id}")
    public void deleteEvent(@PathVariable(value = "id") String id) {
        todoRepo.deleteById(Integer.parseInt(id));
    }

    @DeleteMapping("/clearToDo")
    void deleteAllEvents() {
        todoRepo.deleteAll();
        return;
    }
}
