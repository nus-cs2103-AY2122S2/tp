package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NONEXISTENT_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Person;

/**
 * Sort list of persons by name.
 */
public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sortedp";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD + ": Sorts list of persons by name " 
            + "and display them in ascending order of alphabets.\n ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.showPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
