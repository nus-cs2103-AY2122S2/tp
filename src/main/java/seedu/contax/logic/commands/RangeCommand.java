package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.ArgumentMultimap;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;

/**
 * Range edit or delete a person identified using it's displayed from index and to index from the address book.
 */
public class RangeCommand extends Command {
    public static final String COMMAND_WORD = "range";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": command in range"
            + "by the index number used in the displayed person list. "
            + "Parameters: from/FROM_INDEX to/TO_INDEX (must be a positive integer) "
            + "Example command range edit and range delete ";

    public static final String MESSAGE_EDIT_USAGE = COMMAND_WORD + ": Edits the details of the person in range"
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Parameters: from/FROM_INDEX to/TO_INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_RANGE_FROM + "1"
            + PREFIX_RANGE_TO + "3";

    private final Index fromIndex;
    private final Index toIndex;
    private final ArgumentMultimap argMultimap;
    private final String commandInput;
    /**
     * @param fromIndex              of the person in the filtered person list to edit
     * @param toIndex                of the person in the filtered person list to edit
     * @param argMultimap            details to argMultimap
     * @param commandInput            details to word of command
     */
    public RangeCommand(Index fromIndex, Index toIndex, ArgumentMultimap argMultimap, String commandInput) {
        requireNonNull(fromIndex);
        requireNonNull(toIndex);
        requireNonNull(argMultimap);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.argMultimap = argMultimap;
        this.commandInput = commandInput.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (fromIndex.getZeroBased() > toIndex.getZeroBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        List<CommandResult> commandResultList = new ArrayList<>();
        for (int i = fromIndex.getOneBased(); i <= toIndex.getOneBased(); i++) {
            AddressBookParser addressBookParser = new AddressBookParser();
            try {
                String commandText = createNewCommand(commandInput, Integer.toString(i));
                if (argMultimap.getPreamble().equals("delete")) {
                    commandText = createNewCommand(commandInput, Integer.toString(fromIndex.getOneBased()));
                }
                Command command = addressBookParser.parseCommand(commandText);
                commandResultList.add(command.execute(model));
            } catch (ParseException pe) {
                return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            }
        }
        StringBuilder resultOutput = new StringBuilder();
        for (CommandResult result: commandResultList) {
            resultOutput.append(result.getFeedbackToUser()).append("\n");
        }
        return new CommandResult(resultOutput.toString());
    }

    private String createNewCommand(String commandInput, String index) {
        StringBuilder output = new StringBuilder();
        String[] splitCommand = commandInput.trim().split(" ");
        output.append(splitCommand[0]).append(" ").append(index).append(" ");
        for (int i = 1; i < splitCommand.length; i++) {
            output.append(" ").append(splitCommand[i]);
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RangeCommand // instanceof handles nulls
                && fromIndex.equals(((RangeCommand) other).fromIndex)
                && toIndex.equals(((RangeCommand) other).toIndex)
                && commandInput.equals(((RangeCommand) other).commandInput)
            );
    }
}
