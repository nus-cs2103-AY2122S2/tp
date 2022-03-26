package seedu.address.model.person;

import java.util.List;
import java.util.function.Function;

import seedu.address.commons.util.StringUtil;

public abstract class FieldContainsKeywordsPredicateOr extends ContainsKeywordsPredicate<String> {
    private final List<String> keywords;
    private final Function<Person, String> field;

    FieldContainsKeywordsPredicateOr(List<String> keywords, Function<Person, String> field) {
        super(keywords, field);
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(field.apply(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicateOr // instanceof handles nulls
                && keywords.equals(((FieldContainsKeywordsPredicateOr) other).keywords)); // state check
    }

}
