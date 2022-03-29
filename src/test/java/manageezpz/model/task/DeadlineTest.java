package manageezpz.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private final Deadline testDealine = new Deadline(new Description("testing"),
            new Date("2022-11-08"), new Time("1800"));
    @Test
    public void testGetters() {
        assertEquals("testing", testDealine.getDescription().toString());
        assertEquals("2022-11-08", testDealine.getDate().getParsedDate().toString());
        assertEquals("18:00", testDealine.getTime().getParsedTime().toString());
        assertEquals("deadline", testDealine.getType());
    }

    @Test
    public void testSetters() {
        assertEquals(" ", testDealine.getStatusIcon());
        testDealine.setTaskDone();
        assertEquals("X", testDealine.getStatusIcon());
    }

    @Test
    public void testToString() {
        String expected = "[D][ ] testing (by: Nov 08 2022 6:00 PM)";
        assertEquals(expected, testDealine.toString());
    }

}
