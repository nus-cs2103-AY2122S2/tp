package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class DeadlineListTest {
    @Test
    public void listFormat_returnsStringArray() {
        String expected = "a: 16/07/2028\nd: 18/10/2025\nf: 27/10/2024\n";
        assertEquals(ALICE.getDeadlines().listFormat(), expected);
    }
}
