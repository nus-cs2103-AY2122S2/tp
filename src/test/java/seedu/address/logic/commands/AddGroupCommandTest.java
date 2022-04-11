package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.ModelStub;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AddGroupCommand}.
 */
public class AddGroupCommandTest {
    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_groupAcceptedByModel_addSuccessful() throws Exception {
        AddGroupCommandTest.ModelStubAcceptingGroupAdded modelStub =
                new AddGroupCommandTest.ModelStubAcceptingGroupAdded();

        Group validGroup = new GroupBuilder().build();

        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        // same success message -> returns equal
        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());

        // same group list -> returns equal
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group validGroup = new GroupBuilder().build();
        AddGroupCommand addGroupCommand = new AddGroupCommand(validGroup);
        ModelStub modelStub = new AddGroupCommandTest.ModelStubWithGroup(validGroup);

        assertThrows(CommandException.class,
            AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> addGroupCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Group nusFintechSociety = new GroupBuilder().withGroupName("Nus Fintech Society").build();
        Group nusDataScienceSociety = new GroupBuilder().withGroupName("Nus Data Science Society").build();
        AddGroupCommand addNusFintechSocietyCommand = new AddGroupCommand(nusFintechSociety);
        AddGroupCommand addNusDataScienceSocietyCommand = new AddGroupCommand(nusDataScienceSociety);

        // same object -> returns true
        assertTrue(addNusFintechSocietyCommand.equals(addNusFintechSocietyCommand));

        // same values -> returns true
        AddGroupCommand addNusFintechSocietyCommandCopy = new AddGroupCommand(nusFintechSociety);
        assertTrue(addNusFintechSocietyCommand.equals(addNusFintechSocietyCommandCopy));

        // different types -> returns false
        assertFalse(addNusFintechSocietyCommand.equals(1));

        // null -> returns false
        assertFalse(addNusFintechSocietyCommand.equals(null));

        // different group -> returns false
        assertFalse(addNusFintechSocietyCommand.equals(addNusDataScienceSocietyCommand));
    }

    /**
     * A Model stub that contains a single group.
     */
    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the group being added.
     */
    private class ModelStubAcceptingGroupAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
