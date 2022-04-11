package seedu.address.logic.commands;

import static seedu.address.commons.util.MailUtil.launchMail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Opens the system default mail application to mail all the people in the contact list.
 */
public class MailAllCommand extends Command {

    public static final String COMMAND_WORD = "mail-all";

    public static final String DESKTOP_NOT_SUPPORTED_MESSAGE = "The desktop you are using is not supported";

    public static final String NO_EMAILS_SPECIFIED = "No email addresses specified.";

    public static final String URI_SYNTAX_ERROR_MESSAGE = "The URI syntax used is incorrect";

    private static final String MESSAGE_SUCCESS = "Opening app for bulk emailing";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": For Mailing everyone in the contact list.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the mail-all command.
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} object specifying the success message.
     * @throws CommandException if the command execution goes unexpected.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        String[] emailStrings = retrieveEmailStrings(model);

        try {
            launchMail(emailStrings);
        } catch (URISyntaxException uriSyntaxException) {
            throw new CommandException(URI_SYNTAX_ERROR_MESSAGE);
        } catch (IOException ioException) {
            throw new CommandException(DESKTOP_NOT_SUPPORTED_MESSAGE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Retrieves list of email strings.
     * @param model {@code Model} which the command should operate on.
     * @return The email string list.
     * @throws CommandException if the command execution goes unexpected.
     */
    public String[] retrieveEmailStrings(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        List<String> emails = lastShownList.stream()
                .map(Person::getEmail)
                .map(Email::toString)
                .distinct()
                .collect(Collectors.toList());

        if (emails.size() == 0) {
            throw new CommandException(NO_EMAILS_SPECIFIED);
        }

        String[] emailStrings = new String[emails.size()];
        return emails.toArray(emailStrings);
    }
}
