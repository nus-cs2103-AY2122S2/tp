package seedu.address.model.prescription;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.patient.Nric;

/**
 * Tests that a {@code Patient}'s {@code Prescription} matches any of the keywords given.
 */
public class PrescriptionContainsKeywordsPredicate implements Predicate<Prescription> {
    private final List<String> keywords;

    public PrescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the specified {@code Prescription} belonging to this patient matches any of the specified keywords.
     *
     * @param prescription Prescription records belonging to the currently viewed patient
     * @return true if any of the keyword matches
     */
    @Override
    public boolean test(Prescription prescription) {
        return StringUtil.containsWordIgnoreCase(prescription.getPrescriptionTarget().toString(),
                    ViewedNric.getViewedNric().toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getPrescriptionDate().toString(),
                        keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getInstruction().toString(),
                        keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getDrugName().toString(),
                        keyword)));
    }

    /**
     * This overloaded test method facilitates unit testing by passing the currently viewed patient's NRIC as a
     * parameter instead of using the get method.
     *
     * @param prescription Prescription records belonging to the currently viewed patient
     * @param viewedNric Currently viewed patient's NRIC
     * @return true if any of the keyword matches
     */
    public boolean test(Prescription prescription, Nric viewedNric) {
        return StringUtil.containsWordIgnoreCase(prescription.getPrescriptionTarget().toString(),
                viewedNric.toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getPrescriptionDate().toString(),
                        keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getInstruction().toString(),
                        keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(prescription.getDrugName().toString(),
                        keyword)));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PrescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
