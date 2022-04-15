package seedu.address.model.medical;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Medical} matches any of the keywords given.
 */
public class MedicalContainsKeywordsPredicate implements Predicate<Medical> {
    private final List<String> keywords;

    public MedicalContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the specified {@code Medical} from the entire list of medical information matches any of the specified
     * keywords.
     *
     * @param medical Medical record to test against the keywords
     * @return true if this Medical record contains any of the specified keywords
     */
    @Override
    public boolean test(Medical medical) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getPatientNric().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getAge().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getBloodType().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getMedication().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getEthnicity().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getFamilyHistory().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getGender().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getHeight().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getIllnesses().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getImmunizationHistory().toString(),
                        keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getSurgeries().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medical.getWeight().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MedicalContainsKeywordsPredicate) other).keywords)); // state check
    }

}
