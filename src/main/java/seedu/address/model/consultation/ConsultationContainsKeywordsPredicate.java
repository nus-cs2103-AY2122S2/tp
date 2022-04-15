package seedu.address.model.consultation;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.patient.Nric;

/**
 * Tests that a {@code Person}'s {@code Consultation} matches any of the keywords given.
 */
public class ConsultationContainsKeywordsPredicate implements Predicate<Consultation> {
    private final List<String> keywords;

    public ConsultationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the specified {@code Consultation} belonging to this patient matches any of the specified keywords.
     *
     * @param consultation Consultation records belonging to the currently viewed patient
     * @return true if any of the keyword matches
     */
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

    /**
     * This overloaded test method facilitates unit testing by passing the currently viewed patient's NRIC as a
     * parameter instead of using the get method.
     *
     * @param consultation Consultation records belonging to the currently viewed patient
     * @param viewedNric Currently viewed patient's NRIC
     * @return true if any of the keyword matches
     */
    public boolean test(Consultation consultation, Nric viewedNric) {
        return StringUtil.containsWordIgnoreCase(consultation.getNric().toString(),
                viewedNric.toString())
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
