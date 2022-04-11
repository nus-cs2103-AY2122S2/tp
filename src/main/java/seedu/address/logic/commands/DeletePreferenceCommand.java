package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.InvalidPreferenceKeyException;


public class DeletePreferenceCommand extends Command {

    public static final String COMMAND_WORD = "deletePref";
    public static final String MESSAGE_SUCCESS = "Preference deleted for %s: [%s]";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a preference from the client specified "
            + "by the index number used in the displayed client list and the specified preference key.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "" + PREFIX_PREFERENCE_KEY + "PREFERENCE_KEY\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PREFERENCE_KEY + "Drink";

    public static final String MESSAGE_INVALID_KEY = "There is no existing preference matching the key %s for "
            + "this client: %s";

    private final Index index;
    private final String preferenceKey;

    /**
     * Creates a DeletePreferenceCommand to delete the specified {@code preferenceKey}
     */
    public DeletePreferenceCommand(Index index, String preferenceKey) {
        requireNonNull(index);
        requireNonNull(preferenceKey);
        this.index = index;
        this.preferenceKey = preferenceKey;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getClientList();

        if (model.hasNoClients()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "delete preference from"));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToDeletePreference = lastShownList.get(index.getZeroBased());

        try {
            clientToDeletePreference.deletePreference(preferenceKey);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    clientToDeletePreference.getName().toString(), preferenceKey), false, false, false,
                    false, false, null, clientToDeletePreference);
        } catch (InvalidPreferenceKeyException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_KEY, preferenceKey,
                    clientToDeletePreference.getName()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePreferenceCommand
                && index.equals(((DeletePreferenceCommand) other).index)
                && preferenceKey.equals(((DeletePreferenceCommand) other).preferenceKey));
    }
}
