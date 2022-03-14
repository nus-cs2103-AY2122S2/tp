package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student/module/class group to TAssist\n"
            + "1. Adds a student: "
            + "Parameters: " + TYPE_STUDENT + " "
            + PREFIX_ID + "STUDENT_ID "
            + PREFIX_NAME + "NAME "
            + PREFIX_TELEGRAM + "PHONE_NUMBER "
            + PREFIX_EMAIL + "EMAIL "
            + "Example: " + COMMAND_WORD + " "
            + TYPE_STUDENT + " "
            + PREFIX_ID + "E0123456 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELEGRAM + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com\n"
            + "2. Adds a module: "
            + "Parameters: " + TYPE_MODULE + " "
            + PREFIX_NAME + "MODULE_NAME "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_ACADEMIC_YEAR + "ACADEMIC_YEAR "
            + "Example: " + COMMAND_WORD + " "
            + TYPE_MODULE + " "
            + PREFIX_NAME + "Software Engineering Project "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_ACADEMIC_YEAR + "21S1\n"
            + "3. Adds a class group: "
            + "Parameters: " + TYPE_CLASS + " "
            + PREFIX_ID + "CLASS_ID "
            + PREFIX_TYPE + "CLASS_TYPE "
            + PREFIX_MODULE_INDEX + "MODULE_INDEX "
            + "Example: " + COMMAND_WORD + " "
            + TYPE_CLASS + " "
            + PREFIX_ID + "T13 "
            + PREFIX_TYPE + "tutorial "
            + PREFIX_MODULE_INDEX + "1 ";

    public static final String MESSAGE_SUCCESS = "New entity added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This entity already exists in TAssist";

    private final Entity toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddCommand(Entity entity) {
        requireNonNull(entity);
        toAdd = entity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEntity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON); // TODO: Update Command Exception
        }

        model.addEntity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddCommand
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
