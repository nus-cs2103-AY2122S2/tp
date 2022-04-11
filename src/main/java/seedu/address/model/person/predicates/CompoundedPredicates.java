package seedu.address.model.person.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.person.Person;

public class CompoundedPredicates extends PersonContainsKeywordsPredicate {
    private List<PersonContainsKeywordsPredicate> predicateList = new ArrayList<>();

    public CompoundedPredicates() {
        super(null);
    }

    public void addPredicate(PersonContainsKeywordsPredicate pred) {
        predicateList.add(pred);
    }

    @Override
    public boolean test(Person person) {
        // test each predicate, then accumulate them
        Stream<Boolean> boolStrm = predicateList.stream().map(pred -> pred.test(person));
        return boolStrm.reduce(true, (next, acc) -> acc && next);
    }

    public boolean isEmpty() {
        return predicateList.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompoundedPredicates // instanceof handles nulls
                && predicateList.equals(((CompoundedPredicates) other).predicateList)); // state check
    }
}
