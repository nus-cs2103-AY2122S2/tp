package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HelpArgumentTest {
    private static final String INVALID_ARGUMENT_1 = "invalid argument";
    private static final String INVALID_ARGUMENT_2 = "list ";
    private static final String INVALID_ARGUMENT_3 = "!@#$%^&*()\\|/.><_-'+'=";
    private static final String INVALID_ARGUMENT_4 = "        ";

    private static final String VALID_ARGUMENT_1 = "add";
    private static final String VALID_ARGUMENT_2 = "";
    private static final String VALID_ARGUMENT_3 = "pass";
    private static final String VALID_ARGUMENT_4 = "export";

    @Test
    public void checkStaticVariable() {
        assertNotNull(HelpArgument.HELP_ARGUMENT_WITH_DESCRIPTION);
        assertNotNull(HelpArgument.OVERALL_HELPING_DESCRIPTION);
        assertNotNull(HelpArgument.ADD_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.EDIT_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.DELETE_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.LIST_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.PASS_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.FAIL_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.ACCEPT_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.REJECT_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.EXIT_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.EXPORT_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.HELP_COMMAND_DESCRIPTION);
        assertNotNull(HelpArgument.COMMAND_NOT_FOUND_DESCRIPTION);
    }

    @Test
    public void construct_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HelpArgument(null));
    }

    @Test
    public void construct_invalidArgument_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new HelpArgument(INVALID_ARGUMENT_1));
        assertThrows(IllegalArgumentException.class, () -> new HelpArgument(INVALID_ARGUMENT_2));
        assertThrows(IllegalArgumentException.class, () -> new HelpArgument(INVALID_ARGUMENT_3));
        assertThrows(IllegalArgumentException.class, () -> new HelpArgument(INVALID_ARGUMENT_4));
    }

    @Test
    public void isValidHelpArgument_invalidArgument_returnsFalse() {
        assertFalse(HelpArgument.isValidHelpArgument(INVALID_ARGUMENT_1));
        assertFalse(HelpArgument.isValidHelpArgument(INVALID_ARGUMENT_2));
        assertFalse(HelpArgument.isValidHelpArgument(INVALID_ARGUMENT_3));
        assertFalse(HelpArgument.isValidHelpArgument(INVALID_ARGUMENT_4));
    }

    @Test
    public void isValidHelpArgument_validArgument_returnsTrue() {
        assertTrue(HelpArgument.isValidHelpArgument(VALID_ARGUMENT_1));
        assertTrue(HelpArgument.isValidHelpArgument(VALID_ARGUMENT_2));
        assertTrue(HelpArgument.isValidHelpArgument(VALID_ARGUMENT_3));
        assertTrue(HelpArgument.isValidHelpArgument(VALID_ARGUMENT_4));
    }

    @Test
    public void toString_returnsCorrectValue() {
        assertEquals(new HelpArgument(VALID_ARGUMENT_1).toString(), HelpArgument.ADD_COMMAND_DESCRIPTION);
        assertEquals(new HelpArgument(VALID_ARGUMENT_2).toString(), HelpArgument.OVERALL_HELPING_DESCRIPTION);
        assertEquals(new HelpArgument(VALID_ARGUMENT_3).toString(), HelpArgument.PASS_COMMAND_DESCRIPTION);
        assertEquals(new HelpArgument(VALID_ARGUMENT_4).toString(), HelpArgument.EXPORT_COMMAND_DESCRIPTION);
    }
}
