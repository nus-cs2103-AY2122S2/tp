package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.person.ContactedDate;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the logic of contacted status check for a {@link Person}.
 */
public class ContactedDateMatchPredicate implements Predicate<Person> {
    private final ArgumentMultimap argMultimap;

    /**
     * Constructs ContactStatusMatchPredicate for the {@link FindCommand}.
     * @param argMultimap Description of the user query that was passed into the FindCommand.
     */
    public ContactedDateMatchPredicate(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    /**
     * Conducts a check on the last contacted date of the {@link seedu.address.model.person.Person Person} to
     * check if the person is eligible to be included in the filtered list. Eligibility is decided by an
     * integer argument passed into the find command where "find c/1" represents choosing contacts that were
     * contacted 1 or more days ago. If no integer argument is found, contacts that had not been contacted are chosen.
     *
     * @param person Person to be tested.
     * @return True if person was last contacted n or more days ago, where n is an integer argument retrieved from
     * the user query. If the user query contains no integer, contacts that were not contacted are selected instead.
     * false otherwise.
     */
    @Override
    public boolean test(Person person) {
        String userArg = argMultimap.getValue(PREFIX_CONTACTED_DATE).orElse("");
        if (userArg.isEmpty()) {
            String personContactedDate = person.getContactedDate().toString();
            return personContactedDate.equals(ContactedDate.MESSAGE_NOT_CONTACTED);
        } else {
            int expectedDaysBetweenDates = Integer.parseInt(userArg);
            LocalDate today = LocalDate.now();
            String personContactedStatus = person.getContactedDate().contactedDate;
            if (personContactedStatus.isEmpty()) {
                return false;
            } else {
                LocalDate lastContactedDate = LocalDate.parse(personContactedStatus, ContactedDate.DATE_FORMATTER);
                return expectedDaysBetweenDates <= ChronoUnit.DAYS.between(lastContactedDate, today);
            }
        }
    }
}
