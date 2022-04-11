package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.MAIL_X_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MAIL_X_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MailXDescriptorBuilder;

public class MailXDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        MailXCommand.MailXDescriptor descriptorWithSameValues = new MailXCommand.MailXDescriptor(MAIL_X_DESC_AMY);
        assertEquals(MAIL_X_DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(MAIL_X_DESC_AMY, MAIL_X_DESC_AMY);

        // null -> returns false
        assertNotEquals(null, MAIL_X_DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, MAIL_X_DESC_AMY);

        // different values -> returns false
        assertNotEquals(MAIL_X_DESC_AMY, MAIL_X_DESC_BOB);

        // different name -> returns false
        MailXCommand.MailXDescriptor editedAmy = new MailXDescriptorBuilder(MAIL_X_DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertNotEquals(MAIL_X_DESC_AMY, editedAmy);

        // different student number -> returns false
        editedAmy = new MailXDescriptorBuilder(MAIL_X_DESC_AMY).withStudentNumber(VALID_STUDENT_NUMBER_BOB)
                .build();
        assertNotEquals(MAIL_X_DESC_AMY, editedAmy);

        // different email -> returns false
        editedAmy = new MailXDescriptorBuilder(MAIL_X_DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(MAIL_X_DESC_AMY, editedAmy);

        // different mod -> returns false
        editedAmy = new MailXDescriptorBuilder(MAIL_X_DESC_AMY).withMod(VALID_MOD_BOB).build();
        assertNotEquals(MAIL_X_DESC_AMY, editedAmy);
    }
}
