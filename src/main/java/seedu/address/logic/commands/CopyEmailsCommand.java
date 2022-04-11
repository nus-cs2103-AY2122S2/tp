package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copies the emails of the currently displayed list of people to clipboard.
 */
public class CopyEmailsCommand extends Command {
    public static final String COMMAND_WORD = "copyemails";
    public static final String EMAIL_LIST_SEPARATOR = "; ";
    public static final String MESSAGE_EMPTY_EMAIL_LIST = "There are no emails to copy!";
    public static final String MESSAGE_COPY_EMAILS_FAILURE = "Unable to copy to clipboard.";
    public static final String MESSAGE_COPY_EMAILS_SUCCESS = "Successfully copied \"%s\" to clipboard!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies a comma-separated list of all displayed emails to clipboard.\n"
            + "Example: " + COMMAND_WORD;

    private static final Logger LOGGER = Logger.getLogger(CopyEmailsCommand.class.getName());

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.log(Level.INFO, "Executing CopyEmailsCommand#execute(Model)");
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String emailString = getAllEmails(lastShownList);
        if (emailString.length() == 0) {
            LOGGER.log(Level.INFO, "Empty email list");
            throw new CommandException(MESSAGE_EMPTY_EMAIL_LIST);
        }

        try {
            StringSelection stringSelection = new StringSelection(emailString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            String message = String.format(MESSAGE_COPY_EMAILS_SUCCESS, emailString);

            LOGGER.log(Level.INFO, "CopyEmailsCommand#execute(Model) success");
            return new CommandResult(message);
        } catch (IllegalStateException e) {
            LOGGER.log(Level.INFO, "Failed to copy emails to clipboard");
            throw new CommandException(MESSAGE_COPY_EMAILS_FAILURE);
        }
    }

    /**
     * Extracts the emails from a list of {@link seedu.address.model.person.Person Persons}
     *
     * @param lastShownList A list of {@link seedu.address.model.person.Person Person} objects.
     * @return A {@code EMAIL_LIST_SEPARATOR}-separated list of emails.
     */
    private String getAllEmails(List<Person> lastShownList) {
        StringBuilder emailString = new StringBuilder();
        for (Person person: lastShownList) {
            if (emailString.length() != 0) {
                emailString.append(EMAIL_LIST_SEPARATOR);
            }
            emailString.append(person.getEmail().toString());
        }
        return emailString.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CopyEmailsCommand; // All CopyEmailsCommand are identical
    }
}
