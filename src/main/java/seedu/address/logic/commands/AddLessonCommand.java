package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.logic.commands.misc.ViewTab;
import seedu.address.model.Model;
import seedu.address.model.lesson.ConflictingLessonsPredicate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.exceptions.ConflictsWithLessonsException;

public class AddLessonCommand extends Command {

    public static final String COMMAND_WORD = "addlesson";
    public static final String SHORTENED_COMMAND_WORD = "al";
    public static final String COMMAND_DESCRIPTION = "Add a lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the schedule"
            + "\n"
            + "Parameters: [" + PREFIX_RECURRING + "] "
            + PREFIX_LESSON_NAME + " NAME "
            + PREFIX_SUBJECT + " SUBJECT "
            + PREFIX_LESSON_ADDRESS + " ADDRESS "
            + "\n     "
            + PREFIX_DATE + " DATE "
            + PREFIX_START_TIME + " START_TIME "
            + "\n     "
            + PREFIX_DURATION_HOURS + " DURATION IN HOURS "
            + PREFIX_DURATION_MINUTES + " DURATION IN MINUTES "
            + "\n     "
            + "Example: "
            + "\n     "
            + COMMAND_WORD + " "
            + PREFIX_LESSON_NAME + " Sec 2 Biology Group Tuition "
            + PREFIX_SUBJECT + " Biology "
            + "\n     "
            + PREFIX_LESSON_ADDRESS + " Blk 11 Ang Mo Kio Street 74, #11-04 "
            + "\n     "
            + PREFIX_DATE + " 19-12-2022 "
            + PREFIX_START_TIME + " 18:00 "
            + PREFIX_DURATION_HOURS + " 2 "
            + PREFIX_DURATION_MINUTES + " 15 ";

    public static final String MESSAGE_SUCCESS = "Okay, added this lesson to your schedule!";
    public static final String MESSAGE_CONFLICTING_LESSONS_TIP = "TIP: you can use \"rmlesson\" to"
            + " remove these conflicting lessons";

    private final Lesson toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.addLesson(toAdd);
        } catch (ConflictsWithLessonsException e) {
            model.updateFilteredLessonList(new ConflictingLessonsPredicate(toAdd));

            throw new CommandException(
                    ConflictsWithLessonsException.ERROR_MESSAGE + "\n\n"
                            + MESSAGE_CONFLICTING_LESSONS_TIP, ViewTab.LESSON
            );
        }

        model.setSelectedLesson(toAdd);
        return new CommandResult(MESSAGE_SUCCESS, InfoPanelTypes.LESSON, ViewTab.LESSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLessonCommand // instanceof handles nulls
                && toAdd.equals(((AddLessonCommand) other).toAdd));
    }
}
