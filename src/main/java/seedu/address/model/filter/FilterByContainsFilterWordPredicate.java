package seedu.address.model.filter;

import java.util.function.Predicate;

import seedu.address.model.pet.Pet;

public abstract class FilterByContainsFilterWordPredicate implements Predicate<Pet> {
    private final String filterWord;

    public FilterByContainsFilterWordPredicate(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getFilterWord() {
        return this.filterWord;
    }
}
