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
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventPredicateListBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    public static final String UNMATCHING_START_DATE = "19-02-2059";
    public static final String MATCHING_START_DATE = "11-5-2022";
    public static final String UNMATCHING_END_DATE = "11-5-1959";
    public static final String MATCHING_END_DATE = "11-5-2022";
    public static final String[] UNMATCHING_DATE_RANGE = {"19-2-2045", "12-5-2045"};
    public static final String[] MATCHING_DATE_RANGE = {"12-3-2022", "11-5-2022"};

    public static final String UNMATCHING_EVENT_NAME = "aksdjaskdj";
    public static final String MATCHING_EVENT_NAME_SUBSTRING = "d Birthday";
    public static final String MATCHING_EVENT_NAME_ENTIRE = "2nd Birthday";
    public static final String UNMATCHING_FRIEND_NAME_1 = "Roger";
    public static final String MATCHING_FRIEND_NAME_1 = "Amy K";
    public static final String MATCHING_FRIEND_NAME_2 = "Alex Yeoh";

    private Model model = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Event>> firstPredicate = new EventPredicateListBuilder().build();
        List<Predicate<Event>> secondPredicate = new EventPredicateListBuilder().clearNameSubstring().build();

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

        // different predicates -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    public void assertFindEventCommandSuccess(String expectedMessage, List<Predicate<Event>> predicates,
                                              List<Event> expectedFilteredList) {
        FindEventCommand command = new FindEventCommand(predicates);
        expectedModel.updateFilteredEventList(event -> {
            for (Predicate<Event> predicate : predicates) {
                if (!predicate.test(event)) {
                    return false;
                }
            }
            return true;
        });
        Collections.sort(expectedFilteredList);
        assertEventCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedFilteredList, model.getFilteredEventList());
    }

    @Test
    public void execute_filterByEventName_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().withNameSubstring(UNMATCHING_EVENT_NAME)
                .clearStartDate().clearEndDate().clearFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterByEventName_multipleEventsFound() {
        // Match partial event name
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING)
                .clearStartDate().clearEndDate().clearFriendNameSubstrings().build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_WITH_DESCRIPTION, EVENT_NO_FRIENDS);
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);

        // Different case -> same outcome
        predicates = new EventPredicateListBuilder().withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING.toUpperCase())
                .clearStartDate().clearEndDate().clearFriendNameSubstrings().build();
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);

        // Match entire event name
        expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        predicates = new EventPredicateListBuilder().withNameSubstring(MATCHING_EVENT_NAME_ENTIRE)
                .clearStartDate().clearEndDate().clearFriendNameSubstrings().build();
        expectedFilteredList = Arrays.asList(EVENT_WITH_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterByOnlyStartDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .withStartDate(UNMATCHING_START_DATE).clearEndDate().clearFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterByOnlyStartDate_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .withStartDate(MATCHING_START_DATE).clearEndDate().clearFriendNameSubstrings().build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION, EVENT_NO_FRIENDS);
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterByOnlyEndDate_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().withEndDate(UNMATCHING_END_DATE).clearFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterByOnlyEndDate_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().withEndDate(MATCHING_END_DATE).clearFriendNameSubstrings().build();
        List<Event> expectedFilteredList = Arrays.asList(EVENT_NO_FRIENDS, EVENT_WITH_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterByDateRange_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .withStartDate(UNMATCHING_DATE_RANGE[0]).withEndDate(UNMATCHING_DATE_RANGE[1])
                .clearFriendNameSubstrings().build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterByDateRange_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .withStartDate(MATCHING_DATE_RANGE[0]).withEndDate(MATCHING_DATE_RANGE[1])
                .clearFriendNameSubstrings().build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_FRIENDS, EVENT_WITH_DESCRIPTION);
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterBySingleFriendName_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().clearEndDate().withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1).build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterBySingleFriendName_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().clearEndDate().withFriendNameSubstrings(MATCHING_FRIEND_NAME_1).build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterByMultipleFriendNames_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().clearEndDate()
                .withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_1).build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }

    @Test
    public void execute_filterByMultipleFriendNames_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().clearEndDate()
                .withFriendNameSubstrings(MATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2).build();
        List<Event> expectedFilteredList =
                Arrays.asList(EVENT_NO_DESCRIPTION, EVENT_WITH_DESCRIPTION, EVENT_WITH_DIFF_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);

        // Diff case -> same result
        String nameDiffCase1 = MATCHING_FRIEND_NAME_1.toLowerCase();
        String nameDiffCase2 = MATCHING_FRIEND_NAME_2.toUpperCase();
        predicates = new EventPredicateListBuilder().clearNameSubstring()
                .clearStartDate().clearEndDate().withFriendNameSubstrings(nameDiffCase1, nameDiffCase2).build();
        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }

    @Test
    public void execute_filterByAllThree_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder()
                .withNameSubstring(UNMATCHING_EVENT_NAME)
                .withStartDate(UNMATCHING_END_DATE).withEndDate(MATCHING_END_DATE)
                .withFriendNameSubstrings(UNMATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2)
                .build();

        assertFindEventCommandSuccess(expectedMessage, predicates, Collections.emptyList());
    }


    @Test
    public void execute_filterByAllThree_eventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        List<Predicate<Event>> predicates = new EventPredicateListBuilder()
                .withNameSubstring(MATCHING_EVENT_NAME_SUBSTRING)
                .withStartDate("11-3-2022")
                .withEndDate("12-3-2022")
                .withFriendNameSubstrings(MATCHING_FRIEND_NAME_1, MATCHING_FRIEND_NAME_2)
                .build();
        List<Event> expectedFilteredList = Arrays.asList(EVENT_WITH_DESCRIPTION);

        assertFindEventCommandSuccess(expectedMessage, predicates, expectedFilteredList);
    }
}
