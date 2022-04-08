package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Person}'s {@code Height} or {@code Weight} matches any of the keywords given.
 */
public class HeightOrWeightInRangePredicate implements Predicate<Person> {
    private final String keywords;
    private final Prefix prefix;

    /**
     * Constructs a predicate for {@code Height} or {@code Weight}
     * @param keywords the filtering keywords
     * @param prefix the prefix for weight or height
     */
    public HeightOrWeightInRangePredicate(String keywords, Prefix prefix) {
        assert (prefix.equals(PREFIX_HEIGHT) || prefix.equals(PREFIX_WEIGHT));
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Person person) {
        boolean isHeight = prefix.equals(PREFIX_HEIGHT);
        int height = Integer.parseInt(person.getHeight().value);
        int weight = Integer.parseInt(person.getWeight().value);
        int comparable = isHeight ? height : weight;

        if (keywords.startsWith("gte")) {
            String[] splitArgs = keywords.split("gte");
            int otherComparable = Integer.parseInt(splitArgs[1]);
            return comparable >= otherComparable;
        }
        if (keywords.startsWith("gt")) {
            String[] splitArgs = keywords.split("gt");
            int otherComparable = Integer.parseInt(splitArgs[1]);
            return comparable > otherComparable;
        }
        if (keywords.startsWith("lte")) {
            String[] splitArgs = keywords.split("lte");
            int otherComparable = Integer.parseInt(splitArgs[1]);
            return comparable <= otherComparable;
        }
        if (keywords.startsWith("lt")) {
            String[] splitArgs = keywords.split("lt");
            int otherComparable = Integer.parseInt(splitArgs[1]);
            return comparable < otherComparable;
        }
        if (keywords.startsWith("eq")) {
            String[] splitArgs = keywords.split("eq");
            int otherComparable = Integer.parseInt(splitArgs[1]);
            return comparable == otherComparable;
        }
        // this should not be reached
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HeightOrWeightInRangePredicate // instanceof handles nulls
                && keywords.equals(((HeightOrWeightInRangePredicate) other).keywords)
                && prefix.equals(((HeightOrWeightInRangePredicate) other).prefix)); // state check
    }
}
