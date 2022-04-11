package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code MeetingName}, {@code Module} or {@code Tags} matches any
 * of the keywords given.
 */
public class MeetingContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if a {@code Meeting}'s {@code MeetingName} or {@code Tags} matches any of the keywords.
     *
     * @param meeting Meeting whose meeting name and tags need to be checked
     *                     to see if they contain any of the keywords given.
     * @return True, if meeting's name or tags contains any of the keywords.
     */
    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> meeting.getName().name.toLowerCase().contains(keyword.toLowerCase())
                        || meeting.getModule().code.toLowerCase().contains(keyword.toLowerCase())
                        || meeting.getTags().stream()
                        .anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords)); // state check
    }

}
