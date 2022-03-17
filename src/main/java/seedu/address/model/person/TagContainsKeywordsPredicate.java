package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Function;

public abstract class TagContainsKeywordsPredicate extends ContainsKeywordsPredicate<List<String>> {
    private final List<String> keywords;
    private final Function<Person, List<String>> field;

    TagContainsKeywordsPredicate(List<String> keywords, Function<Person, List<String>> field) {
        super(keywords, field);
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> field.apply(person).stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(keyword, tag)));
//        return keywords.stream()
//                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(field.apply(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
