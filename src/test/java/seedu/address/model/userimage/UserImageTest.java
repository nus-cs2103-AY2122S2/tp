package seedu.address.model.userimage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserImageTest {
    @Test
    public void isSameUserImage() {
        UserImage success = new UserImage(new FilePath("./src/test/resources/images/success.png"), "success");
        assertTrue(success.equals(
                new UserImage(new FilePath("./src/test/resources/images/success.png"), "success")));

        //tests same object
        assertTrue(success.equals(success));

        //tests different description
        assertFalse(success.equals(
              new UserImage(new FilePath("./src/test/resources/images/success.png"), "fail")));
        assertFalse(success.equals(
            new UserImage(new FilePath("./src/test/resources/images/success.png"), "")));

        //tests different filepath
        assertFalse(success.equals(
            new UserImage(new FilePath("./src/test/resources/images/fail.png"), "success")));

        //tests different filepath and iamge
        assertFalse(success.equals(
            new UserImage(new FilePath("./src/test/resources/images/fail.png"), "fail")));
    }

    @Test
    public void isValidImage() {
        //tests valid image
        FilePath success = new FilePath("./src/test/resources/images/success.png");
        assertTrue(UserImage.isImage(success));

        //tests file that is image extension but not image
        FilePath notImage = new FilePath("./src/test/resources/images/notAImage.png");
        assertFalse(UserImage.isImage(notImage));

        //tests file that is not an image
        FilePath text = new FilePath("./src/test/resources/images/text.txt");
        assertFalse(UserImage.isImage(text));
    }

    @Test
    public void equals() {
        UserImage success = new UserImage(new FilePath("./src/test/resources/images/success.png"), "success");
        //Tests other types of objects
        assertFalse(success.equals(new Object()));
    }
}

