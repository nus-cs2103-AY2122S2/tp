package seedu.address.model.person.predicates;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Tests all of a {@code Person}'s fields to match the respective fields keywords given.
 */
public class CombineContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {
    private final List<FieldContainsKeywordsPredicate> predicates;

    public CombineContainsKeywordsPredicate(List<FieldContainsKeywordsPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        List<Boolean> result = new ArrayList<>();
        for (FieldContainsKeywordsPredicate predicate : predicates) {
            Boolean predicateResult = predicate.test(person);
            result.add(predicateResult);
        }
        return result.stream().allMatch(val -> val);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof CombineContainsKeywordsPredicate)) {
            return false;
        }

        CombineContainsKeywordsPredicate c = (CombineContainsKeywordsPredicate) other;
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < c.predicates.size(); i++) {
            Boolean predicateEqual = predicates.get(i).equals(c.predicates.get(i));
            result.add(predicateEqual);
        }
        return result.stream().allMatch(val -> val);
    }
}