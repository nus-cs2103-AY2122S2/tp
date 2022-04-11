package seedu.address.model.schedule;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class ScheduleNameContainsKeywordsPredicate implements Predicate<Schedule> {
    private final List<String> keywords;

    public ScheduleNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        schedule.getScheduleName().scheduleName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ScheduleNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
