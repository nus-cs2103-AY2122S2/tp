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
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.ParserUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;
import seedu.contax.model.person.Person;

//@@author HanJiyao
/**
 * Range edit or delete a person identified using it's displayed from index and to index from the address book.
 */
public class RangeCommand extends Command {
    public static final String COMMAND_WORD = "range";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Perform command in range "
            + "by the index number used in the displayed person list.**"
            + "\nParameters: *"
            + "COMMAND (must be valid command without index) "
            + PREFIX_RANGE_FROM + "FROM "
            + PREFIX_RANGE_TO + "TO *\n"
            + "Example: `" + COMMAND_WORD + " editperson "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_ADDRESS + "New Address "
            + PREFIX_RANGE_FROM + "1 "
            + PREFIX_RANGE_TO + "3 `";

    private final Logger logger = LogsCenter.getLogger(RangeCommand.class);

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
        Person restorePerson = model.getFilteredPersonList().get(toIndex.getZeroBased());
        for (int i = toIndex.getOneBased(); i >= fromIndex.getOneBased(); i--) {
            AddressBookParser addressBookParser = new AddressBookParser();

            try {
                String commandText = ParserUtil.parseAndCreateNewCommand(commandInput, Integer.toString(i));

                if (!commandText.startsWith(EditPersonCommand.COMMAND_WORD)
                        && !commandText.startsWith(DeletePersonCommand.COMMAND_WORD)) {
                    throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            RangeCommand.MESSAGE_USAGE));
                }

                logger.info("----------------[RANGE COMMAND][" + commandText + "]");
                Command command = addressBookParser.parseCommand(commandText);

                try {
                    commandResultList.add(command.execute(model));
                } catch (CommandException ce) {
                    commandResultList.clear(); // only for the purpose of not changing output in feature freeze
                    // special case: duplicated name editing, restore to previous name
                    if (fromIndex.getZeroBased() != toIndex.getZeroBased()
                            && commandText.startsWith(EditPersonCommand.COMMAND_WORD)) {
                        model.setPerson(model.getFilteredPersonList().get(toIndex.getZeroBased()), restorePerson);
                    }
                    commandResultList.add(new CommandResult(ce.getMessage()));
                    break;
                }
            } catch (ParseException pe) {
                throw new CommandException(pe.getMessage());
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
