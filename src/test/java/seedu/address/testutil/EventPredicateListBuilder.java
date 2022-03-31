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
import seedu.address.model.event.EventNameContainsSubstringPredicate;

/**
 * A utility class to help with building a List<Predicate<Event>>.
 */
public class EventPredicateListBuilder {

    public static final String DEFAULT_NAME_SUBSTRING = "Default Event";
    public static final LocalDate DEFAULT_DATE_START = LocalDate.parse("11-5-2022", INPUT_DATE_FORMATTER);
    public static final LocalDate DEFAULT_DATE_END = LocalDate.parse("12-5-2022", INPUT_DATE_FORMATTER);
    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_1 = "Amy";
    public static final String DEFAULT_FRIEND_NAME_SUBSTRING_2 = "Alex";

    private String nameSubstring;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> friendNameSubstrings;

    /**
     * Creates a {@code EventPredicateListBuilder} with the default details, including a valid date.
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

    public EventPredicateListBuilder clearNameSubstring() {
        this.nameSubstring = null;
        return this;
    }

    /**
     * Parses the {@code friendNameSubstrings} into a {@code List<String>} and set it to the
     * {@code EventPredicateList} that we are building.
     */
    public EventPredicateListBuilder withFriendNameSubstrings(String ... friendNameSubstrings) {
        this.friendNameSubstrings = List.of(friendNameSubstrings);
        return this;
    }

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
            predicates.add(new EventNameContainsSubstringPredicate(nameSubstring));
        }
        if (startDate != null) {
            predicates.add(new EventDateIsBeforePredicate(startDate));

        }
        if (endDate != null) {
            predicates.add(new EventDateIsAfterPredicate(endDate));
        }
        for (String friendNameSubstring : friendNameSubstrings) {
            predicates.add(new EventFriendNamesContainSubstringPredicate(friendNameSubstring));
        }
        return Collections.unmodifiableList(predicates);
    }
}
