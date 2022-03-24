package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertEventCommandSuccess;
import static seedu.address.testutil.TypicalEvents.EVENT_NO_DESCRIPTION;
import static seedu.address.testutil.TypicalEvents.EVENT_NO_FRIENDS;
import static seedu.address.testutil.TypicalEvents.EVENT_WITH_DESCRIPTION;
import static seedu.address.testutil.TypicalEvents.EVENT_WITH_DIFF_DESCRIPTION;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBookWithEvents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventFilterPredicate;
import seedu.address.testutil.EventFilterPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    public static final String UMATCHING_DATE = "19-02-1959";
    public static final String MATCHING_DATE = "12-5-2022";
    public static final String UNMATCHING_EVENT_NAME = "aksdjaskdj";
    public static final String MATCHING_EVENT_NAME_SUBSTRING = " Birthday";
    public static final String MATCHING_EVENT_NAME_ENTIRE = "2nd Birthday";
    public static final String UNMATCHING_FRIEND_NAME_1 = "Roger";
    public static final String UNMATCHING_FRIEND_NAME_2 = "Dilbert Goh";
    public static final String MATCHING_FRIEND_NAME_1 = "Amy K";
    public static final String MATCHING_FRIEND_NAME_2 = "Alex Yeoh";

    private Model model = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());

    @Test
    public void equals() {
        EventFilterPredicate firstPredicate = new EventFilterPredicate("first", List.of("first"));
        EventFilterPredicate secondPredicate = new EventFilterPredicate("second", List.of("second"));

        FindEventCommand findFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different predicate -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    public void assertFindEventCommandSuccess(String expectedMessage, EventFilterPredicate predicate,
                                              List<Event> expectedFilteredList) {
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        Collections.sort(expectedFilteredList);
        assertEventCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedFilteredList, model.getFilteredEventList());
    }

    @Test
    public void execute_filterByEventName_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring(UNMATCHING_EVENT_NAME)
                .clearDate().withFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicate, Collections.emptyList());
    }

    @Test
    public void execute_filterByEventName_multipleEventsFound() {
        // Match partial event name
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING)
                .clearDate().withFriendNameSubstrings().build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_WITH_DESCRIPTION, EVENT_NO_FRIENDS, EVENT_WITH_DIFF_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);

        // Different case -> same outcome
        predicate = new EventFilterPredicateBuilder().withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING.toUpperCase())
                .clearDate().withFriendNameSubstrings().build();
        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);

        // Match entire event name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        predicate = new EventFilterPredicateBuilder().withNameSubstring(MATCHING_EVENT_NAME_ENTIRE)
                .clearDate().withFriendNameSubstrings().build();
        expectedFilteredList = Arrays.asList(EVENT_WITH_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);
    }

    @Test
    public void execute_filterByDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .withDate(UMATCHING_DATE).withFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicate, Collections.emptyList());
    }

    @Test
    public void execute_filterByDate_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .withDate(MATCHING_DATE).withFriendNameSubstrings().build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);
    }

    @Test
    public void execute_filterBySingleFriendName_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .clearDate().withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1).build();

        assertFindEventCommandSuccess(expectedMessage, predicate, Collections.emptyList());
    }

    @Test
    public void execute_filterBySingleFriendName_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .clearDate().withFriendNameSubstrings(MATCHING_FRIEND_NAME_1).build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);
    }

    @Test
    public void execute_filterByMultipleFriendNames_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .clearDate().withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_1).build();

        assertFindEventCommandSuccess(expectedMessage, predicate, Collections.emptyList());
    }

    @Test
    public void execute_filterByMultipleFriendNames_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .clearDate().withFriendNameSubstrings(MATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2).build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);

        // Diff case -> same result
        String nameDiffCase1 = MATCHING_FRIEND_NAME_1.toLowerCase();
        String nameDiffCase2 = MATCHING_FRIEND_NAME_2.toUpperCase();
        predicate = new EventFilterPredicateBuilder().withNameSubstring("")
                .clearDate().withFriendNameSubstrings(nameDiffCase1, nameDiffCase2).build();
        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);

    }

    @Test
    public void execute_filterByAllThree_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder().withNameSubstring(UNMATCHING_EVENT_NAME)
                .withDate(MATCHING_DATE).withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2)
                .build();

        assertFindEventCommandSuccess(expectedMessage, predicate, Collections.emptyList());
    }


    @Test
    public void execute_filterByAllThree_eventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventFilterPredicate predicate = new EventFilterPredicateBuilder()
                .withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING)
                .withDate(MATCHING_DATE).withFriendNameSubstrings(MATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2)
                .build();
        List<Event> expectedFilteredList = Arrays.asList(EVENT_WITH_DIFF_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicate, expectedFilteredList);
    }
}
