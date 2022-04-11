package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.DETAILED_VIEW;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.VIEW_IMAGES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.logic.commands.DeleteImageCommand.MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSavedImages.populateTestImages;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DeleteImageCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    void setUp() throws IOException {
        populateTestImages();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_negativeIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new DeleteImageCommand(Index.fromZeroBased(-1)));
    }

    @Test
    public void execute_success() {
        Person personToRemoveImage = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Index imageToDeleteIndex = Index.fromOneBased(1);
        DeleteImageCommand deleteImageCommand = new DeleteImageCommand(INDEX_SECOND_PERSON, imageToDeleteIndex);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(personToRemoveImage).withImageDetails().build();

        CommandResult expectedResult = new CommandResult(String.format(
                DeleteImageCommand.MESSAGE_DELETE_IMAGE_SUCCESSFUL, imageToDeleteIndex.getOneBased(), editedPerson),
                VIEW_IMAGES);
        expectedModel.setPerson(personToRemoveImage, editedPerson);

        assertCommandSuccess(deleteImageCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_nullPersonIndex_throwsNullPointerException() {
        Command deleteImageCommand = new DeleteImageCommand(Index.fromZeroBased(0));
        assertThrows(NullPointerException.class, () -> deleteImageCommand.execute(model));
    }

    @Test
    public void execute_outOfBoundsPersonIndex_throwsCommandException() {
        Index personIndex = Index.fromZeroBased(model.getSortedPersonList().size());
        Command deleteImageCommand = new DeleteImageCommand(personIndex, Index.fromZeroBased(0));
        assertCommandFailure(deleteImageCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personWithNoImages_throwsCommandException() {
        Index imageIndex = Index.fromZeroBased(0);
        Command deleteImageCommand = new DeleteImageCommand(INDEX_FIRST_PERSON, imageIndex);
        assertCommandFailure(deleteImageCommand, model, MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_outOfBoundsImageIndex_throwsCommandException() {
        int size = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()).getImageDetailsList().size();
        Index imageIndex = Index.fromZeroBased(size);
        Command deleteImageCommand = new DeleteImageCommand(INDEX_SECOND_PERSON, imageIndex);
        assertCommandFailure(deleteImageCommand, model, MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX);
    }

    @Test
    void executeInDetailedView_success() {
        Person personToRemoveImage = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Index imageToDeleteIndex = Index.fromOneBased(1);

        model.setDetailedContactView(personToRemoveImage);

        DeleteImageCommand deleteImageDetailedViewCommand = new DeleteImageCommand(imageToDeleteIndex);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder(personToRemoveImage).withImageDetails().build();
        expectedModel.setDetailedContactView(editedPerson);

        CommandResult expectedResult = new CommandResult(String.format(
                DeleteImageCommand.MESSAGE_DELETE_IMAGE_SUCCESSFUL, imageToDeleteIndex.getOneBased(), editedPerson),
                DETAILED_VIEW);
        expectedModel.setPerson(personToRemoveImage, editedPerson);

        assertDetailedViewCommandSuccess(deleteImageDetailedViewCommand, model, expectedResult, expectedModel);
    }
}
