package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class NoteCommandTest {
    private static final String VALID_NOTE = "Bestie";
    private static final String INVALID_NOTE = "    ";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addValidNote_success() {
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, VALID_NOTE);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withNotes(List.of(VALID_NOTE)).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(NoteCommand.MESSAGE_UPDATE_NOTE_SUCCESS, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addInvalidNote_noChange() {
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, INVALID_NOTE);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(NoteCommand.MESSAGE_NO_UPDATE_TO_NOTES, personToEdit);

        assertCommandSuccess(noteCommand, model, expectedMessage, model);
    }

    @Test
    public void executeInDetailedView_addValidNote_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToEdit);

        Person editedPerson = new PersonBuilder(personToEdit).withNotes(List.of(VALID_NOTE)).build();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        NoteCommand noteCommand = new NoteCommand(VALID_NOTE);
        CommandResult expectedResult = new CommandResult(String.format(NoteCommand.MESSAGE_UPDATE_NOTE_SUCCESS,
                editedPerson), CommandResult.SpecialCommandResult.DETAILED_VIEW);
        assertDetailedViewCommandSuccess(noteCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void executeInDetailedView_addInvalidNote_noChange() {
        NoteCommand noteCommand = new NoteCommand(INVALID_NOTE);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToEdit);
        CommandResult expectedResult = new CommandResult(String.format(NoteCommand.MESSAGE_NO_UPDATE_TO_NOTES,
                personToEdit), CommandResult.SpecialCommandResult.DETAILED_VIEW);

        assertDetailedViewCommandSuccess(noteCommand, model, expectedResult, model);
    }

    @Test
    public void equals() {
        NoteCommand listViewNoteCommand = new NoteCommand(INDEX_FIRST_PERSON, VALID_NOTE);
        NoteCommand detailedViewNoteCommand = new NoteCommand(VALID_NOTE);
        assertFalse(listViewNoteCommand.equals(detailedViewNoteCommand));
    }
}
