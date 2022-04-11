package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.VIEW_IMAGES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGES_DIRECTORY;
import static seedu.address.testutil.TypicalSavedImages.populateTestImages;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;

class ImagesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() throws IOException {
        populateTestImages();
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setContactImagesFilePath(TEST_IMAGES_DIRECTORY);

        model = new ModelManager(getTypicalAddressBook(), userPrefs);
        expectedModel = new ModelManager(model.getAddressBook(), userPrefs);
    }

    @Test
    public void execute_nullPersonIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImagesCommand().execute(model));
    }

    @Test
    public void execute_personWithNoImages_success() {
        Command imagesCommand = new ImagesCommand(INDEX_FIRST_PERSON);
        ImageDetailsList emptyList = new ImageDetailsList();
        CommandResult result = new CommandResult(
                String.format(ImagesCommand.MESSAGE_IMAGES_SUCCESS, 0, INDEX_FIRST_PERSON.getOneBased(), emptyList),
                VIEW_IMAGES);
        assertCommandSuccess(imagesCommand, model, result, expectedModel);
    }

    @Test
    public void execute_personWithImages_success() {
        Person personImagesToShow = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        ImageDetailsList personImagesList = personImagesToShow.getImageDetailsList();

        String expectedMessage = String.format(ImagesCommand.MESSAGE_IMAGES_SUCCESS, personImagesList.size(),
                INDEX_SECOND_PERSON.getOneBased(), personImagesList);
        CommandResult expectedResult = new CommandResult(expectedMessage, VIEW_IMAGES);

        expectedModel.setImagesToView(personImagesList);
        Command imagesCommand = new ImagesCommand(INDEX_SECOND_PERSON);

        assertCommandSuccess(imagesCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void executeInDetailedView_success() {
        Person personImagesToShow = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        model.setDetailedContactView(personImagesToShow);

        ImageDetailsList personImagesList = personImagesToShow.getImageDetailsList();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setImagesToView(personImagesList);
        ImagesCommand imagesCommand = new ImagesCommand();

        CommandResult expectedResult = new CommandResult(String.format(ImagesCommand.MESSAGE_IMAGES_SUCCESS,
                personImagesList.size(), personImagesToShow.getName().fullName, personImagesList),
                VIEW_IMAGES);
        assertDetailedViewCommandSuccess(imagesCommand, model, expectedResult, expectedModel);
    }

}
