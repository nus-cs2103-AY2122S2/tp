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
                    CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_VIEW);

            //Change View command
            if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_VIEW)) {
                String view = argMultimap.getValue(CliSyntax.PREFIX_VIEW).get().toLowerCase();
                if (view.equals("modules")) {
                    return new ListCommand(ListCommand.ListView.MODULES);
                } else if (view.equals("people")) {
                    return new ListCommand(ListCommand.ListView.PEOPLE);
                } else {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_USAGE_VIEW));
                }
            }

            //List all (empty list command)
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)) {
                return new ListCommand();
            }

            String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get();

            switch (option) {
            case "module":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_MODULE_MISSING));
                }

                ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get()
                    .toUpperCase());

                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                    if (!(type.equals("professors") || type.equals("students"))) {
                        throw new ParseException(
                            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE_TYPE));
                    }
                    return new ListCommand(moduleCode, type);
                } else {
                    return new ListCommand(moduleCode);
                }
            case "type":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_TYPE_MISSING));
                }
                String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                if (!(type.equals("professors") || type.equals("students"))) {
                    throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE_TYPE));
                }
                return new ListCommand(type);
            default:
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListCommand.MESSAGE_USAGE_OPTION));

            }
        } catch (ParseException pe) {
            throw pe;
        }

    }

}
