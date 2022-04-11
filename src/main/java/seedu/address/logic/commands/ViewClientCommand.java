package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

public class ViewClientCommand extends Command {

    public static final String COMMAND_WORD = "viewClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays details of the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing client: %1$s";

    private final Index index;


    /**
     * @param index of the client in the filtered client list to view
     */
    public ViewClientCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getClientList();

        if (model.hasNoClients()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "view"));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(index.getZeroBased());
        model.updateDisplayedClient(clientToView);
        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToView.getName()), false, false, false, false,
                false, index, null);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewClientCommand)) {
            return false;
        }

        ViewClientCommand otherVc = (ViewClientCommand) other;

        return index.equals(otherVc.index);
    }
}
