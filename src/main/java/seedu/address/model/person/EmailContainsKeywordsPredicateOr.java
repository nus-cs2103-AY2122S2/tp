package seedu.address.model.person;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EmailContainsKeywordsPredicateOr implements Predicate<Person> {
    private final List<String> keywords;
    private final Function<Person, String> field;

    /**
     * Constructor for EmailContainsKeywordPredicateOr. THis class was detached from the other
     * FieldContainsKeywordPredicate classes to support functionality for searching for a substring.
     *
     * @param keywords The list of keywords to be searched for.
     */
    public EmailContainsKeywordsPredicateOr(List<String> keywords) {
        this.keywords = keywords;
        this.field = person -> person.getEmail().value;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(field.apply(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicateOr // instanceof handles nulls
                && super.equals(other)); // state check
    }

}
