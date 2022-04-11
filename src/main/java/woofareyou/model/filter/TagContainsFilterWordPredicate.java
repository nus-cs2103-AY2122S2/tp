package woofareyou.model.filter;

import woofareyou.commons.util.FilterUtil;
import woofareyou.model.pet.Pet;

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
        if (other == this) {
            return true;
        }
        return (other instanceof TagContainsFilterWordPredicate// instanceof handles nulls
                && getFilterWord().equals(((TagContainsFilterWordPredicate) other).getFilterWord())); // state check
    }
}
