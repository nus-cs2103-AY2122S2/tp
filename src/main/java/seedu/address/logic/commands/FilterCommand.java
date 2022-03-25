package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.RemoveLabCommand.MESSAGE_LAB_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.StudentHasLabPredicate;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students with the specified lab number and lab status"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_LAB + "LAB_NUMBER "
            + PREFIX_LABSTATUS + "LAB_STATUS \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_LAB + "1" + " " + PREFIX_LABSTATUS + "s";

    private final StudentHasLabPredicate predicate;

    public FilterCommand(StudentHasLabPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Lab toFilter = predicate.getLab();
        if (!model.hasLab(toFilter)) {
            throw new CommandException(String.format(MESSAGE_LAB_NOT_FOUND, toFilter.labNumber));
        }
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FilterCommand
                && predicate.equals(((FilterCommand) other).predicate));
    }
}
