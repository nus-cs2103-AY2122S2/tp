package seedu.address.model.pet;

import seedu.address.commons.util.StringUtil;

public class OwnerNameContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {

    public OwnerNameContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        return StringUtil.containsWordIgnoreCase(pet.getOwnerName().toString(), getFilterWord());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OwnerNameContainsFilterWordPredicate // instanceof handles nulls
                && getFilterWord().equals(((OwnerNameContainsFilterWordPredicate) other)
                .getFilterWord())); // state check
    }
}
