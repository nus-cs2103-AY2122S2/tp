package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.LessonBook;
import seedu.address.model.Model;
import seedu.address.model.StudentBook;

/**
 * Clears the student book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = "Clear the entire lesson and student book";
    public static final String SHORTENED_COMMAND_WORD = "";
    public static final String MESSAGE_USAGE = "To confirm clearing, please enter \"clear -f\" in the command box.";
    public static final String MESSAGE_SUCCESS = "TeachWhat! has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLessonBook(new LessonBook());
        model.setStudentBook(new StudentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
