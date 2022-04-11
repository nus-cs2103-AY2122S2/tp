package seedu.address.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.exceptions.DuplicatePolicyException;


public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_SUCCESS = "New policy added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the client identified "
            + "by the index number used in the displayed client list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_POLICY_MANAGER + "POLICY_MANAGER] "
            + "[" + PREFIX_PREMIUM + "PREMIUM]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Investment Plan "
            + PREFIX_COMPANY + "AAM Advisory "
            + PREFIX_POLICY_MANAGER + "Me "
            + PREFIX_PREMIUM + "15";

    private final Index index;
    private final Policy policyToAdd;

    /**
     * Creates an AddPolicyCommand to add the specified {@code Policy} to the {@code Client} specified by {@code Index}
     */
    public AddPolicyCommand(Index index, Policy policy) {
        requireNonNull(index);
        requireNonNull(policy);
        this.index = index;
        this.policyToAdd = policy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getClientList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "add policy to"));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToAddPolicy = lastShownList.get(index.getZeroBased());
        Client updatedClient;
        try {
            updatedClient = clientToAddPolicy.addPolicy(policyToAdd);
        } catch (DuplicatePolicyException e) {
            throw new CommandException(e.getMessage());
        }
        model.setClient(clientToAddPolicy, updatedClient);
        model.updateDisplayedClient(clientToAddPolicy);
        return new CommandResult(String.format(MESSAGE_SUCCESS, policyToAdd), false, false, false,
                false, false, null, updatedClient);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPolicyCommand // instanceof handles nulls
                && policyToAdd.equals(((AddPolicyCommand) other).policyToAdd)
                && index.equals(((AddPolicyCommand) other).index));
    }
}
