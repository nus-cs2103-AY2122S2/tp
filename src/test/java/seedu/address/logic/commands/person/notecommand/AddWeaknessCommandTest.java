package seedu.address.logic.commands.person.notecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.logic.commands.person.notecommands.AddWeaknessCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddWeaknessCommandTest {

    private static final String NOTE_STUB_1 = "Some note 1";
    private static final String NOTE_STUB_2 = "Some note 2";

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void execute_addWeaknessUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withWeaknesses(NOTE_STUB_1).build();

        AddWeaknessCommand addWeaknessCommand = new AddWeaknessCommand(
                INDEX_FIRST_PERSON,
                new Note(NOTE_STUB_1));

        String expectedMessage = String.format(
                AddWeaknessCommand.MESSAGE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()),
                new TaskBook(model.getTaskBook()),
                new StrategyBoard(),
                new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addWeaknessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddWeaknessCommand addWeaknessCommand = new AddWeaknessCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(addWeaknessCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        AddWeaknessCommand addWeaknessCommand = new AddWeaknessCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(addWeaknessCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateWeakness_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        assert person.getWeaknesses().size() > 0;
        Note duplicateWeakness = person.getWeaknesses().get(0);

        AddWeaknessCommand addWeaknessCommand = new AddWeaknessCommand(INDEX_SECOND_PERSON, duplicateWeakness);

        assertCommandFailure(addWeaknessCommand, model, Messages.MESSAGE_DUPLICATE_WEAKNESS);
    }

    @Test
    public void equals() {
        final AddWeaknessCommand standardCommand = new AddWeaknessCommand(INDEX_FIRST_PERSON,
                new Note(VALID_NOTE_AMY));

        // same values -> returns true
        AddWeaknessCommand commandWithSameValues = new AddWeaknessCommand(INDEX_FIRST_PERSON,
                new Note(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearPersonCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddWeaknessCommand(INDEX_SECOND_PERSON,
                new Note(VALID_NOTE_AMY))));

        // different Note -> returns false
        assertFalse(standardCommand.equals(new AddWeaknessCommand(INDEX_FIRST_PERSON,
                new Note(VALID_NOTE_BOB))));
    }
}
