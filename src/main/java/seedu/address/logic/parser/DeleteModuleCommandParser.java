package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new DeleteModuleCommand object
 */
public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModuleCommand
     * and returns a DeleteModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public DeleteModuleCommand parse(String args) throws ParseException {
        DeleteModuleCommand deleteModuleCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE) // did not supply module code or preamble is not empty
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
        } else {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
            String[] modCodeKeywords = moduleCode.toString().split("\\s+");
            deleteModuleCommand = new DeleteModuleCommand(
                    new ModuleCodeContainsKeywordsPredicate(Arrays.asList(modCodeKeywords)));
        }
        return deleteModuleCommand;
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
