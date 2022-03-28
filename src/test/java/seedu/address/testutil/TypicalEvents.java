package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class TypicalEvents {

    public static final String AMY_NAME = "Amy Koh";
    public static final String ALEX_NAME = "Alex Yeoh";

    // TODO alter to reflect new restrictions after refactoring of name and description classes
    public static final Event EVENT_AMY_BIRTHDAY = new EventBuilder().withName("Amy Birthday")
            .withDateTime(VALID_EVENT_DATETIME_AMY)
            .withNames(AMY_NAME)
            .withDescription("This is Amy's Birthday")
            .build();

    public static final Event EVENT_NO_DESCRIPTION = new EventBuilder().withName("Birthday")
            .withDateTime("12-5-2022 1520")
            .withNames(AMY_NAME, ALEX_NAME).build();

    public static final Event EVENT_WITH_DESCRIPTION = new EventBuilder().withName("2nd Birthday")
            .withDateTime("12-3-2022 1520")
            .withDescription("This is a description")
            .withNames(AMY_NAME, ALEX_NAME).build();

    public static final Event EVENT_NO_FRIENDS = new EventBuilder().withName("3rd Birthday")
            .withDateTime("11-5-2022 1520")
            .withDescription("This is a description").withNames().build();

    public static final Event EVENT_WITH_DIFF_DESCRIPTION = new EventBuilder().withName("4th Birthday")
            .withDateTime("12-5-2022 2020")
            .withDescription("This is a another description")
            .withNames(AMY_NAME, ALEX_NAME).build();

    public static final Person AMY = new PersonBuilder().withName(AMY_NAME).build();
    public static final Person ALEX = new PersonBuilder().withName(ALEX_NAME).build();

    private TypicalEvents() {}

    public static AddressBook getTypicalAddressBookWithEvents() {
        AddressBook ab = new AddressBook();
        for (Event event: getTypicalEvents()) {
            ab.addEvent(event);
        }
        // So that friend name check does not fail
        ab.addPerson(AMY);
        ab.addPerson(ALEX);
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DESCRIPTION, EVENT_NO_FRIENDS,
                EVENT_WITH_DIFF_DESCRIPTION));
    }

}
