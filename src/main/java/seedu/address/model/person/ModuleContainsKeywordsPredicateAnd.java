package seedu.address.model.person;

import java.util.List;

public class ModuleContainsKeywordsPredicateAnd extends TagContainsKeywordsPredicateAnd {

    public ModuleContainsKeywordsPredicateAnd(List<String> keywords) {
        super(keywords, person -> person.getModuleStrings());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicateAnd // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
