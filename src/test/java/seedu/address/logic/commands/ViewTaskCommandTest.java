package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class ViewTaskCommandTest {

    @Test
    public void equals() {
        final ViewTaskCommand standardCommand = new ViewTaskCommand( new GroupBuilder()
                .withGroupName("NUS Fintech Society").build());

        // same value --> returns true
        ViewTaskCommand commandWithSameValues = new ViewTaskCommand(new GroupBuilder()
                .withGroupName("NUS Fintech Society").build());
    }

}
