package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.person.ContactedDate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ContactStatusMatchPredicateTest {

    @Test
    void test_validCases_returnsTrue() {
        Person person = new PersonBuilder().withContactedDate("").build();
        String arg = " c/";
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(arg, ARRAY_OF_PREFIX);
        ContactedDateMatchPredicate predicate = new ContactedDateMatchPredicate(descriptor);
        assertTrue(predicate.test(person));

        Person personWithTodayDate = new PersonBuilder().withContactedDate(dateThatIsNDaysAgo(2)).build();
        String validArg = " c/2";
        ArgumentMultimap personWithTodayDateMap = ArgumentTokenizer.tokenize(validArg, ARRAY_OF_PREFIX);
        ContactedDateMatchPredicate personWithTodayDatePredicate =
                new ContactedDateMatchPredicate(personWithTodayDateMap);
        assertTrue(personWithTodayDatePredicate.test(personWithTodayDate));
    }

    @Test
    void test_validCases_returnsFalse() {
        Person personWithTodayDate = new PersonBuilder().withContactedDate(dateThatIsNDaysAgo(4)).build();
        String validArg = " c/5";
        ArgumentMultimap personWithTodayDateMap = ArgumentTokenizer.tokenize(validArg, ARRAY_OF_PREFIX);
        ContactedDateMatchPredicate personWithTodayDatePredicate =
                new ContactedDateMatchPredicate(personWithTodayDateMap);
        assertFalse(personWithTodayDatePredicate.test(personWithTodayDate));
    }

    private String dateThatIsNDaysAgo(int n) {
        LocalDate date = LocalDate.now();
        date = date.minusDays(n);
        return date.format(DateTimeFormatter.ofPattern(ContactedDate.DATE_FORMAT));
    }
}
