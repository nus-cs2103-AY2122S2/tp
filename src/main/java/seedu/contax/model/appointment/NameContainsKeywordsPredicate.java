package seedu.contax.model.appointment;

import seedu.contax.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Appointment> {

    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && super.getKeywords().equals(((NameContainsKeywordsPredicate) other).getKeywords())); // state check
    }

}
