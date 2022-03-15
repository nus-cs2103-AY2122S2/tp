package unibook.logic.parser;

import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.DeleteCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        // Delete command has 2 formats
        // delete m/MODULECODE and delete index (assumed to be for person list only)
        try {

            System.out.println(args);

            // delete index case
            if (Character.isDigit(args.trim().charAt(0))) {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);

            } else { // delete m/MODULECODE case
                ArgumentMultimap argMultimap =
                        ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_MODULE);

                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)
                        || !argMultimap.getPreamble().isEmpty()) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteCommand.MESSAGE_USAGE));
                }

                ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());

                return new DeleteCommand(moduleCode);
            }

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
