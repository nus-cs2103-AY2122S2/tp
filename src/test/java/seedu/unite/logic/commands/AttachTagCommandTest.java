package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.unite.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.unite.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;
import static seedu.unite.testutil.TypicalTags.FRIEND;
import static seedu.unite.testutil.TypicalTags.NONEXIST;
import static seedu.unite.testutil.TypicalTags.OWEMONEY;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.unite.commons.core.Messages;
import seedu.unite.commons.core.index.Index;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.Unite;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.testutil.TagBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for AttachTagCommand.
 */
public class AttachTagCommandTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfiltered_success() {
        AttachTagCommand attachTagCommand = new AttachTagCommand(OWEMONEY, INDEX_FIRST_PERSON);
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tempTags = editedPerson.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);
        tagCopy.add(OWEMONEY);
        Person newPerson = new Person(editedPerson.getName(), editedPerson.getPhone(),
                editedPerson.getEmail(), editedPerson.getAddress(), tagCopy, editedPerson.getCourse(),
                editedPerson.getMatricCard(), editedPerson.getTelegram());

        String expectedMessage = String.format(AttachTagCommand.MESSAGE_SUCCESS,
                OWEMONEY, editedPerson.getName());

        Model expectedModel = new ModelManager(new Unite(model.getUnite()), new UserPrefs());
        expectedModel.setPerson(editedPerson, newPerson);
        assertCommandSuccess(attachTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tempTags = personInFilteredList.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);
        tagCopy.add(OWEMONEY);
        Person newPerson = new Person(personInFilteredList.getName(), personInFilteredList.getPhone(),
                personInFilteredList.getEmail(), personInFilteredList.getAddress(),
                tagCopy, personInFilteredList.getCourse(), personInFilteredList.getMatricCard(),
                personInFilteredList.getTelegram());
        AttachTagCommand attachTagCommand = new AttachTagCommand(OWEMONEY, INDEX_FIRST_PERSON);
        String expectedMessage = String.format(AttachTagCommand.MESSAGE_SUCCESS,
                OWEMONEY, personInFilteredList.getName());

        Model expectedModel = new ModelManager(new Unite(model.getUnite()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), newPerson);
        // attach does not update the entire list
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(attachTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistingTagUnfilteredList_failure() {
        AttachTagCommand attachTagCommand = new AttachTagCommand(NONEXIST, INDEX_FIRST_PERSON);
        assertCommandFailure(attachTagCommand, model, AttachTagCommand.MESSAGE_MISSING_TAG);
    }

    @Test
    public void execute_nonExistingTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        AttachTagCommand attachTagCommand = new AttachTagCommand(NONEXIST, INDEX_FIRST_PERSON);
        assertCommandFailure(attachTagCommand, model, AttachTagCommand.MESSAGE_MISSING_TAG);
    }

    @Test
    public void execute_duplicateTagUnfilteredList_failure() {
        AttachTagCommand attachTagCommand = new AttachTagCommand(FRIEND, INDEX_FIRST_PERSON);
        assertCommandFailure(attachTagCommand, model, AttachTagCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_duplicateTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        AttachTagCommand attachTagCommand = new AttachTagCommand(FRIEND, INDEX_FIRST_PERSON);
        assertCommandFailure(attachTagCommand, model, AttachTagCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AttachTagCommand attachTagCommand = new AttachTagCommand(OWEMONEY, outOfBoundIndex);

        assertCommandFailure(attachTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of unite
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of unite list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnite().getPersonList().size());

        AttachTagCommand attachTagCommand = new AttachTagCommand(OWEMONEY, outOfBoundIndex);

        assertCommandFailure(attachTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AttachTagCommand attachTagCommand = new AttachTagCommand(OWEMONEY, INDEX_FIRST_PERSON);

        // same values -> returns true
        Tag sameTag = new TagBuilder(OWEMONEY).build();
        AttachTagCommand commandWithSameValues = new AttachTagCommand(sameTag, INDEX_FIRST_PERSON);
        assertTrue(attachTagCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(attachTagCommand.equals(attachTagCommand));

        // null -> returns false
        assertFalse(attachTagCommand.equals(null));

        // different types -> returns false
        assertFalse(attachTagCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(attachTagCommand.equals(new AttachTagCommand(OWEMONEY, INDEX_SECOND_PERSON)));

        // different descriptor -> returns false
        assertFalse(attachTagCommand.equals(new AttachTagCommand(FRIEND, INDEX_FIRST_PERSON)));
    }

}
