package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InstructionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Instruction(null));
    }

    @Test
    public void constructor_invalidInstruction_throwsIllegalArgumentException() {
        String invalidInstruction = "";
        assertThrows(IllegalArgumentException.class, () -> new Instruction(invalidInstruction));
    }

    @Test
    public void isValidInstruction() {
        // null address
        assertThrows(NullPointerException.class, () -> Instruction.isValidInstruction(null));

        // invalid addresses
        assertFalse(Instruction.isValidInstruction("")); // empty string
        assertFalse(Instruction.isValidInstruction(" ")); // spaces only

        // valid addresses
        assertTrue(Instruction.isValidInstruction("2 tablets everyday"));
        assertTrue(Instruction.isValidInstruction("-")); // one character
        assertTrue(Instruction.isValidInstruction("You need to consume 2 tablets everyday")); // long address
    }
}
