package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

public class UploadCommandTest {

    @Test
    public void equals() {
        UserImage testImage1 = new UserImage(new FilePath("./src/test/resources/images/success.png"), "");
        UserImage testImage2 = new UserImage(new FilePath("./src/test/resources/images/fail.png"), "fail");
        UploadCommand uploadImage1Command = new UploadCommand(INDEX_FIRST_PERSON,
                    new LinkedHashSet<UserImage>(Arrays.asList(testImage1)));
        UploadCommand uploadImage2Command = new UploadCommand(INDEX_FIRST_PERSON,
             new LinkedHashSet<UserImage>(Arrays.asList(testImage2)));
        // same object -> returns true
        assertTrue(uploadImage1Command.equals(uploadImage1Command));

        // same values -> returns true
        UploadCommand uploadImage1CommandCopy = new UploadCommand(INDEX_FIRST_PERSON,
             new LinkedHashSet<UserImage>(Arrays.asList(testImage1)));
        assertTrue(uploadImage1Command.equals(uploadImage1CommandCopy));

        // different types -> returns false
        assertFalse(uploadImage1Command.equals(1));

        // null -> returns false
        assertFalse(uploadImage1Command.equals(null));

        // different person -> returns false
        assertFalse(uploadImage1Command.equals(uploadImage2Command));
    }
}
