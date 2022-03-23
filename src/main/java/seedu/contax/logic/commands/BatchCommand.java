package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_END_WITH;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EQUALS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_START_WITH;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.LogicManager;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.ArgumentMultimap;
import seedu.contax.logic.parser.ParserUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;
import seedu.contax.model.person.Person;
import seedu.contax.model.util.SearchType;




/**
 * Batch edit or delete a person identified using base on string =/ provided.
 */
public class BatchCommand extends Command {

    public static final String COMMAND_WORD = "batch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Perform command in batch \n"
            + "Parameters: "
            + "COMMAND (must be valid command without index) "
            + PREFIX_SEARCH_TYPE + "SEARCH_TYPE "
            + PREFIX_EQUALS + "VALUE \n"
            + "Example: " + COMMAND_WORD + " edit "
            + PREFIX_ADDRESS + "new address "
            + PREFIX_SEARCH_TYPE + "phone "
            + PREFIX_EQUALS + "123 ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final String commandInput;
    private final SearchType searchType;
    private final ArgumentMultimap userInput;

    /**
     * Creates an BatchCommand base on specified {@code commandInput} {@code searchType} and {@code userInput}.
     *
     * @param commandInput              details to word of command
     * @param searchType                search type of field apply matcher
     * @param userInput                 regex provided by user
     */
    public BatchCommand(String commandInput, SearchType searchType, ArgumentMultimap userInput) {
        requireNonNull(commandInput);
        requireNonNull(searchType);
        requireNonNull(userInput);
        this.commandInput = commandInput;
        this.searchType = searchType;
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String userValue = "";
        List<Index> indexList = new ArrayList<>();
        if (userInput.getValue(PREFIX_EQUALS).isPresent()) {
            userValue = userInput.getValue(PREFIX_EQUALS).get();
            indexList = matchInputStringToIndex(model, searchType, userValue, "=");
        } else if (userInput.getValue(PREFIX_START_WITH).isPresent()) {
            userValue = userInput.getValue(PREFIX_START_WITH).get();
            indexList = matchInputStringToIndex(model, searchType, userValue, "^");
        } else if (userInput.getValue(PREFIX_END_WITH).isPresent()) {
            userValue = userInput.getValue(PREFIX_END_WITH).get();
            indexList = matchInputStringToIndex(model, searchType, userValue, "$");
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }
        if (indexList.size() == 0) {
            return new CommandResult(COMMAND_WORD
                    + ": No result matching \"" + userValue + "\"");
        }
        List<CommandResult> commandResultList = new ArrayList<>();
        for (Index index: indexList) {
            AddressBookParser addressBookParser = new AddressBookParser();
            try {
                String commandText = ParserUtil.parseAndCreateNewCommand(
                        commandInput, Integer.toString(index.getOneBased()));
                logger.info("----------------[BATCH COMMAND][" + commandText + "]");
                Command command = addressBookParser.parseCommand(commandText);
                commandResultList.add(command.execute(model));
            } catch (ParseException pe) {
                throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
            }
        }
        StringBuilder resultOutput = new StringBuilder();
        for (CommandResult result: commandResultList) {
            resultOutput.append(result.getFeedbackToUser()).append("\n");
        }
        return new CommandResult(resultOutput.toString());
    }

    private List<Index> matchInputStringToIndex(Model model, SearchType searchType, String userInput, String matchType)
            throws CommandException {
        List<Index> indexList = new ArrayList<>();
        List<Person> personList = model.getFilteredPersonList();
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            Index index = Index.fromZeroBased(i);

            String targetField;
            switch (searchType.searchType) {
            case SearchType.TYPE_PHONE:
                targetField = person.getPhone().toString();
                break;
            case SearchType.TYPE_EMAIL:
                targetField = person.getEmail().toString();
                break;
            case SearchType.TYPE_ADDRESS:
                targetField = person.getAddress().toString();
                break;
            default:
                targetField = person.getName().toString();
                break;
            }
            if (!targetField.isEmpty()) {
                if (matchType.equals("=") && targetField.equals(userInput)) {
                    indexList.add(index);
                } else if (matchType.equals("^") && targetField.startsWith(userInput)) {
                    indexList.add(index);
                } else if (matchType.equals("$") && targetField.endsWith(userInput)) {
                    indexList.add(index);
                }
            }
        }
        return indexList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BatchCommand // instanceof handles nulls
                && commandInput.equals(((BatchCommand) other).commandInput)
                && searchType.equals(((BatchCommand) other).searchType)
                && userInput.getValue(PREFIX_EQUALS)
                .equals(((BatchCommand) other).userInput.getValue(PREFIX_EQUALS))
                && userInput.getValue(PREFIX_START_WITH)
                .equals(((BatchCommand) other).userInput.getValue(PREFIX_START_WITH))
                && userInput.getValue(PREFIX_END_WITH)
                .equals(((BatchCommand) other).userInput.getValue(PREFIX_END_WITH)));
    }
}
