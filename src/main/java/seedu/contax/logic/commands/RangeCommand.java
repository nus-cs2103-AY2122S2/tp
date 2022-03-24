package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.LogicManager;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.ParserUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;

/**
 * Range edit or delete a person identified using it's displayed from index and to index from the address book.
 */
public class RangeCommand extends Command {
    public static final String COMMAND_WORD = "range";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Perform command in range"
            + "by the index number used in the displayed person list. \n"
            + "Parameters: "
            + "COMMAND (must be valid command without index) "
            + PREFIX_RANGE_FROM + "FROM "
            + PREFIX_RANGE_TO + "TO \n"
            + "Example: " + COMMAND_WORD + " edit "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_ADDRESS + "new address ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Index fromIndex;
    private final Index toIndex;
    private final String commandInput;
    /**
     * @param fromIndex              of the person in the filtered person list to edit
     * @param toIndex                of the person in the filtered person list to edit
     * @param commandInput           details to word of command
     */
    public RangeCommand(Index fromIndex, Index toIndex, String commandInput) {
        requireNonNull(fromIndex);
        requireNonNull(toIndex);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.commandInput = commandInput.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (fromIndex.getZeroBased() > toIndex.getZeroBased()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        List<CommandResult> commandResultList = new ArrayList<>();
        for (int i = toIndex.getOneBased(); i >= fromIndex.getOneBased(); i--) {
            AddressBookParser addressBookParser = new AddressBookParser();
            try {
                String commandText = ParserUtil.parseAndCreateNewCommand(commandInput, Integer.toString(i));

                logger.info("----------------[RANGE COMMAND][" + commandText + "]");

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
