package seedu.address.model.userimage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserImageTest {
    @Test
    public void isSameUserImage() {
        UserImage success = new UserImage(new FilePath("./src/test/resources/images/success.png"),"success");
        assertTrue(success.equals(
                new UserImage(new FilePath("./src/test/resources/images/success.png"),"success")));

        //tests different description
        assertFalse(success.equals(
          new UserImage(new FilePath("./src/test/resources/images/success.png"),"fail")));
        assertFalse(success.equals(
          new UserImage(new FilePath("./src/test/resources/images/success.png"),"")));

        //tests different filepath
        assertFalse(success.equals(
          new UserImage(new FilePath("./src/test/resources/images/fail.png"),"success")));

        //tests different filepath and iamge
        assertFalse(success.equals(
          new UserImage(new FilePath("./src/test/resources/images/fail.png"),"fail")));
    }

    @Test
    public void isValidImage() {
        //tests valid image
        UserImage success = new UserImage(new FilePath("./src/test/resources/images/success.png"),"success");
        assertTrue(success.isImage());

        //tests file that is image extension but not image
        UserImage notImage = new UserImage(new FilePath("./src/test/resources/images/notAImage.png"),"");
        assertFalse(notImage.isImage());

        //tests file that is not an image
        UserImage text = new UserImage(new FilePath("./src/test/resources/images/text.txt"),"");
        assertFalse(text.isImage());
    }
}

