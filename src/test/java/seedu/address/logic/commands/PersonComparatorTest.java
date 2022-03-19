package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SortCommand.PersonComparator;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

class PersonComparatorTest {
    @Test
    public void compare_valid_person() {
        Person alice = TypicalPersons.ALICE;
        Person bob = TypicalPersons.BOB;

        PersonComparator nameComparator = new PersonComparator(Arrays.asList(PREFIX_NAME), Arrays.asList("desc"));
        PersonComparator emailComparator = new PersonComparator(Arrays.asList(PREFIX_EMAIL), Arrays.asList("asc"));
        PersonComparator addressComparator = new PersonComparator(Arrays.asList(PREFIX_ADDRESS), Arrays.asList("asc"));
        PersonComparator statusComparator = new PersonComparator(Arrays.asList(PREFIX_STATUS), Arrays.asList("asc"));
        PersonComparator phoneComparator = new PersonComparator(Arrays.asList(PREFIX_PHONE), Arrays.asList("asc"));
        PersonComparator moduleComparator = new PersonComparator(Arrays.asList(PREFIX_MODULE), Arrays.asList("asc"));

        assertTrue(nameComparator.compare(alice, bob) == -1 * alice.getName().compareTo(bob.getName()));
        assertTrue(emailComparator.compare(alice, bob) == alice.getEmail().compareTo(bob.getEmail()));
        assertTrue(addressComparator.compare(alice, bob) == alice.getAddress().compareTo(bob.getAddress()));
        assertTrue(statusComparator.compare(alice, bob) == alice.getStatus().compareTo(bob.getStatus()));
        assertTrue(phoneComparator.compare(alice, bob) == alice.getPhone().compareTo(bob.getPhone()));
        assertTrue(moduleComparator.compare(alice, bob)
                == Integer.compare(alice.getModules().size(), bob.getModules().size()));
    }

    @Test
    public void equals() {
        List<String> firstOrder = Arrays.asList("asc", "asc", "desc", "desc");
        List<String> secondOrder = Arrays.asList("asc", "asc");

        PersonComparator firstPersonComparator = new PersonComparator(Arrays.asList(PREFIX_NAME, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_STATUS), firstOrder);
        PersonComparator secondPersonComparator = new PersonComparator(Arrays.asList(PREFIX_NAME,
                PREFIX_STATUS), secondOrder);

        // same object -> returns true
        assertTrue(firstPersonComparator.equals(firstPersonComparator));

        // same values -> returns true
        PersonComparator firstPersonComparatorCopy = new PersonComparator(Arrays.asList(PREFIX_NAME, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_STATUS), firstOrder);
        assertTrue(firstPersonComparator.equals(firstPersonComparatorCopy));

        // different types -> returns false
        assertFalse(firstPersonComparator.equals(1));

        // null -> returns false
        assertFalse(firstPersonComparator.equals(null));

        // different person -> returns false
        assertFalse(firstPersonComparator.equals(secondPersonComparator));
    }
}
