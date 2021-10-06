package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

/**
 * An utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event SAMPLE_EVENT = new Event(new EventName("Sleep"), new EventDate("2021-09-18"),
            new EventTime("1000"));
    public static final Event ANOTHER_EVENT = new Event(new EventName("Sleep2"), new EventDate("2021-09-18"),
            new EventTime("1001"));
    public static final Event SAMPLE_EVENT_COPY_DIFFERENT_TIME = new Event(new EventName("Sleep"),
            new EventDate("2021-09-18"), new EventTime("1001"));
    public static final Event SAMPLE_EVENT_2 = new Event(new EventName("Random Event 1"),
            new EventDate("1-1-1"), new EventTime("0001"));

    private TypicalEvents() {}

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(SAMPLE_EVENT, ANOTHER_EVENT, SAMPLE_EVENT_2));
    }
}