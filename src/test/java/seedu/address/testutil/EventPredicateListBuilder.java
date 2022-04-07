package seedu.address.testutil;

import static seedu.address.logic.parser.ParserUtil.INPUT_DATE_FORMATTER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventDateIsAfterPredicate;
import seedu.address.model.event.EventDateIsBeforePredicate;
import seedu.address.model.event.EventFriendNamesContainSubstringPredicate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNameContainsSubstringPredicate;
import seedu.address.model.person.FriendName;

/**
 * A utility class to help with building a list of event predicates.
 */
public class EventPredicateListBuilder {

    public static final String DEFAULT_NAME_SUBSTRING = "Default Event";

    public static final String DEFAULT_DATE_START_STRING = "11-5-2022";
    public static final String DEFAULT_DATE_END_STRING = "12-5-2022";
    public static final LocalDate DEFAULT_DATE_START = LocalDate.parse(DEFAULT_DATE_START_STRING, INPUT_DATE_FORMATTER);
    public static final LocalDate DEFAULT_DATE_END = LocalDate.parse(DEFAULT_DATE_END_STRING, INPUT_DATE_FORMATTER);

    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_1 = "Amy";
    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_2 = "Alex";

    private String nameSubstring;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> friendNameSubstrings;

    /**
     * Creates a {@code EventPredicateListBuilder} with the default details.
     */
    public EventPredicateListBuilder() {
        nameSubstring = DEFAULT_NAME_SUBSTRING;
        startDate = DEFAULT_DATE_START;
        endDate = DEFAULT_DATE_END;
        friendNameSubstrings = List.of(DEFAULT_FRIEND_NAME_SUBSTRING_1, DEFAULT_FRIEND_NAME_SUBSTRING_2);
    }

    /**
     * Sets the {@code nameSubstring} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder withNameSubstring(String nameSubstring) {
        this.nameSubstring = nameSubstring;
        return this;
    }

    /**
     * Clears the {@code nameSubstring} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder clearNameSubstring() {
        this.nameSubstring = null;
        return this;
    }

    /**
     * Parses the {@code friendNameSubstrings} into a {@code List} and sets it to the
     * {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder withFriendNameSubstrings(String ... friendNameSubstrings) {
        this.friendNameSubstrings = List.of(friendNameSubstrings);
        return this;
    }

    /**
     * Clears the {@code friendNameSubstrings} of the {@code EventPredicateList} we are building.
     */
    public EventPredicateListBuilder clearFriendNameSubstrings() {
        this.friendNameSubstrings = List.of();
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder withStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, INPUT_DATE_FORMATTER);
        return this;
    }

    /**
     * Clears the {@code startDate} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder clearStartDate() {
        this.startDate = null;
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder withEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, INPUT_DATE_FORMATTER);
        return this;
    }

    /**
     * Clears the {@code endDate} of the {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder clearEndDate() {
        this.endDate = null;
        return this;
    }

    /**
     * Builds and returns the List of Event Predicates with the data in the builder.
     */
    public List<Predicate<Event>> build() {
        ArrayList<Predicate<Event>> predicates = new ArrayList<>();
        if (nameSubstring != null) {
            predicates.add(new EventNameContainsSubstringPredicate(new EventName(nameSubstring)));
        }
        if (startDate != null) {
            predicates.add(new EventDateIsAfterPredicate(startDate.minusDays(1)));

        }
        if (endDate != null) {
            predicates.add(new EventDateIsBeforePredicate(endDate.plusDays(1)));
        }
        for (String friendNameSubstring : friendNameSubstrings) {
            predicates.add(new EventFriendNamesContainSubstringPredicate(new FriendName(friendNameSubstring)));
        }
        return Collections.unmodifiableList(predicates);
    }
}
