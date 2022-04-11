package seedu.contax.model.appointment;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a tester that checks attributes in Appointments for particular keywords.
 */
public abstract class ContainsKeywordsPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
