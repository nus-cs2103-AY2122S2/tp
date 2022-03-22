package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Faculty;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // only faculty specified
        FilterCommand.FilterDescriptor filterDescriptor = new FilterCommand.FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        filterDescriptor.setFaculty(faculty);
        FilterCommand command = new FilterCommand(filterDescriptor);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty));

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);

        // only covid status specified
        FilterCommand.FilterDescriptor filterDescriptor2 = new FilterCommand.FilterDescriptor();
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        filterDescriptor2.setCovidStatus(status);
        FilterCommand command2 = new FilterCommand(filterDescriptor2);

        expectedModel.updateFilteredPersonList(person -> person.isStatus(status));

        assertCommandSuccess(command2, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // all fields specified
        FilterCommand.FilterDescriptor filterDescriptor = new FilterCommand.FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        filterDescriptor.setFaculty(faculty);
        filterDescriptor.setCovidStatus(status);
        FilterCommand command = new FilterCommand(filterDescriptor);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty));
        expectedModel.updateFilteredPersonList(person -> person.isStatus(status));

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        FilterCommand.FilterDescriptor firstFilterDescriptor = new FilterCommand.FilterDescriptor();
        FilterCommand.FilterDescriptor secondFilterDescriptor = new FilterCommand.FilterDescriptor();

        CovidStatus firstStatus = new CovidStatus(VALID_COVID_STATUS_BOB);

        Faculty firstFaculty = new Faculty(VALID_FACULTY_BOB);
        Faculty secondFaculty = new Faculty(VALID_FACULTY_AMY);

        firstFilterDescriptor.setCovidStatus(firstStatus);
        firstFilterDescriptor.setFaculty(firstFaculty);

        secondFilterDescriptor.setFaculty(secondFaculty);

        FilterCommand firstFilterCommand = new FilterCommand(firstFilterDescriptor);
        FilterCommand secondFilterCommand = new FilterCommand(secondFilterDescriptor);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstFilterDescriptor);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different person -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));
    }
}
