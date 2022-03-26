package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabStatus;

class JsonAdaptedLabTest {

    private static final String VALID_LABNUMBER = "1";
    private static final String VALID_LABSTATUS = "GRADED";
    private static final String VALID_LABMARK = "10";
    private static final String VALID_NO_LABMARK = "Unknown";

    private static final String INVALID_LABNUMBER = "a";
    private static final String INVALID_LABSTATUS = "test";
    private static final String INVALID_LABMARK = "a";

    @Test
    public void toModelType_validLabDetails_success() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, VALID_LABSTATUS, VALID_LABMARK);
        try {
            assertEquals(new Lab("1").of("GRADED", "10"), js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_nullLabNumber_throwsIllegalValueException() {
        JsonAdaptedLab js = new JsonAdaptedLab(null, VALID_LABSTATUS, VALID_LABMARK);
        assertThrows(IllegalValueException.class, () -> js.toModelType());
    }

    @Test
    public void toModelType_invalidLabNumber_throwsIllegalValueException() {
        JsonAdaptedLab js = new JsonAdaptedLab(INVALID_LABNUMBER, VALID_LABSTATUS, VALID_LABMARK);
        assertThrows(IllegalValueException.class, () -> js.toModelType());
    }

    @Test
    public void toModelType_nullLabStatus_success() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, null, VALID_LABMARK);
        JsonAdaptedLab js2 = new JsonAdaptedLab(VALID_LABNUMBER, null, VALID_NO_LABMARK);
        Lab expectedLab = new Lab("1").of(VALID_LABSTATUS, VALID_LABMARK);
        Lab expectedLab2 = new Lab("1");
        try {
            assertEquals(expectedLab, js.toModelType());
            assertEquals(expectedLab2, js2.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidLabStatusValidMark_succes() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, INVALID_LABSTATUS, VALID_LABMARK);
        Lab expectedLab = new Lab("1").of(VALID_LABSTATUS, VALID_LABMARK);
        try {
            assertEquals(expectedLab, js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidLabStatusNoMarkAndNullMark_throwsIllegalArgumentException() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, INVALID_LABSTATUS, VALID_NO_LABMARK);
        Lab expectedLab = new Lab("1");
        try {
            assertEquals(expectedLab, js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_nullLabMarkStatusNotGraded_success() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, "SUBMITTED", null);
        Lab expectedLab = new Lab("1").of(LabStatus.SUBMITTED);
        try {
            assertEquals(expectedLab, js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_nullLabMarkStatusGraded_throwIllegalArgumentException() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, VALID_LABSTATUS, null);
        Lab expectedLab = new Lab("1");
        try {
            assertEquals(expectedLab, js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidLabMark_throwIllegalArgumentException() {
        JsonAdaptedLab js = new JsonAdaptedLab(VALID_LABNUMBER, VALID_LABSTATUS, INVALID_LABMARK);
        Lab expectedLab = new Lab("1");
        try {
            assertEquals(expectedLab, js.toModelType());
        } catch (Exception e) {
            fail();
        }
    }
}
