package seedu.address.model.filter;

import seedu.address.commons.util.FilterUtil;
import seedu.address.model.pet.Pet;

public class TagContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {

    public TagContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        return FilterUtil.tagContainFilterWord(pet.getTags(), getFilterWord());
    }
}
