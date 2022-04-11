package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_SLOT_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GARENA;
import static seedu.address.testutil.TypicalApplications.GRAB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class ApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Application application = new ApplicationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> application.getTags().remove(0));
    }

    @Test
    public void isSameApplication() {
        // same object -> returns true
        assertTrue(GRAB.isSameApplication(GRAB));

        // null -> returns false
        assertFalse(GRAB.isSameApplication(null));

        // same name, job title and no tags, all other attributes different -> returns true
        Application editedGrab = new ApplicationBuilder(GRAB).withJobTitle(GRAB.getJobTitle().toString())
                .withPhone(VALID_PHONE_GARENA).withEmail(VALID_EMAIL_GARENA)
                .withAddress(VALID_ADDRESS_GARENA).build();
        assertTrue(GRAB.isSameApplication(editedGrab));

        // different name, all other attributes same -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withName(VALID_NAME_GARENA).build();
        assertFalse(GRAB.isSameApplication(editedGrab));

        // different job title, all other attributes same -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withJobTitle(VALID_JOBTITLE_GARENA).build();
        assertFalse(GRAB.isSameApplication(editedGrab));

        // different tags, all other attributes same -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withTags(VALID_TAG_LOCAL).build();
        assertFalse(GRAB.isSameApplication(editedGrab));

        // name differs in case, all other attributes same -> returns false
        Application editedGarena = new ApplicationBuilder(GARENA).withName(VALID_NAME_GARENA.toLowerCase()).build();
        assertFalse(GARENA.isSameApplication(editedGarena));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_GARENA + " ";
        editedGarena = new ApplicationBuilder(GARENA).withName(nameWithTrailingSpaces).build();
        assertFalse(GARENA.isSameApplication(editedGarena));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application aliceCopy = new ApplicationBuilder(GRAB).build();
        assertTrue(GRAB.equals(aliceCopy));

        // same object -> returns true
        assertTrue(GRAB.equals(GRAB));

        // null -> returns false
        assertFalse(GRAB.equals(null));

        // different type -> returns false
        assertFalse(GRAB.equals(5));

        // different person -> returns false
        assertFalse(GRAB.equals(GARENA));

        // different name -> returns false
        Application editedGrab = new ApplicationBuilder(GRAB).withName(VALID_NAME_GARENA).build();
        assertFalse(GRAB.equals(editedGrab));

        // different phone -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withPhone(VALID_PHONE_GARENA).build();
        assertFalse(GRAB.equals(editedGrab));

        // different email -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withEmail(VALID_EMAIL_GARENA).build();
        assertFalse(GRAB.equals(editedGrab));

        // different address -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withAddress(VALID_ADDRESS_GARENA).build();
        assertFalse(GRAB.equals(editedGrab));

        //different interview slot -> return false
        editedGrab = new ApplicationBuilder(GRAB).withAddress(VALID_INTERVIEW_SLOT_GARENA).build();
        assertFalse(GRAB.equals(editedGrab));

        // different tags -> returns false
        editedGrab = new ApplicationBuilder(GRAB).withTags(VALID_TAG_LOCAL).build();
        assertFalse(GRAB.equals(editedGrab));
    }
}
