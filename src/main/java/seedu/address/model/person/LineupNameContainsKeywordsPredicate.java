package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.lineup.LineupName;

/**
 * Tests that a {@code Person}'s {@code LineupName} matches any of the keywords given.
 */
public class LineupNameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public LineupNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        System.out.println("predicate! " + person.getLineupNames());
        System.out.println("keyword! " + keywords);
        return keywords.stream()
                .anyMatch(keyword -> processLineupNameSet(person, keyword));
    }

    private boolean processLineupNameSet(Person person, String keyword) {
        requireNonNull(person);
        requireNonNull(keyword);
        boolean isMatch = false;
        Set<LineupName> lineupNames = person.getLineupNames();
        for (LineupName lineupName : lineupNames) {
            String[] splitLineupName = lineupName.toString().split("\\s+");
            for (int i = 0; i < splitLineupName.length; i++) {
                if (keyword.equals(splitLineupName[i])) {
                    isMatch = true;
                }
            }
        }
        return isMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LineupNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LineupNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
