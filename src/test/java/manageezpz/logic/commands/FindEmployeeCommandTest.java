package manageezpz.logic.commands;

import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalPersons.BENSON;
import static manageezpz.testutil.TypicalPersons.CARL;
import static manageezpz.testutil.TypicalPersons.getTypicalAddressBookEmployees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.Messages;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.person.Person;
import manageezpz.model.person.PersonMultiplePredicate;

class FindEmployeeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());

    @Test
    void equals() {
        PersonMultiplePredicate predicate1 = new PersonMultiplePredicate(List.of("alice"), null, null);
        PersonMultiplePredicate predicate2 = new PersonMultiplePredicate(List.of("bob"), null, null);

        FindEmployeeCommand command1 = new FindEmployeeCommand(predicate1);
        FindEmployeeCommand command2 = new FindEmployeeCommand(predicate2);

        // Test ownself -> true
        assertTrue(command1.equals(command1));

        // Test same values -> true
        PersonMultiplePredicate predicate1Copy = new PersonMultiplePredicate(List.of("alice"), null, null);
        FindEmployeeCommand command1Copy = new FindEmployeeCommand(predicate1Copy);
        assertTrue(command1.equals(command1Copy));

        // Some other object -> false
        assertFalse(command1.equals("False"));

        // null -> false
        assertFalse(command1.equals(null));

        // Different predicate -> false
        assertFalse(command1.equals(command2));
    }

    @Test
    void findEmployeeCommand_withNames_showEmployeesWithNames() {
        PersonMultiplePredicate predicate = new PersonMultiplePredicate(List.of("Alice", "Benson"), null,
                null);
        expectedModel.updateFilteredPersonList(predicate);
        List<Person> expectedPeople = List.of(ALICE, BENSON);
        String message = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindEmployeeCommand command = new FindEmployeeCommand(predicate);

        assertCommandSuccess(command, model, message, expectedModel);
        assertEquals(expectedPeople, model.getFilteredPersonList());
    }

    @Test
    void findEmployeeCommand_withPhone_showEmployeeWithPhone() {
        PersonMultiplePredicate predicate = new PersonMultiplePredicate(null, ALICE.getPhone().toString(),
                null);
        expectedModel.updateFilteredPersonList(predicate);
        List<Person> expectedPeople = List.of(ALICE);
        String message = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindEmployeeCommand command = new FindEmployeeCommand(predicate);

        assertCommandSuccess(command, model, message, expectedModel);
        assertEquals(expectedPeople, model.getFilteredPersonList());
    }

    @Test
    void findEmployeeCommand_withEmail_showEmployeeWithEmail() {
        PersonMultiplePredicate predicate = new PersonMultiplePredicate(null, null,
                CARL.getEmail().toString());
        expectedModel.updateFilteredPersonList(predicate);
        List<Person> expectedPeople = List.of(CARL);
        String message = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindEmployeeCommand command = new FindEmployeeCommand(predicate);

        assertCommandSuccess(command, model, message, expectedModel);
        assertEquals(expectedPeople, model.getFilteredPersonList());
    }
}
