package seedu.address.model.filter;

import seedu.address.commons.util.FilterUtil;
import seedu.address.model.pet.NameContainsKeywordsPredicate;
import seedu.address.model.pet.Pet;

public class TagContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {

    public TagContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        return FilterUtil.tagContainFilterWord(pet.getTags(), getFilterWord());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsFilterWordPredicate// instanceof handles nulls
                && getFilterWord().equals(((TagContainsFilterWordPredicate) other).getFilterWord())); // state check
    }
}
