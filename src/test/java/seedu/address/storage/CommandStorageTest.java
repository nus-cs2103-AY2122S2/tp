package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class CommandStorageTest {

    @Test
    public void constantlyPressUp_returnFirstString() {
        CommandStorage s = new CommandStorage();
        s.addCommand("First");
        s.addCommand("Second");
        s.addCommand("Third");

        for (int i = 0; i < 100; i++) {
            s.getPreviousCommand();
        }

        try {
            assertEquals(s.getCommand(0), s.getCurrentCommand());
        } catch (IllegalValueException err) {
            fail();
        }
    }


    @Test
    public void constantlyPressDown_returnLastString() {
        CommandStorage s = new CommandStorage();
        s.addCommand("First");
        s.addCommand("Second");
        s.addCommand("Third");

        for (int i = 0; i < new Random().nextInt(100); i++) {
            s.getPreviousCommand();
        }

        for (int i = 0; i < 100; i++) {
            s.getNextCommand();
        }

        try {
            assertEquals(s.getCommand(2), s.getCurrentCommand());
        } catch (IllegalValueException err) {
            fail();
        }
    }
}
