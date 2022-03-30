package seedu.address.model.person;

import java.util.List;
import java.util.function.Function;

public abstract class TagContainsKeywordsPredicateAnd extends ContainsKeywordsPredicate<List<String>> {
    private final List<String> keywords;
    private final Function<Person, List<String>> field;

    TagContainsKeywordsPredicateAnd(List<String> keywords, Function<Person, List<String>> field) {
        super(keywords, field);
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> field.apply(person).stream()
                        .anyMatch(tag -> tag.contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicateAnd // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicateAnd) other).keywords)); // state check
    }

}
