package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/time-entries")
public class TimeEntryController {
    @Autowired
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry newTimeEntry = timeEntryRepository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTimeEntry);
        //return new ResponseEntity<TimeEntry>().getBody(newTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry newTimeEntry = timeEntryRepository.find(id);
        if(newTimeEntry != null) {
            return new ResponseEntity<TimeEntry>(newTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>>  list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>> (timeEntryList, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry newTimeEntry = timeEntryRepository.update(id, timeEntry);
        if(newTimeEntry != null) {
            return new ResponseEntity<TimeEntry>(newTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
