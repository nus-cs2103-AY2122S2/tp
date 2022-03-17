package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

public class TypicalEvents {

    public static final Event EVENT_NO_DESCRIPTION = new EventBuilder().withName("Birthday")
            .withDateTime("12-5-2022 1520")
            .withNames("Amy Koh", "Alex Yeoh").build();

    public static final Event EVENT_WITH_DESCRIPTION = new EventBuilder().withName("2nd Birthday")
            .withDateTime("12-3-2022 1520")
            .withDescription("This is a description")
            .withNames("Amy Koh", "Alex Yeoh").build();

    public static final Event EVENT_NO_FRIENDS = new EventBuilder().withName("3rd Birthday")
            .withDateTime("11-5-2022 1520")
            .withDescription("This is a description").build();

    public static final Event EVENT_WITH_DIFF_DESCRIPTION = new EventBuilder().withName("4th Birthday")
            .withDateTime("17-5-2022 2020")
            .withDescription("This is a another description")
            .withNames("Amy Koh", "Alex Yeoh").build();

    private TypicalEvents() {}

    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (Event event: getTypicalEvents()) {
            eb.addEvent(event);
        }
        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_NO_FRIENDS, EVENT_WITH_DESCRIPTION,
                EVENT_WITH_DIFF_DESCRIPTION));
    }

}
