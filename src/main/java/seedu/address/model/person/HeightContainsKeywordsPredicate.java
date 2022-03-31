package seedu.address.model.person;

import java.util.function.Predicate;

public class HeightContainsKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public HeightContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        int thisHeight = Integer.parseInt(person.getHeight().value);
        if (keywords.startsWith("gte")) {
            String[] splitArgs = keywords.split("gte");
            int otherHeight = Integer.parseInt(splitArgs[1]);
            return thisHeight >= otherHeight;
        }
        if (keywords.startsWith("gt")) {
            String[] splitArgs = keywords.split("gt");
            int otherHeight = Integer.parseInt(splitArgs[1]);
            return thisHeight > otherHeight;
        }
        if (keywords.startsWith("lte")) {
            String[] splitArgs = keywords.split("lte");
            int otherHeight = Integer.parseInt(splitArgs[1]);
            return thisHeight <= otherHeight;
        }
        if (keywords.startsWith("lt")) {
            String[] splitArgs = keywords.split("lt");
            int otherHeight = Integer.parseInt(splitArgs[1]);
            return thisHeight < otherHeight;
        }
        if (keywords.startsWith("eq")) {
            String[] splitArgs = keywords.split("eq");
            int otherHeight = Integer.parseInt(splitArgs[1]);
            return thisHeight == otherHeight;
        }
        // this should not be reached
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HeightContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((HeightContainsKeywordsPredicate) other).keywords)); // state check
    }
}
