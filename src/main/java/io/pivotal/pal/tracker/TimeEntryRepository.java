package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);

    TimeEntry find(long timeEntryId);

    List<TimeEntry> list();

    TimeEntry update(long eq, TimeEntry timeEntry);

    void delete(long timeEntryId);
}
