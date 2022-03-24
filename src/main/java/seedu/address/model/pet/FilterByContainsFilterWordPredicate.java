package seedu.address.model.pet;

import java.util.function.Predicate;

public abstract class FilterByContainsFilterWordPredicate implements Predicate<Pet> {
    public String filterWord;

    public FilterByContainsFilterWordPredicate(String filterWord) {
        this.filterWord = filterWord;
    }
}
