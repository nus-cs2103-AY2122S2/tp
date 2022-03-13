package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;
import seedu.address.model.person.predicates.InsurancePackageContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * A utility class to help with building FieldContainsKeywordsPredicate list.
 */
public class PredicatesListBuilder {
    private final List<FieldContainsKeywordsPredicate> predicatesList;

    public PredicatesListBuilder() {
        this.predicatesList = new ArrayList<>();
    }

    /**
     * Adds the {@code NameContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addNamePredicate(NameContainsKeywordsPredicate namePredicate) {
        predicatesList.add(namePredicate);
        return this;
    }
    /**
     * Adds the {@code PhoneContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addPhonePredicate(PhoneContainsKeywordsPredicate phonePredicate) {
        predicatesList.add(phonePredicate);
        return this;
    }
    /**
     * Adds the {@code EmailContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addEmailPredicate(EmailContainsKeywordsPredicate emailPredicate) {
        predicatesList.add(emailPredicate);
        return this;
    }
    /**
     * Adds the {@code InsurancePackageContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addInsurancePackagePredicate(
            InsurancePackageContainsKeywordsPredicate insurancePackagePredicate) {
        predicatesList.add(insurancePackagePredicate);
        return this;
    }
    /**
     * Adds the {@code AddressContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addAddressPredicate(AddressContainsKeywordsPredicate addressPredicate) {
        predicatesList.add(addressPredicate);
        return this;
    }
    /**
     * Adds the {@code TagsContainsKeywordsPredicate} of the {@code List} that we are building.
     */
    public PredicatesListBuilder addTagsPredicate(TagsContainsKeywordsPredicate tagsPredicate) {
        predicatesList.add(tagsPredicate);
        return this;
    }

    public List<FieldContainsKeywordsPredicate> build() {
        return predicatesList;
    }
}
