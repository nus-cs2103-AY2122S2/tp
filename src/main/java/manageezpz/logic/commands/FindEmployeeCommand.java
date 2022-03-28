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
    public static final String PERSON_PROPERTIES = String.join(", ", PREFIX_NAME.getPrefix(),
            PREFIX_EMAIL.getPrefix(), PREFIX_PHONE.getPrefix());
    public static final String NOTE = "";
    public static final String EXAMPLE = "";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all employees that contains the properties specified.\n"
            + "Employee's properties " + PERSON_PROPERTIES + "\n"
            + NOTE + "\n"
            + EXAMPLE;
    public static final String INVALID_NAME = Name.MESSAGE_CONSTRAINTS + "\n";
    public static final String INVALD_PHONE = Phone.MESSAGE_CONSTRAINTS + "\n";
    public static final String INVALID_EMAIL = Email.MESSAGE_CONSTRAINTS + "\n";

    private PersonMultiplePredicate predicate;

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
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
            return predicate.equals(otherPredicate);
        }
        return false;
    }
}
