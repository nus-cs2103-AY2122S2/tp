package seedu.address.model.common;

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
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("")); // empty string

        // valid description
        assertTrue(Description.isValidDescription("tE394 !!11    descrIptioe394~!"));
        assertTrue(Description.isValidDescription("-")); // one character
        assertTrue(Description.isValidDescription("this is going to be a looooooooooooooooooojjiïirjdorirth"
                + "looooooooooooooooooooooooooontg description with special characterS~~!3$54^&*((peoœø!! and"
                + "punctuations ++2049.....!!!  !! == :>> :)")); // long address
    }

    @Test
    public void equals() {
        Description descriptionOne = new Description("test description one");
        Description descriptionOneCopy = new Description("test description one");
        Description descriptionTwo = new Description("test description two");
        Description descriptionNull = new Description(null);

        //different object but same value
        assertTrue(descriptionOneCopy.equals(descriptionOneCopy));

        //different object and different value
        assertFalse(descriptionOne.equals(descriptionTwo));

        //null values
        assertTrue(descriptionNull.equals(new Description(null)));

        assertFalse(descriptionOne.equals(descriptionNull));

        //different object
        assertFalse(descriptionOne.equals(1));

        assertTrue(descriptionOne.hashCode() == (descriptionOne.hashCode()));

        assertTrue(descriptionOne.hashCode() == (descriptionOneCopy.hashCode()));

        assertTrue(descriptionOne.hashCode() != (descriptionTwo.hashCode()));

    }
}

