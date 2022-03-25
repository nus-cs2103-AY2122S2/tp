package seedu.address.model.filter;

import seedu.address.model.pet.Pet;

import java.util.function.Predicate;

public abstract class FilterByContainsFilterWordPredicate implements Predicate<Pet> {
    private final String filterWord;

    public FilterByContainsFilterWordPredicate(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getFilterWord() {
        return this.filterWord;
    }
}
