package woofareyou.model.filter;

import java.util.function.Predicate;

import woofareyou.model.pet.Pet;

public abstract class FilterByContainsFilterWordPredicate implements Predicate<Pet> {
    private final String filterWord;

    public FilterByContainsFilterWordPredicate(String filterWord) {
        this.filterWord = filterWord;
    }

    /**
     * Accessor method returns a {@code String} representing the filter word.
     * @return Filter word String.
     */
    public String getFilterWord() {
        return this.filterWord;
    }
}
