package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.GroupName;

/**
 * Contains unit tests for {@code JsonAdaptedGroup}.
 */
public class JsonAdaptedGroupTest {

    private static final String INVALID_GROUP_NAME = "";

    @Test
    public void toModelType_validGroupName_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(NUS_FINTECH_SOCIETY);
        assertEquals(NUS_FINTECH_SOCIETY, group.toModelType());
    }

    @Test
    public void toModelType_invalidGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(INVALID_GROUP_NAME,
                        new ArrayList<JsonAdaptedPerson>(), new ArrayList<JsonAdaptedTask>());
        String expectedMessage = GroupName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group = new JsonAdaptedGroup(null,
                new ArrayList<JsonAdaptedPerson>(), new ArrayList<JsonAdaptedTask>());

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }
}
