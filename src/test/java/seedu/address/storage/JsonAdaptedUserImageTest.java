package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;


public class JsonAdaptedUserImageTest {

    private static final String VALID_FILEPATH = "./src/test/resources/images/success.png";
    private static final String VALID_DESCRIPTION = "test";
    private static final String INVALID_FILEPATH = null;

    @Test
    public void toModelType_validUserImage_returnsUserImage() throws Exception {
        JsonAdaptedUserImage userImage = new JsonAdaptedUserImage(VALID_FILEPATH, VALID_DESCRIPTION);
        UserImage expectedUserImage = new UserImage(new FilePath(VALID_FILEPATH), VALID_DESCRIPTION);
        assertEquals(expectedUserImage, userImage.toModelType());
    }

    @Test
    public void toModelType_invalidFilePath_throwsIllegalValueException() {
        JsonAdaptedUserImage userImage = new JsonAdaptedUserImage(INVALID_FILEPATH, VALID_DESCRIPTION);
        String expectedMessage = FilePath.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, userImage::toModelType);
    }
}
