package seedu.address.model.consultation;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;

/**
 * Tests that a {@code Person}'s {@code Consultation} matches any of the keywords given.
 */
public class ConsultationContainsKeywordsPredicate implements Predicate<Consultation> {
    private final List<String> keywords;

    public ConsultationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Consultation consultation) {
        return StringUtil.containsWordIgnoreCase(consultation.getNric().toString(),
                    ViewedNric.getViewedNric().toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getDate().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getDiagnosis().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getFee().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getNotes().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consultation.getTime().toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ConsultationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
