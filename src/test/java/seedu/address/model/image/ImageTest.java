package seedu.address.model.image;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ImageTest {

    private final String imageNameStub = "test";
    private final String imageNameStub1 = "test2";
    private final String imageNameStub2 = "<";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Image(null));
    }

    @Test
    public void isValidFile_invalidPath_throws() {
        assertFalse(Image.isValidFile(imageNameStub2));
    }

    @Test
    public void getImagePath_validPathName() {
        Image image = new Image(imageNameStub);

        assertDoesNotThrow(() -> image.getImagePath());
    }

    @Test
    public void equals() {
        Image image = new Image(imageNameStub);
        Image image2 = new Image(imageNameStub);
        Image image3 = new Image(imageNameStub1);

        assertEquals(image, image2);
        assertNotEquals(image, image3);
    }

    @Test
    void testToString() {
        Image image = new Image(imageNameStub);
        assertNotNull(image.toString());
    }
}
