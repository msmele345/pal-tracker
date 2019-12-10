package io.pivotal.pal.tracker;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Component
public class TimeEntryController {

    TimeEntryRepository timeRepo;


    public TimeEntryController(TimeEntryRepository timeRepo) {
        this.timeRepo = timeRepo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newEntry = timeRepo.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entry = timeRepo.find(timeEntryId);

        if(entry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeRepo.list();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeRepo.update(timeEntryId, timeEntry);

        if(updatedTimeEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            return ResponseEntity.status(HttpStatus.OK).body(updatedTimeEntry);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeRepo.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
