package unibook.logic.parser;

import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.logic.commands.ListCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_TYPE,
                            CliSyntax.PREFIX_MODULE);
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)) {
                return new ListCommand();
            }

            String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get();

            switch (option) {
            case "module":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            ListCommand.MESSAGE_USAGE));
                }

                ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get()
                    .toUpperCase());
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                    return new ListCommand(moduleCode, type);
                } else {
                    return new ListCommand(moduleCode);
                }
            case "type":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            ListCommand.MESSAGE_USAGE));
                }
                String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                if (type.equals("students") || type.equals("professors")) {
                    return new ListCommand(type);
                } else {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            ListCommand.MESSAGE_USAGE));
                }
            default:
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_USAGE));


            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        }

    }

}
