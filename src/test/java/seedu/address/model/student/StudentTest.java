package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTutorialGroups().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .withGitHub(VALID_GITHUB_BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new StudentBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different gitHub -> returns false
        editedAlice = new StudentBuilder(ALICE).withGitHub(VALID_GITHUB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tutorial groups -> returns false
        editedAlice = new StudentBuilder(ALICE).withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void tutorialGroupExists() {
        Student aliceCopy = new StudentBuilder(ALICE).build();

        // same tutorial group -> returns true
        assertTrue(ALICE.tutorialGroupExists(aliceCopy.getTutorialGroups()));

        // different tutorial group -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        assertFalse(editedAlice.tutorialGroupExists(aliceCopy.getTutorialGroups()));

        // null -> returns false
        assertFalse(ALICE.tutorialGroupExists((Set<TutorialGroup>) null));
    }
}
