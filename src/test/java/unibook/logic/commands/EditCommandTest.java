package unibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UniBook;
import unibook.model.UserPrefs;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.testutil.EditPersonDescriptorBuilder;
import unibook.testutil.builders.ProfessorBuilder;
import unibook.testutil.builders.StudentBuilder;
import unibook.testutil.typicalclasses.TypicalIndexes;
import unibook.testutil.typicalclasses.TypicalUniBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalUniBook.getTypicalUniBook(), new UserPrefs());


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        ProfessorBuilder personInList = new ProfessorBuilder((Professor) lastPerson);
        Person editedPerson =
            personInList.withName(CommandTestUtil.VALID_NAME_STUDENT_AMY)
                    .withPhone(CommandTestUtil.VALID_PHONE_STUDENT_AMY)
                    .withTags(CommandTestUtil.VALID_TAG).build();

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_STUDENT_AMY)
            .withPhone(CommandTestUtil.VALID_PHONE_STUDENT_AMY).withTags(CommandTestUtil.VALID_TAG).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new UniBook(model.getUniBook()), new UserPrefs());
        expectedModel.setPerson(indexLastPerson.getZeroBased(), lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditCommand.EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new UniBook(model.getUniBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList =
            model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new StudentBuilder((Student) personInFilteredList)
              .withName(CommandTestUtil.VALID_NAME_STUDENT_AMY).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_STUDENT_AMY).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new UniBook(model.getUniBook()), new UserPrefs());
        expectedModel.setPerson(0, model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in unibook
        Person personInList = model.getUniBook().getPersonList()
              .get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder(personInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditCommand.EditPersonDescriptor descriptor =
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_STUDENT_AMY).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of unibook
     */

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of unibook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUniBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_STUDENT_AMY).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
