package seedu.address.model.prescription;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;

/**
 * Tests that a {@code Patient}'s {@code Prescription} matches any of the keywords given.
 */
public class PrescriptionContainsKeywordsPredicate implements Predicate<Prescription> {
    private final List<String> keywords;

    public PrescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PrescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
