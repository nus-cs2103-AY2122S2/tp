package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeassignCommand}.
 */
public class DeassignCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null,
                new GroupBuilder().build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToDeassign = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        AssignCommand command = new AssignCommand(INDEX_FIRST_PERSON, groupToDeassign);

        assertCommandFailure(command, model, DeassignCommand.MESSAGE_GROUP_DOES_NOT_EXIST);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Group groupToDeassign = new GroupBuilder().build();

        DeassignCommand deassignCommand = new DeassignCommand(outOfBoundIndex, groupToDeassign);

        assertCommandFailure(deassignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Group nusFintechSociety = new GroupBuilder().withGroupName("Nus Fintech Society").build();
        Group nusDataScienceSociety = new GroupBuilder().withGroupName("Nus Data Science Society").build();

        DeassignCommand deassignFirstCommand = new DeassignCommand(INDEX_FIRST_PERSON, nusFintechSociety);
        DeassignCommand deassignSecondCommand = new DeassignCommand(INDEX_SECOND_PERSON, nusDataScienceSociety);

        // same object -> returns true
        assertTrue(deassignFirstCommand.equals(deassignFirstCommand));

        // same values -> returns true
        DeassignCommand deassignFirstCommandCopy = new DeassignCommand(INDEX_FIRST_PERSON, nusFintechSociety);
        assertTrue(deassignFirstCommand.equals(deassignFirstCommandCopy));

        // different types -> returns false
        assertFalse(deassignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deassignFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(deassignFirstCommand.equals(deassignSecondCommand));
    }
}
