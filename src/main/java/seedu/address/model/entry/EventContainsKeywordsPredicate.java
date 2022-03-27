package seedu.address.model.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Tests that a {@code Event}'s  {@code companyName} matches any of the keywords given.
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> nameKeywords;
    private final List<String> companyKeywords;
    private final List<String> dateKeywords;
    private final List<String> timeKeywords;
    private final List<String> tagKeywords;

    public EventContainsKeywordsPredicate(List<String> nameKeywords,
                                          List<String> companyKeywords,
                                          List<String> dateKeywords,
                                          List<String> timeKeywords,
                                          List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.companyKeywords = companyKeywords;
        this.dateKeywords = dateKeywords;
        this.timeKeywords = timeKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Event event) {
        boolean correctName;
        boolean correctCompany;
        boolean correctDate;
        boolean correctTime;

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

//        boolean correctTag = tagKeywords.stream()
//                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getTags().toString(), keyword));

        return correctName && correctCompany && correctDate && correctTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((EventContainsKeywordsPredicate) other).nameKeywords)
                && companyKeywords.equals(((EventContainsKeywordsPredicate) other).companyKeywords)
                && dateKeywords.equals(((EventContainsKeywordsPredicate) other).dateKeywords)
                && timeKeywords.equals(((EventContainsKeywordsPredicate) other).timeKeywords)
                && tagKeywords.equals(((EventContainsKeywordsPredicate) other).tagKeywords)); // state check
    }

    private boolean validKeywords(List<String> keywords) {
        return keywords.size() == 1 && keywords.get(0) == "";
    }

}
