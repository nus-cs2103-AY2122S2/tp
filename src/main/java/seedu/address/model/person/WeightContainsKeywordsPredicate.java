package seedu.address.model.person;

import java.util.function.Predicate;

public class WeightContainsKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public WeightContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        int thisWeight = Integer.parseInt(person.getWeight().value);
        if (keywords.startsWith("gte")) {
            String[] splitArgs = keywords.split("gte");
            int otherWeight = Integer.parseInt(splitArgs[1]);
            return thisWeight >= otherWeight;
        }
        if (keywords.startsWith("gt")) {
            String[] splitArgs = keywords.split("gt");
            int otherWeight = Integer.parseInt(splitArgs[1]);
            return thisWeight > otherWeight;
        }
        if (keywords.startsWith("lte")) {
            String[] splitArgs = keywords.split("lte");
            int otherWeight = Integer.parseInt(splitArgs[1]);
            return thisWeight <= otherWeight;
        }
        if (keywords.startsWith("lt")) {
            String[] splitArgs = keywords.split("lt");
            int otherWeight = Integer.parseInt(splitArgs[1]);
            return thisWeight < otherWeight;
        }
        if (keywords.startsWith("eq")) {
            String[] splitArgs = keywords.split("eq");
            int otherWeight = Integer.parseInt(splitArgs[1]);
            return thisWeight == otherWeight;
        }
        // this should not be reached
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeightContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((WeightContainsKeywordsPredicate) other).keywords)); // state check
    }
}
