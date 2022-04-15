package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FilterCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Faculty;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        Block block = new Block(VALID_BLOCK_BOB);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        // only faculty specified
        FilterDescriptor descriptorFaculty = new FilterDescriptor();
        descriptorFaculty.setFaculty(faculty);
        FilterCommand commandFaculty = new FilterCommand(descriptorFaculty);
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty));

        assertCommandSuccess(commandFaculty, model, expectedCommandResult, expectedModel);

        // only covid status specified
        FilterDescriptor descriptorStatus = new FilterDescriptor();
        descriptorStatus.setCovidStatus(status);
        FilterCommand commandStatus = new FilterCommand(descriptorStatus);
        expectedModel.updateFilteredPersonList(person -> person.isStatus(status));

        assertCommandSuccess(commandStatus, model, expectedCommandResult, expectedModel);

        // only block specified
        FilterDescriptor descriptorBlock = new FilterDescriptor();
        descriptorBlock.setBlock(block);
        FilterCommand commandBlock = new FilterCommand(descriptorBlock);
        expectedModel.updateFilteredPersonList(person -> person.isBlock(block));

        assertCommandSuccess(commandBlock, model, expectedCommandResult, expectedModel);

        // only faculty and covid status specified
        FilterDescriptor descriptorFacultyAndStatus = new FilterDescriptor();
        descriptorFacultyAndStatus.setFaculty(faculty);
        descriptorFacultyAndStatus.setCovidStatus(status);
        FilterCommand commandFacultyAndStatus = new FilterCommand(descriptorFacultyAndStatus);
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty) && person.isStatus(status));

        assertCommandSuccess(commandFacultyAndStatus, model, expectedCommandResult, expectedModel);

        // only faculty and block specified
        FilterDescriptor descriptorFacultyAndBlock = new FilterDescriptor();
        descriptorFacultyAndBlock.setFaculty(faculty);
        descriptorFacultyAndBlock.setBlock(block);
        FilterCommand commandFacultyAndBlock = new FilterCommand(descriptorFacultyAndBlock);
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty) && person.isBlock(block));

        assertCommandSuccess(commandFacultyAndBlock, model, expectedCommandResult, expectedModel);

        // only covid status and block specified
        FilterDescriptor descriptorStatusAndBlock = new FilterDescriptor();
        descriptorStatusAndBlock.setCovidStatus(status);
        descriptorStatusAndBlock.setBlock(block);
        FilterCommand commandStatusAndBlock = new FilterCommand(descriptorStatusAndBlock);
        expectedModel.updateFilteredPersonList(person -> person.isStatus(status) && person.isBlock(block));

        assertCommandSuccess(commandStatusAndBlock, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // all fields specified
        FilterDescriptor filterDescriptor = new FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        Block block = new Block(VALID_BLOCK_BOB);
        filterDescriptor.setFaculty(faculty);
        filterDescriptor.setCovidStatus(status);
        filterDescriptor.setBlock(block);
        FilterCommand command = new FilterCommand(filterDescriptor);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> person.isFaculty(faculty) && person.isStatus(status)
                && person.isBlock(block));

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        FilterDescriptor firstFilterDescriptor = new FilterDescriptor();
        FilterDescriptor secondFilterDescriptor = new FilterDescriptor();

        CovidStatus firstStatus = new CovidStatus(VALID_COVID_STATUS_BOB);
        Faculty firstFaculty = new Faculty(VALID_FACULTY_BOB);
        Block firstBlock = new Block(VALID_BLOCK_BOB);

        Faculty secondFaculty = new Faculty(VALID_FACULTY_AMY);

        firstFilterDescriptor.setCovidStatus(firstStatus);
        firstFilterDescriptor.setFaculty(firstFaculty);
        firstFilterDescriptor.setBlock(firstBlock);

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

    @Test
    public void test_filterCommandWordIsCorrect() {
        assertTrue(FilterCommand.COMMAND_WORD.equals("filter"));
    }
}
