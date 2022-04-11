package unibook.model.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unibook.testutil.Assert;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
        //invalid tag names, tags should not have spaces
        assertTrue(!Tag.isValidTagName(" "));
        assertTrue(!Tag.isValidTagName("t o"));
        //tag should be a maxmium of 20 characters
        assertTrue(!Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaa"));
        //tag shouldnt contain non alphanumeric characters
        assertTrue(!Tag.isValidTagName("@adw"));
        assertTrue(!Tag.isValidTagName("s%dw"));
        assertTrue(!Tag.isValidTagName("as*adwa"));
    }

}
