package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.util.Pair;
import seedu.address.commons.core.datewithouttime.DateWithoutTime;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Procedure;

/**
 * Get all Procedures scheduled on a givenDate
 */
public class ListProcOnCommand extends Command {

    public static final String COMMAND_WORD = "listProcOn";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all Procedures to be done on a given date\n"
            + "Parameters: date (must be a valid date)\n"
            + "Example: " + COMMAND_WORD + " 02/02/2022";

    public static final String MESSAGE_LIST_PROC_SUCCESS = "Listing Procedures on requested date:\n%1$s";
    public static final String MESSAGE_NO_PROCEDURES = "Can't find any Procedure on requested date!";

    private final DateWithoutTime targetDate;

    public ListProcOnCommand(DateWithoutTime date) {
        this.targetDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Client> lastShownList = model.getFilteredClientList();
        List<Pair<Procedure, Client>> procedureClientPairList = new ArrayList<>();

        for (int i = 0; i < lastShownList.size(); i++) {
            Client currentClient = lastShownList.get(i);
            List<Procedure> clientProceduresOnDate = currentClient.getProcsOnDate(targetDate);
            clientProceduresOnDate.stream().map(Procedure -> new Pair(Procedure, currentClient))
                    .forEach(procedureClientPairList::add);
        }

        Collections.sort(procedureClientPairList, new Comparator<Pair<Procedure, Client>>() {
            @Override
            public int compare(Pair<Procedure, Client> p1, Pair<Procedure, Client> p2) {
                return p1.getKey().compareTo(p2.getKey());
            }
        });

        String resultString = composeResultString(procedureClientPairList);

        if (procedureClientPairList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_PROCEDURES);
        } else {
            return new CommandResult(String.format(MESSAGE_LIST_PROC_SUCCESS, resultString));
        }
    }

    private String composeResultString(List<Pair<Procedure, Client>> procedureClientPairList) {
        StringBuilder proceduresToStringBuilder = new StringBuilder();
        for (int i = 0; i < procedureClientPairList.size(); i++) {
            int index = i + 1;
            proceduresToStringBuilder.append(index + ". " + procedureClientPairList.get(i).getKey().toString() + "\n");
            proceduresToStringBuilder.append(procedureClientPairList.get(i).getValue().getName().toString()
                    + ", located at " + procedureClientPairList.get(i).getValue().getAddress().toString() + "\n");
        }
        return proceduresToStringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListProcOnCommand)) {
            return false;
        }

        // state check
        ListProcOnCommand otherCommand = (ListProcOnCommand) other;
        return targetDate.equals(otherCommand.targetDate);
    }
}
