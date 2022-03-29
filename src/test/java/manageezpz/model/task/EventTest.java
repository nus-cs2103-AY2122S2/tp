package manageezpz.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    private final Event testEvent = new Event(new Description("testing"), new Date("2022-11-08"), new Time("1800"),
            new Time("2000"));

    @Test
    public void testGetters() {
        assertEquals("testing", testEvent.getDescription().toString());
        assertEquals("2022-11-08", testEvent.getDate().getParsedDate().toString());
        assertEquals("18:00", testEvent.getStartTime().getParsedTime().toString());
        assertEquals("20:00", testEvent.getEndTime().getParsedTime().toString());
        assertEquals("event", testEvent.getType());
    }

    @Test
    public void testSetters() {
        assertEquals(" ", testEvent.getStatusIcon());
        testEvent.setTaskDone();
        assertEquals("X", testEvent.getStatusIcon());
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] testing (at: Nov 08 2022 6:00 PM to 8:00 PM)";
        assertEquals(expected, testEvent.toString());
    }

}
