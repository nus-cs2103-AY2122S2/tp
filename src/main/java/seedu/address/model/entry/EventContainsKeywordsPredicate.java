package seedu.address.model.entry;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Tests that a {@code Event}'s  {@code companyName} matches any of the keywords given.
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> nameKeywords;
    private final List<String> companyKeywords;
    private final List<String> dateKeywords;
    private final List<String> timeKeywords;
    private final List<String> locationKeywords;
    private final List<String> tagKeywords;

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

    @Override
    public boolean test(Event event) {
        boolean correctName;
        boolean correctCompany;
        boolean correctDate;
        boolean correctTime;
        boolean correctLocation;
        boolean correctTag;

        if(validKeywords(nameKeywords)) {
            correctName = true;
        } else {
            correctName = nameKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().fullName, keyword));
        }
        if(validKeywords(companyKeywords)) {
            correctCompany = true;
        } else {
            correctCompany = companyKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getCompanyName().fullName, keyword));
        }
        if(validKeywords(dateKeywords)) {
            correctDate = true;
        } else {
            correctDate = dateKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getDate().getPure().toString(),
                            keyword));
        }
        if(validKeywords(timeKeywords)) {
            correctTime = true;
        } else {
            correctTime = timeKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getTime().getPure().toString(),
                            keyword));
        }

        if(validKeywords(locationKeywords)) {
            correctLocation = true;
        } else {
            correctLocation = locationKeywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getLocation().getPure(),
                            keyword));
        }

        if(validKeywords(tagKeywords)) {
            correctTag = true;
        } else {
            correctTag = tagKeywords.stream()
                    .anyMatch(keyword -> event.getTags().stream().map(tag -> tag.getPure().toLowerCase())
                            .collect(Collectors.toSet())
                            .contains(keyword));
        }

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

    private boolean validKeywords(List<String> keywords) {
        return keywords.size() == 1 && keywords.get(0) == "";
    }

}
