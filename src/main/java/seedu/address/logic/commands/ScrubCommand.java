package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ScrubPersonPredicate;


/**
 * Command that encapsulates the logic of removing contacts that matches the conditions specified by the given
 * predicate.
 */
public class ScrubCommand extends Command {
    public static final String COMMAND_WORD = "scrub";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person if that person have the phone number/tag/email(domain) specified by the "
            + "user.\n"
            + "Parameters: "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_EMAIL + "domain]..." + "\n"
            + "Examples: " + COMMAND_WORD + " p/90400201 p/90400202";

    public static final String MESSAGE_SCRUB_SUCCESS = "Successfully scrubbed %s person";
    public static final String MESSAGE_WRONG_DOMAIN_FORMAT = "Email scrubbing allows only domain name as a parameter."
            + "\nFor example: @domain or @domain.com";
    public static final String NO_VALID_PREFIX = "At least 1 valid prefix must be provided!";

    private final ScrubPersonPredicate predicate;

    public ScrubCommand(ScrubPersonPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the ScrubCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of the execution.
     * @throws CommandException If an error occurs during the execution of the ScrubCommand.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int numberOfDeletedPerson = removePersonThatMatchesDescription(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS); // To show all currently available person
        model.saveAddressBookState();
        if (numberOfDeletedPerson == 0) {
            return new CommandResult(MESSAGE_EMPTY_SEARCH);
        }
        return new CommandResult(String.format(MESSAGE_SCRUB_SUCCESS, numberOfDeletedPerson));
    }

    /**
     * Removes any person from the address book that matches the description described by the {@link #predicate}.
     *
     * @param model {@code Model} which the deletion should happen in.
     * @return Number of person that has been deleted from the address book.
     */
    private int removePersonThatMatchesDescription(Model model) {
        model.updateFilteredPersonList(predicate);
        ObservableList<Person> personsToDelete = model.getFilteredPersonList();
        int numberOfDeletedPerson = personsToDelete.size();
        model.deleteAllPerson(personsToDelete);
        return numberOfDeletedPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScrubCommand // instanceof handles nulls
                && predicate.equals(((ScrubCommand) other).predicate)); // state check
    }
}
