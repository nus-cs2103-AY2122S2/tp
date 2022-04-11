package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;

class JsonAdaptedNameTest {
    private static final String VALID_NAME_STRING = AMY.getName().toString();
    private static final String INVALID_NAME_STRING = "R@chel";

    @Test
    void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedName name = new JsonAdaptedName(INVALID_NAME_STRING);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalValueException.class, expectedMessage, name::toModelType);
    }

    @Test
    void toModelType_validName_returnsName() throws Exception {
        JsonAdaptedName name = new JsonAdaptedName(VALID_NAME_STRING);
        assertEquals(AMY.getName(), name.toModelType());
    }

    @Test
    void getName_sameName_success() {
        JsonAdaptedName name = new JsonAdaptedName(VALID_NAME_STRING);
        assertEquals(VALID_NAME_STRING, name.getName());
    }

    @Test
    void getName_differentName_failure() {
        JsonAdaptedName name = new JsonAdaptedName(VALID_NAME_STRING);
        assertNotEquals("Amy BeE", name.getName());
        assertNotEquals("Amy Beee", name.getName());
        assertNotEquals("AmyBeE", name.getName());
        assertNotEquals("AMY BeE", name.getName());
    }
}
