package woofareyou.model.filter;

import woofareyou.commons.util.StringUtil;
import woofareyou.model.pet.Pet;

public class OwnerNameContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {

    public OwnerNameContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        return StringUtil.tagOrNameContainsWordsIgnoreCase(pet.getOwnerName().toString(), getFilterWord());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return (other instanceof OwnerNameContainsFilterWordPredicate // instanceof handles nulls
                && getFilterWord().equals(((OwnerNameContainsFilterWordPredicate) other)
                .getFilterWord())); // state check
    }
}
