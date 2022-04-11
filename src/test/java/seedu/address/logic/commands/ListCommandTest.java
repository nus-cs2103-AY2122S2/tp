package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ListCommandTest {

    //TODO: check using models and user assertCommandSuccess

    @Test
    public void equalityCheck() {
        ListCommand lc1 = new ListCommand();
        ListCommand lc2 = new ListCommand();
        assertEquals(lc1, lc2);
    }
}
