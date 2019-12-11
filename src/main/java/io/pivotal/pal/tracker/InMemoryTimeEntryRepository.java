package io.pivotal.pal.tracker;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ConditionalOnBean(JdbcTimeEntryRepository.class)
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> entries = new HashMap<>();

    long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        long id = currentId++;

        TimeEntry newEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        entries.put(id, newEntry);

        return newEntry;
    }

    @Override
    public TimeEntry find(long id) {

        TimeEntry returnedEntry = entries.get(id);
        return returnedEntry;
    }

    @Override
    public ArrayList<TimeEntry> list() {
        ArrayList<TimeEntry> list = new ArrayList<>();
        for (Map.Entry<Long, TimeEntry> entry : entries.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
    @Override
    public void delete(long id) {
        TimeEntry timeEntry = find(id);
        entries.remove(id, timeEntry);
    }

    @Override
    public TimeEntry update(long id, TimeEntry newEntry) {
        TimeEntry timeEntry = find(id);
        if (timeEntry == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                newEntry.getProjectId(),
                newEntry.getUserId(),
                newEntry.getDate(),
                newEntry.getHours()
        );

        entries.replace(id, updatedEntry);
        return updatedEntry;
    }


}
