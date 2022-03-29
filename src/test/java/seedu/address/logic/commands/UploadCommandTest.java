package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UploadCommand.MESSAGE_UPLOAD_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

public class UploadCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_upload_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        FilePath filePath = new FilePath("./src/test/resources/images/success.png");
        UserImage testImage = new UserImage(filePath, "test");
        Set<UserImage> testImages = new LinkedHashSet<>(Arrays.asList(testImage));
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UPLOAD_SUCCESS,
              false, false, false, false, false, false);
        assertCommandSuccess(new UploadCommand(INDEX_FIRST_PERSON, testImages),
                    model, expectedCommandResult, expectedModel);
    }
}
