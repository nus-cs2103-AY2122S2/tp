package seedu.contax.model.appointment;

import java.util.List;
import java.util.function.Predicate;

public abstract class ContainsKeywordsPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
