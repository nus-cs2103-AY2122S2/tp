package seedu.address.logic.commands.applicant;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class TabApplicant extends Command {
    public static final String COMMAND_WORD = "tabapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to Applicant Tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TABAPPLICANT_MESSAGE = "Switched to Applicant Tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_TABAPPLICANT_MESSAGE, true, false, true);
    }
}
