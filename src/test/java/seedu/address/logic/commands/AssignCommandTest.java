package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AssignCommand}.
 */
public class AssignCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(null,
                new GroupBuilder().build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToAssign = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        AssignCommand command = new AssignCommand(INDEX_FIRST_PERSON, groupToAssign);

        assertCommandFailure(command, model, AssignCommand.MESSAGE_GROUP_DOES_NOT_EXIST);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Group groupToAssign = new GroupBuilder().build();

        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, groupToAssign);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Group nusFintechSociety = new GroupBuilder().withGroupName("Nus Fintech Society").build();
        Group nusDataScienceSociety = new GroupBuilder().withGroupName("Nus Data Science Society").build();

        AssignCommand assignFirstCommand = new AssignCommand(INDEX_FIRST_PERSON, nusFintechSociety);
        AssignCommand assignSecondCommand = new AssignCommand(INDEX_SECOND_PERSON, nusDataScienceSociety);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(INDEX_FIRST_PERSON, nusFintechSociety);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }
}
