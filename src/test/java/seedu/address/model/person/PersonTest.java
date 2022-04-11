package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTeams().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withGithubUsername(VALID_USERNAME_BOB).withTeams(VALID_TEAM_GOOGLE).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        //person have the same phone, all other attributes different -> returns true
        Person personWithSamePhone = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(BOB.isSamePerson(personWithSamePhone));

        //person have the same github username, all other attributes different -> returns true
        Person personWithSameGitHub = new PersonBuilder(ALICE).withGithubUsername(VALID_USERNAME_BOB).build();
        assertTrue(BOB.isSamePerson(personWithSameGitHub));

        //person have the same email, all other attributes different -> returns true
        Person personWithSameEmail = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(BOB.isSamePerson(personWithSameEmail));
    }

    @Test
    public void getDuplicateValue() {
        Person personWithSameEmail = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertEquals(BOB.getDuplicateValue(personWithSameEmail), "Email");

        Person personWithSameGitHub = new PersonBuilder(ALICE).withGithubUsername(VALID_USERNAME_BOB).build();
        assertEquals(BOB.getDuplicateValue(personWithSameGitHub), "Github UserName");

        Person personWithSamePhone = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertEquals(BOB.getDuplicateValue(personWithSamePhone), "Phone");

        assertEquals(ALICE.getDuplicateValue(BOB), "Error no same duplicate values");
    }


    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different username -> returns false
        editedAlice = new PersonBuilder(ALICE).withGithubUsername(VALID_USERNAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different isPotentialTeammate field -> returns false
        editedAlice = new PersonBuilder(ALICE).isPotentialTeammate(true).build();
        assertFalse(ALICE.equals(editedAlice));

        // different s -> returns false
        editedAlice = new PersonBuilder(ALICE).withTeams(VALID_TEAM_GOOGLE).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
