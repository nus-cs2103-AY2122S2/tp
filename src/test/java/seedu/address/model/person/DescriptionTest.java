package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {


    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void constructor_invalidDescriptionEmptySpaces_throwsIllegalArgumentException() {
        String invalidDescription = " ";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription("tE394 !!11    descrIptioe394~!"));
        assertTrue(Description.isValidDescription("-")); // one character
        assertTrue(Description.isValidDescription("this is going to be a looooooooooooooooooojjiïirjdorirth"
                + "looooooooooooooooooooooooooontg description with special characterS~~!3$54^&*((peoœø!! and"
                + "punctuations ++2049.....!!!  !! == :>> :)")); // long address
    }
}

