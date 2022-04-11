package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCompanyDescriptor(DESC_BIG_BANK);
        assertTrue(DESC_BIG_BANK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BIG_BANK.equals(DESC_BIG_BANK));

        // null -> returns false
        assertFalse(DESC_BIG_BANK.equals(null));

        // different types -> returns false
        assertFalse(DESC_BIG_BANK.equals(5));

        // different values -> returns false
        assertFalse(DESC_BIG_BANK.equals(DESC_JANICE_STREET));

        // different name -> returns false
        EditCompanyDescriptor editedBigBank = new EditCompanyDescriptorBuilder(DESC_BIG_BANK)
                .withName(VALID_COMPANY_JANICE_STREET).build();
        assertFalse(DESC_BIG_BANK.equals(editedBigBank));

        // different phone -> returns false
        editedBigBank = new EditCompanyDescriptorBuilder(DESC_BIG_BANK).withPhone(VALID_PHONE_B).build();
        assertFalse(DESC_BIG_BANK.equals(editedBigBank));

        // different email -> returns false
        editedBigBank = new EditCompanyDescriptorBuilder(DESC_BIG_BANK).withEmail(VALID_EMAIL_B).build();
        assertFalse(DESC_BIG_BANK.equals(editedBigBank));

        // different address -> returns false
        editedBigBank = new EditCompanyDescriptorBuilder(DESC_BIG_BANK).withAddress(VALID_ADDRESS_B).build();
        assertFalse(DESC_BIG_BANK.equals(editedBigBank));

        // different tags -> returns false
        editedBigBank = new EditCompanyDescriptorBuilder(DESC_BIG_BANK).withTags(VALID_TAG_INTERVIEW).build();
        assertFalse(DESC_BIG_BANK.equals(editedBigBank));
    }
}
