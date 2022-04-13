package seedu.tinner.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tinner.model.CompanyList;
import seedu.tinner.model.Model;

/**
 * Clears the company list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Company list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCompanyList(new CompanyList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
