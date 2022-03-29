package seedu.contax.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.contax.commons.util.StringUtil;


/**
 * Tests that an {@code Appointment}'s {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonNameContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Appointment> {

    public PersonNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        if (appointment.getPerson() == null) {
            return false;
        }

        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getPerson().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonNameContainsKeywordsPredicate // instanceof handles nulls
                && super.getKeywords().equals(((PersonNameContainsKeywordsPredicate) other)
                .getKeywords())); // state check
    }

}
