package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PersonUtil.AMY;
import static seedu.address.testutil.PersonUtil.BOB;
import static seedu.address.testutil.PersonUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.PersonUtil.VALID_EMAIL_AMY;
import static seedu.address.testutil.PersonUtil.VALID_EMAIL_BOB;
import static seedu.address.testutil.PersonUtil.VALID_NAME_BOB;
import static seedu.address.testutil.PersonUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.PersonUtil.VALID_TAG_COWORKER;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class PersonTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = AMY;
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(AMY.isSamePerson(AMY));

        // null -> returns false
        assertFalse(AMY.isSamePerson(null));

        // same email, all other attributes different -> returns true
        Person editedPerson = BOB.addFields(new Email(VALID_EMAIL_AMY));
        assertTrue(AMY.isSamePerson(editedPerson));

        // different email, all other attributes same -> returns false
        editedPerson = AMY.addFields(new Email(VALID_EMAIL_BOB));
        assertFalse(AMY.isSamePerson(editedPerson));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person personCopy = new Person(AMY);
        assertTrue(AMY.equals(personCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different person -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Person editedPerson = AMY.addFields(new Name(VALID_NAME_BOB));
        assertFalse(AMY.equals(editedPerson));

        // different phone -> returns false
        editedPerson = AMY.addFields(new Phone(VALID_PHONE_BOB));
        assertFalse(AMY.equals(editedPerson));

        // different email -> returns false
        editedPerson = AMY.addFields(new Email(VALID_EMAIL_BOB));
        assertFalse(AMY.equals(editedPerson));

        // different address -> returns false
        editedPerson = AMY.addFields(new Address(VALID_ADDRESS_BOB));
        assertFalse(AMY.equals(editedPerson));

        // different tags -> returns false
        editedPerson = AMY.setTags(new Tag(VALID_TAG_COWORKER));
        assertFalse(AMY.equals(editedPerson));
    }
}
