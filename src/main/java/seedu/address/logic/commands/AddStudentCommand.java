package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Adds a student to the student book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addstudent";
    public static final String SHORTENED_COMMAND_WORD = "as";
    public static final String COMMAND_DESCRIPTION = "Add a student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the student book. "
            + "Parameters: "
            + PREFIX_STUDENT_NAME + " NAME "
            + PREFIX_STUDENT_PHONE + " PHONE "
            + PREFIX_STUDENT_EMAIL + " EMAIL "
            + PREFIX_STUDENT_ADDRESS + " ADDRESS "
            + "[" + PREFIX_STUDENT_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NAME + " John Doe "
            + PREFIX_STUDENT_PHONE + " 98765432 "
            + PREFIX_STUDENT_EMAIL + " johnd@example.com "
            + PREFIX_STUDENT_ADDRESS + " 311, Clementi Ave 2, #02-25 "
            + PREFIX_STUDENT_TAG + " friends "
            + PREFIX_STUDENT_TAG + " owesMoney";


    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student book";

    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        model.setSelectedStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), true, InfoPanelTypes.STUDENT, ViewTab.STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
