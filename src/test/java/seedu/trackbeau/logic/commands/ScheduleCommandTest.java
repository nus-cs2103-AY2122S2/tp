package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.ui.Panel;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCustomerCommand}.
 */
public class ScheduleCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void execute_validDate_noCustomerFound() {
        LocalDate expectedDate = LocalDate.parse("10-10-2022", formatter);
        expectedModel.setSelectedDate(expectedDate);
        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SUCCESS, expectedDate.format(formatter));

        ScheduleCommand command = new ScheduleCommand(expectedDate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, Panel.SCHEDULE_PANEL);
    }
}
