package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class ContainsKeywordsPredicate<T> implements Predicate<Person> {
    private final List<String> keywords;
    private final Function<Person, T> field;

    ContainsKeywordsPredicate(List<String> keywords, Function<Person, T> field) {
        requireNonNull(keywords);
        requireNonNull(field);
        this.keywords = keywords;
        this.field = field;
    }


}
