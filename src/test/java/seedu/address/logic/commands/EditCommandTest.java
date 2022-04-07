package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_HACKING_TEAM;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        boolean isResetmode = true;
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor, isResetmode);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SINGLE_PERSON_SUCCESS, editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getDisplayPersonList().get(0), editedPerson);
        expectedModel.commitAddressBook();
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getDisplayPersonList().size());
        Person lastPerson = model.getDisplayPersonList().get(indexLastPerson.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(lastPerson);

        // Preparing the edit command
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withTeams(VALID_TEAM_GOOGLE).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor, true);

        // Preparing the expected result, which are the expected model and message
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTeams(VALID_TEAM_GOOGLE).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);
        expectedModel.commitAddressBook();

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SINGLE_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor(), true);

        //making expected Message
        Person editedPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SINGLE_PERSON_SUCCESS, editedPerson);

        //making expected model, which is the same as original model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.commitAddressBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // make the app show filtered list
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        //making the edit command
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build(), true);

        // making expected model
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getDisplayPersonList().get(0), editedPerson);
        expectedModel.commitAddressBook();

        //making expected msg
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SINGLE_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor, true);

        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() throws CommandException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder(personInList).build(), true);

        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDisplayPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor, true);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INDEX_FOR_PERSON);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build(), true);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_INDEX_FOR_PERSON);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY, true);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor, true);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY, true)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB, true)));
    }

    //test batch edit
    @Test
    public void execute_batchEditAllIndicesValidInDefaultMode_success() {
        //making the edit command to test
        List<Index> targetIndices = new ArrayList<>();
        targetIndices.add(INDEX_FIRST_PERSON);
        targetIndices.add(INDEX_SECOND_PERSON);
        EditPersonDescriptor editPersonDescriptorToAddTeam =
            new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM).build();
        EditCommand editCommand = new EditCommand(targetIndices, editPersonDescriptorToAddTeam, false);

        //making the expected model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstIndexPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondIndexPerson = model.getDisplayPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder firstIndexEditPersonBuilder =
            new PersonBuilder(firstIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        PersonBuilder secondIndexEditPersonBuilder =
            new PersonBuilder(secondIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        expectedModel.setPerson(firstIndexPerson, firstIndexEditPersonBuilder.build());
        expectedModel.setPerson(secondIndexPerson, secondIndexEditPersonBuilder.build());
        expectedModel.commitAddressBook();

        //making the expected display message
        List<Name> editedNames = new ArrayList<>();
        editedNames.add(firstIndexPerson.getName());
        editedNames.add(secondIndexPerson.getName());
        String expectedMsg = String.format(MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS, editedNames);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }

    //test batch edit when some indices are invalid
    @Test
    public void execute_batchEditSomeInvalidIndices_success() {

        //make edit command
        List<Index> targetIndices = new ArrayList<>();
        targetIndices.add(INDEX_FIRST_PERSON);
        targetIndices.add(INDEX_SECOND_PERSON);
        Index veryBigInvalidIndex = Index.fromOneBased(1000);
        targetIndices.add(veryBigInvalidIndex);
        assertTrue(veryBigInvalidIndex.getZeroBased() > model.getDisplayPersonList().size());
        EditPersonDescriptor editPersonDescriptorToAddTeam =
            new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM).build();
        EditCommand editCommand = new EditCommand(targetIndices, editPersonDescriptorToAddTeam, false);

        //make expected model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstIndexPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondIndexPerson = model.getDisplayPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder firstIndexEditPersonBuilder =
            new PersonBuilder(firstIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        PersonBuilder secondIndexEditPersonBuilder =
            new PersonBuilder(secondIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        expectedModel.setPerson(firstIndexPerson, firstIndexEditPersonBuilder.build());
        expectedModel.setPerson(secondIndexPerson, secondIndexEditPersonBuilder.build());
        expectedModel.commitAddressBook();

        //make expected msg
        List<Name> editedNames = new ArrayList<>();
        editedNames.add(firstIndexPerson.getName());
        editedNames.add(secondIndexPerson.getName());
        String expectedMsg = String.format(
            Messages.MESSAGE_INVALID_INDEX_FOR_SOME_PERSON + "\n" + MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS, editedNames);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }

    //test batch edit to silently ignore fields other than team and skills, without throwing an error
    @Test
    public void execute_batchEditShouldEnableOnlyTeamsAndSkillsChange_success() {
        //making edit command
        List<Index> targetIndices = new ArrayList<>();
        targetIndices.add(INDEX_FIRST_PERSON);
        targetIndices.add(INDEX_SECOND_PERSON);
        //changing names while adding teams
        EditPersonDescriptor editPersonDescriptorToAddTeam =
            new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM)
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(targetIndices, editPersonDescriptorToAddTeam, false);

        //make expected model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstIndexPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondIndexPerson = model.getDisplayPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder firstIndexEditPersonBuilder =
            new PersonBuilder(firstIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        PersonBuilder secondIndexEditPersonBuilder =
            new PersonBuilder(secondIndexPerson).addTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        expectedModel.setPerson(firstIndexPerson, firstIndexEditPersonBuilder.build());
        expectedModel.setPerson(secondIndexPerson, secondIndexEditPersonBuilder.build());
        expectedModel.commitAddressBook();

        //make expected msg
        List<Name> editedNames = new ArrayList<>();
        editedNames.add(firstIndexPerson.getName());
        editedNames.add(secondIndexPerson.getName());
        String expectedMsg = String.format(MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS, editedNames);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }

    //test reset mode
    @Test
    public void execute_resetMode_success() {
        //making batch edit command
        List<Index> targetIndices = new ArrayList<>();
        targetIndices.add(INDEX_FIRST_PERSON);
        targetIndices.add(INDEX_SECOND_PERSON);
        EditPersonDescriptor editPersonDescriptorToAddTeam =
            new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM)
                .withName(VALID_NAME_BOB).build();
        //reset mode is true
        EditCommand editCommand = new EditCommand(targetIndices, editPersonDescriptorToAddTeam, true);

        //make expected model
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person firstIndexPerson = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondIndexPerson = model.getDisplayPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder firstIndexEditPersonBuilder =
            new PersonBuilder(firstIndexPerson).withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        PersonBuilder secondIndexEditPersonBuilder =
            new PersonBuilder(secondIndexPerson).withTeams(VALID_TEAM_GOOGLE, VALID_TEAM_HACKING_TEAM);
        expectedModel.setPerson(firstIndexPerson, firstIndexEditPersonBuilder.build());
        expectedModel.setPerson(secondIndexPerson, secondIndexEditPersonBuilder.build());
        expectedModel.commitAddressBook();

        //make expected msg
        List<Name> editedNames = new ArrayList<>();
        editedNames.add(firstIndexPerson.getName());
        editedNames.add(secondIndexPerson.getName());
        String expectedMsg = String.format(MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS, editedNames);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }
}
