package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for Block class and its methods.
 */
public class BlockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Block(null));
    }

    @Test
    public void constructor_invalidBlock_throwsIllegalArgumentException() {
        String invalidBlock = "";
        assertThrows(IllegalArgumentException.class, () -> new Block(invalidBlock));
    }

    @Test
    public void isValidBlock() {
        //null faculty
        assertFalse(Block.isValidBlock(null));

        //invalid faculty
        assertFalse(Block.isValidBlock("")); // empty string
        assertFalse(Block.isValidBlock(" ")); // spaces only
        assertFalse(Block.isValidBlock("F")); // incorrect status
        assertFalse(Block.isValidBlock("1")); // incorrect status

        //Valid faculty
        assertTrue(Block.isValidBlock("a")); // lowercase, correct status
        assertTrue(Block.isValidBlock("A")); // correct status
    }

    @Test
    public void getBlockEnumAsString() {
        String listOfBlock = "A B C D E ";
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(Block.HallBlock.values()).forEach(block -> stringBuilder.append(block + " "));
        assertEquals(listOfBlock, stringBuilder.toString());
    }
}
