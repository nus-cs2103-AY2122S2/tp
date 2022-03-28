package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.entry.Event;

/**
 * This class is an abstraction of combining {@code NameContainsKeywords}, {@code CompanyNameContainsKeywords},
 * {@code DateContainsKeywords}, {@code TimeContainsKeywords}, {@code LocationContainsKeywords}
 * , {@code TagContainsKeywords}.
 * In particular, it tests that a {@code Event}'s  attributes matches any of the keywords given by user.
 * Acts the main logic part for checking whether an Event should be displayed for finde command
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> nameKeywords;
    private final List<String> companyKeywords;
    private final List<String> dateKeywords;
    private final List<String> timeKeywords;
    private final List<String> locationKeywords;
    private final List<String> tagKeywords;

    /**
     * Main Constructor for EventContainsKeywordsPredicate
     * @param nameKeywords user input to search event name
     * @param companyKeywords user input to search event companyName
     * @param dateKeywords user input to search event date
     * @param timeKeywords user input to search event time
     * @param locationKeywords user input to search event location
     * @param tagKeywords user input to search event tag
     */
    public EventContainsKeywordsPredicate(List<String> nameKeywords,
                                          List<String> companyKeywords,
                                          List<String> dateKeywords,
                                          List<String> timeKeywords,
                                          List<String> locationKeywords,
                                          List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.companyKeywords = companyKeywords;
        this.dateKeywords = dateKeywords;
        this.timeKeywords = timeKeywords;
        this.locationKeywords = locationKeywords;
        this.tagKeywords = tagKeywords;
    }

    /**
     * It must be noted that the test function is implemented differently than the usual (ANY)ContainsKeywordsPredicate.
     * In particular, it checks all keywords available. If the keyword is not available (not given by user), then
     * it will automatically be removed from consideration when testing an event.
     */
    @Override
    public boolean test(Event event) {
        boolean correctName;
        boolean correctCompany;
        boolean correctDate;
        boolean correctTime;
        boolean correctLocation;
        boolean correctTag;

        correctName = invalidKeywords(nameKeywords)
                || new NameContainsKeywordsPredicate(nameKeywords).test(event);
        correctCompany = invalidKeywords(companyKeywords)
                || new CompanyNameContainsKeywordsPredicate(companyKeywords).test(event);
        correctDate = invalidKeywords(dateKeywords)
                || new DateContainsKeywordsPredicate(dateKeywords).test(event);
        correctTime = invalidKeywords(timeKeywords)
                || new TimeContainsKeywordsPredicate(timeKeywords).test(event);
        correctLocation = invalidKeywords(locationKeywords)
                || new LocationContainsKeywordsPredicate(locationKeywords).test(event);
        correctTag = invalidKeywords(tagKeywords) || new TagContainsKeywordsPredicate(tagKeywords).test(event);

        return correctName && correctCompany && correctDate && correctTime && correctLocation && correctTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((EventContainsKeywordsPredicate) other).nameKeywords)
                && companyKeywords.equals(((EventContainsKeywordsPredicate) other).companyKeywords)
                && dateKeywords.equals(((EventContainsKeywordsPredicate) other).dateKeywords)
                && timeKeywords.equals(((EventContainsKeywordsPredicate) other).timeKeywords)
                && locationKeywords.equals(((EventContainsKeywordsPredicate) other).locationKeywords)
                && tagKeywords.equals(((EventContainsKeywordsPredicate) other).tagKeywords)); // state check
    }

    /**
     * Returns whether the user input inside the keywords is empty. In particular, if it consists of 1 element of
     * empty string.
     */
    private boolean invalidKeywords(List<String> keywords) {
        return keywords.size() == 1 && keywords.get(0) == "";
    }

}
