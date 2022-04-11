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
 * Contains integration tests (interaction with the Model) and unit tests for DetachTagCommand.
 */
public class DetachTagCommandTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfiltered_success() {
        DetachTagCommand detachTagCommand = new DetachTagCommand(FRIEND, INDEX_FIRST_PERSON);
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tempTags = editedPerson.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);
        tagCopy.removeIf(t -> t.isSameTag(FRIEND));
        Person newPerson = new Person(editedPerson.getName(), editedPerson.getPhone(),
                editedPerson.getEmail(), editedPerson.getAddress(), tagCopy, editedPerson.getCourse(),
                editedPerson.getMatricCard(), editedPerson.getTelegram());

        String expectedMessage = String.format(DetachTagCommand.MESSAGE_SUCCESS,
                FRIEND, editedPerson.getName());

        Model expectedModel = new ModelManager(new Unite(model.getUnite()), new UserPrefs());
        expectedModel.setPerson(editedPerson, newPerson);
        assertCommandSuccess(detachTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DetachTagCommand detachTagCommand = new DetachTagCommand(FRIEND, INDEX_FIRST_PERSON);
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tempTags = editedPerson.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);
        tagCopy.removeIf(t -> t.isSameTag(FRIEND));
        Person newPerson = new Person(editedPerson.getName(), editedPerson.getPhone(),
                editedPerson.getEmail(), editedPerson.getAddress(), tagCopy, editedPerson.getCourse(),
                editedPerson.getMatricCard(), editedPerson.getTelegram());

        String expectedMessage = String.format(DetachTagCommand.MESSAGE_SUCCESS,
                FRIEND, editedPerson.getName());

        Model expectedModel = new ModelManager(new Unite(model.getUnite()), new UserPrefs());
        expectedModel.setPerson(editedPerson, newPerson);
        // detach does not update the entire list
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(detachTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistingTagUnfilteredList_failure() {
        DetachTagCommand detachTagCommand = new DetachTagCommand(NONEXIST, INDEX_FIRST_PERSON);
        assertCommandFailure(detachTagCommand, model, DetachTagCommand.MESSAGE_MISSING_TAG);
    }

    @Test
    public void execute_nonExistingTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DetachTagCommand detachTagCommand = new DetachTagCommand(NONEXIST, INDEX_FIRST_PERSON);
        assertCommandFailure(detachTagCommand, model, DetachTagCommand.MESSAGE_MISSING_TAG);
    }

    @Test
    public void execute_nonExistingPersonTagUnfilteredList_failure() {
        DetachTagCommand detachTagCommand = new DetachTagCommand(OWEMONEY, INDEX_FIRST_PERSON);
        assertCommandFailure(detachTagCommand, model, DetachTagCommand.MESSAGE_MISSING_TAG_PERSON);
    }

    @Test
    public void execute_nonExistingPersonTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DetachTagCommand detachTagCommand = new DetachTagCommand(OWEMONEY, INDEX_FIRST_PERSON);
        assertCommandFailure(detachTagCommand, model, DetachTagCommand.MESSAGE_MISSING_TAG_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DetachTagCommand detachTagCommand = new DetachTagCommand(FRIEND, outOfBoundIndex);

        assertCommandFailure(detachTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        DetachTagCommand detachTagCommand = new DetachTagCommand(FRIEND, outOfBoundIndex);

        assertCommandFailure(detachTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DetachTagCommand detachTagCommand = new DetachTagCommand(FRIEND, INDEX_FIRST_PERSON);

        // same values -> returns true
        Tag sameTag = new TagBuilder(FRIEND).build();
        DetachTagCommand commandWithSameValues = new DetachTagCommand(sameTag, INDEX_FIRST_PERSON);
        assertTrue(detachTagCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(detachTagCommand.equals(detachTagCommand));

        // null -> returns false
        assertFalse(detachTagCommand.equals(null));

        // different types -> returns false
        assertFalse(detachTagCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(detachTagCommand.equals(new DetachTagCommand(FRIEND, INDEX_SECOND_PERSON)));

        // different descriptor -> returns false
        assertFalse(detachTagCommand.equals(new DetachTagCommand(OWEMONEY, INDEX_FIRST_PERSON)));
    }

}
