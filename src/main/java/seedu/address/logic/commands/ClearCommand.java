package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the student book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = "Clears the entire lesson book and student book.";
    public static final String MESSAGE_USAGE = "To confirm clearing, please enter \"clear -f\" in the command box.";
    public static final String MESSAGE_SUCCESS = "TeachWhat! has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
//        model.
//        model.setStudentBook(new StudentBook());
        System.out.println("Clear command executed!");
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
