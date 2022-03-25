package seedu.address.model.pet;

import seedu.address.commons.util.FilterUtil;

public class TagContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {

    public TagContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        return FilterUtil.tagContainFilterWord(pet.getTags(), getFilterWord());
    }
}
