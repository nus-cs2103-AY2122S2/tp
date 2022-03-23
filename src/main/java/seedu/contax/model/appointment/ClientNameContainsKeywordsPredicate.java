package seedu.contax.model.appointment;

import seedu.contax.commons.util.StringUtil;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ClientNameContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Appointment> {

    public ClientNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Appointment appointment) {
        return super.getKeywords().stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getPerson().getName().fullName,keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientNameContainsKeywordsPredicate // instanceof handles nulls
                && super.getKeywords().equals(((ClientNameContainsKeywordsPredicate) other).getKeywords())); // state check
    }

}
