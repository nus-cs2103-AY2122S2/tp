package seedu.address.model.dish;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Dish}'s {@code NameDish} matches any of the keywords given.
 */
public class NameDishContainsKeywordsPredicate implements Predicate<Dish> {
    private final List<String> keywords;

    public NameDishContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Dish dish) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(dish.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameDishContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameDishContainsKeywordsPredicate) other).keywords)); // state check
    }

}
