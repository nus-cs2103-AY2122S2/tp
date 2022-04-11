package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code MeetingName}, {@code Module} and {@code Tags} together matches all
 * the keywords given.
 */
public class MeetingContainsAllKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if a {@code Meeting}'s {@code MeetingName},
     * {@code Module} and {@code Tags} together matches all the keywords.
     *
     * @param meeting Meeting whose meeting name, module and tags need to be checked
     *                     to see if they contain all the keywords given.
     * @return True, if meeting's name, module and tags together contains all the keywords.
     */
    @Override
    public boolean test(Meeting meeting) {
        // no meeting matches nothing
        if (keywords.isEmpty()) {
            return false;
        }
        return keywords.stream()
                .allMatch(keyword -> meeting.getName().name.toLowerCase().contains(keyword.toLowerCase())
                        || meeting.getModule().code.toLowerCase().contains(keyword.toLowerCase())
                        || meeting.getTags().stream()
                        .anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
