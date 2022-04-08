package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private final Deadline testDeadline = new Deadline(new Description("testing"),
            new Date("2022-11-08"), new Time("1800"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void getDate_validDate_success() {
        assertEquals("2022-11-08", testDeadline.getDate().getParsedDate().toString());
    }

    @Test
    public void getDescription_validDescription_success() {
        assertEquals("testing", testDeadline.getDescription().toString());
    }

    @Test
    public void getTime_validTime_success() {
        assertEquals("18:00", testDeadline.getTime().getParsedTime().toString());
    }

    @Test
    public void getType_validType_success() {
        assertEquals("deadline", testDeadline.getType());
    }

    @Test
    public void getDateTime_validDateTime_success() {
        String expected = "by Nov 08 2022 6:00 PM";
        assertEquals(expected, testDeadline.getDateTime());
    }

    @Test
    public void setDescription_validDescription_success() {
        testDeadline.setDescription(new Description("Finish Report"));
        assertEquals(testDeadline.getDescription().toString(), "Finish Report");
    }

    @Test
    public void setDate_validDate_success() {
        testDeadline.setDate(new Date("2022-08-08"));
        assertEquals(testDeadline.getDate().getDate(), "2022-08-08");
    }

    @Test
    public void setTime_validTime_success() {
        testDeadline.setTime(new Time("1800"));
        assertEquals(testDeadline.getTime().getTime(), "1800");
    }


    @Test
    public void testToString() {
        String expected = "[D][ ] testing (by: Nov 08 2022 6:00 PM)";
        assertEquals(expected, testDeadline.toString());
    }

}
