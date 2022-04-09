package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INTERVIEW_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BEHAVIOURAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_INTERVIEW_BIG_BANK);
        assertTrue(DESC_INTERVIEW_BIG_BANK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_INTERVIEW_BIG_BANK.equals(DESC_INTERVIEW_BIG_BANK));

        // null -> returns false
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(null));

        // different types -> returns false
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(5));

        // different values -> returns false
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(DESC_INTERVIEW_JANICE_STREET));

        // different name -> returns false
        EditEventDescriptor editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withName(VALID_EVENT_INTERVIEW_JANICE_STREET).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));

        // different companyName -> returns false
        editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withCompanyName(VALID_COMPANY_JANICE_STREET).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));

        // different Date -> returns false
        editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withDate(VALID_DATE_B).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));

        // different Time -> returns false
        editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withTime(VALID_TIME_B).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));

        // different Location -> returns false
        editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withLocation(VALID_LOCATION_B).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));

        // different tags -> returns false
        editedInterviewBigBank = new EditEventDescriptorBuilder(DESC_INTERVIEW_BIG_BANK)
                .withTags(VALID_TAG_BEHAVIOURAL).build();
        assertFalse(DESC_INTERVIEW_BIG_BANK.equals(editedInterviewBigBank));
    }
}
