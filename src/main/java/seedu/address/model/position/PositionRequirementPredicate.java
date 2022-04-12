package seedu.address.model.position;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Position} has a {@code Requirement} that matches any of the keywords given.
 */
public class PositionRequirementPredicate implements Predicate<Position> {
    private final List<String> keywords;

    public PositionRequirementPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Position position) {
        for (Requirement req : position.getRequirements()) {
            if (keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(req.requirementText, keyword))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionRequirementPredicate // instanceof handles nulls
                && keywords.equals(((PositionRequirementPredicate) other).keywords)); // state check
    }
}
