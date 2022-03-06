package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

public class AddLessonCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the schedule."
            + "Parameters: "
            + PREFIX_LESSON_NAME + " NAME "
            + PREFIX_SUBJECT + " SUBJECT "
            + PREFIX_DATE + " DATE "
            + PREFIX_START_TIME + " START_TIME "
            + PREFIX_DURATION_HOURS + " DURATION IN HOURS "
            + PREFIX_DURATION_MINUTES + " DURATION IN MINUTES "
            + PREFIX_RECURRING + " (optional) "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Sec 2 Biology Group Tuition "
            + PREFIX_SUBJECT + " Biology "
            + PREFIX_DATE + "19-3-2022 "
            + PREFIX_START_TIME + " 1800 "
            + PREFIX_DURATION_HOURS + " 2 "
            + PREFIX_DURATION_MINUTES + " 15 "
            + PREFIX_RECURRING;

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_CONFLICTING_LESSON = "This lesson conflicts with an existing lesson "
        + "in the schedule";

    private final Lesson toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasConflictingLesson(toAdd)) {
            throw new CommandException(MESSAGE_CONFLICTING_LESSON);
        }

        model.addLesson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLessonCommand // instanceof handles nulls
                && toAdd.equals(((AddLessonCommand) other).toAdd));
    }
}
