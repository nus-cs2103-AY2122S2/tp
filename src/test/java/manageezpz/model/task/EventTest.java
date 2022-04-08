package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    private final Event testEvent = new Event(new Description("testing"), new Date("2022-11-08"), new Time("1800"),
            new Time("2000"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null));
    }

    @Test
    public void getDescription_validDescription_success() {
        assertEquals("testing", testEvent.getDescription().toString());
    }

    @Test
    public void getDate_validDate_success() {
        assertEquals("2022-11-08", testEvent.getDate().getParsedDate().toString());
    }

    @Test
    public void getStartTime_validTime_success() {
        assertEquals("18:00", testEvent.getStartTime().getParsedTime().toString());
    }

    @Test
    public void getEndTime_validTime_success() {
        assertEquals("20:00", testEvent.getEndTime().getParsedTime().toString());
    }

    @Test
    public void getType_validType_success() {
        assertEquals("event", testEvent.getType());
    }

    @Test
    public void getDateTime_validDateTime_success() {
        String expected = "at Nov 08 2022 6:00 PM to 8:00 PM";
        assertEquals(expected, testEvent.getDateTime());
    }

    @Test
    public void setDescription_validDescription_success() {
        testEvent.setDescription(new Description("Finish Report"));
        assertEquals(testEvent.getDescription().toString(), "Finish Report");
    }

    @Test
    public void setDate_validDate_success() {
        testEvent.setDate(new Date("2022-08-08"));
        assertEquals(testEvent.getDate().getDate(), "2022-08-08");
    }

    @Test
    public void setStartTime_validTime_success() {
        testEvent.setStartTime(new Time("1700"));
        assertEquals(testEvent.getStartTime().getTime(), "1700");
    }

    @Test
    public void setEndTime_validTime_success() {
        testEvent.setEndTime(new Time("1800"));
        assertEquals(testEvent.getEndTime().getTime(), "1800");
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] testing (at: Nov 08 2022 6:00 PM to 8:00 PM)";
        assertEquals(expected, testEvent.toString());
    }

}
