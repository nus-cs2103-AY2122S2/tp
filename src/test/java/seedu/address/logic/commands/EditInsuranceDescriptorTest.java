package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HEART;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LIFE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_LIFE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_LIFE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditInsuranceCommand.EditInsuranceDescriptor;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;

public class EditInsuranceDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInsuranceDescriptor descriptorWithSameValues = new EditInsuranceDescriptor(DESC_HEART);
        assertTrue(DESC_HEART.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_HEART.equals(DESC_HEART));

        // null -> returns false
        assertFalse(DESC_HEART.equals(null));

        // different types -> returns false
        assertFalse(DESC_HEART.equals(5));

        // different values -> returns false
        assertFalse(DESC_HEART.equals(DESC_LIFE));

        // different title -> returns false
        EditInsuranceDescriptor editedHeart = new EditInsuranceDescriptorBuilder(DESC_HEART)
                .withTitle(VALID_TITLE_LIFE).build();
        assertFalse(DESC_HEART.equals(editedHeart));

        // different price -> returns false
        editedHeart = new EditInsuranceDescriptorBuilder(DESC_HEART).withPrice(VALID_PRICE_LIFE).build();
        assertFalse(DESC_HEART.equals(editedHeart));
    }
}
