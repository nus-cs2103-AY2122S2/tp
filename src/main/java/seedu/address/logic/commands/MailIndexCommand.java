package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Opens the system default mail app with the person
 * identified by the index specified by the user.
 */
public class MailIndexCommand extends Command {

    public static final String COMMAND_WORD = "mail-index";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the mailbox to email the person identified by the "
            + "index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer, greater than or equals to 1)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MAIL_PERSON_SUCCESS = "Opening System Default Mail Application : %1$s";

    public static final String URI_SYNTAX_ERROR_MESSAGE = "The URI syntax used is incorrect";

    public static final String DESKTOP_NOT_SUPPORTED_MESSAGE = "The desktop you are using is not supported";

    private final Index targetIndex;

    /**
     * Constructor for the {@code MailIndexCommand} class.
     * @param targetIndex The index of the person in the contact list.
     */
    public MailIndexCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the mail-index command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} object that contains the result of the execution.
     * @throws CommandException if any noncompliance occurs in the command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEmail = lastShownList.get(targetIndex.getZeroBased());

        try {
            launchMail(personToEmail.getEmail().toString());
        } catch (URISyntaxException uriSyntaxException) {
            throw new CommandException(URI_SYNTAX_ERROR_MESSAGE);
        } catch (IOException ioException) {
            throw new CommandException(DESKTOP_NOT_SUPPORTED_MESSAGE);
        }

        return new CommandResult(String.format(MESSAGE_MAIL_PERSON_SUCCESS, personToEmail.getEmail()));
    }

    /**
     * Checks whether two {@code MailIndexCommand} objects are equal.
     * @param other The {@code MailIndexCommand} to check equality against.
     * @return The boolean value specifying whether the two objects are equal or not.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MailIndexCommand// instanceof handles nulls
                && targetIndex.equals(((MailIndexCommand) other).targetIndex)); // state check
    }
}
