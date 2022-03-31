package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import manageezpz.commons.core.Messages;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Email;
import manageezpz.model.person.Name;
import manageezpz.model.person.PersonMultiplePredicate;
import manageezpz.model.person.Phone;

/**
 * The command to find employees based on the multiple properties given.
 */
public class FindEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "findEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all employees that contains the properties specified.\n"
            + "Employee's properties:\n"
            + PREFIX_NAME.getPrefix()
            + "NAMES: Finds all employees which has their name contain any of the words in NAMES\n"
            + PREFIX_PHONE.getPrefix()
            + "PHONE NUMBER: Find employees with the exact phone number\n"
            + PREFIX_EMAIL.getPrefix()
            + "EMAIL: Finds employees with the exact email"
            + "Example:\n"
            + COMMAND_WORD + " " + PREFIX_NAME.getPrefix() + "Alex Yeoh\n"
            + COMMAND_WORD + " " + PREFIX_PHONE.getPrefix() + "62226222\n"
            + COMMAND_WORD + " " + PREFIX_EMAIL.getPrefix() + "alexyeo@google.com\n"
            + COMMAND_WORD + " " + PREFIX_NAME.getPrefix() + "Benson Chua " + PREFIX_PHONE.getPrefix() + "6123456 "
            + PREFIX_EMAIL.getPrefix() + "bensonc@gmail.com";

    public static final String NO_OPTIONS = COMMAND_WORD + " needs at least 1 valid options\n";

    public static final String INVALID_NAME = Name.MESSAGE_CONSTRAINTS + "\n";

    public static final String INVALID_PHONE = Phone.MESSAGE_CONSTRAINTS + "\n";

    public static final String INVALID_EMAIL = Email.MESSAGE_CONSTRAINTS + "\n";

    private final PersonMultiplePredicate predicate;

    /**
     * Constructor for the find employee command.
     * @param predicate The predicate with multiple search terms to search for employees
     */
    public FindEmployeeCommand(PersonMultiplePredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int latestNumberOfEmployees = model.getFilteredPersonList().size();
        String commandMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, latestNumberOfEmployees);
        return new CommandResult(commandMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindEmployeeCommand) {
            Predicate otherPredicate = ((FindEmployeeCommand) obj).predicate;
            boolean isOtherPredicateEqual = predicate.equals(otherPredicate);
            return isOtherPredicateEqual;
        }
        return false;
    }
}
