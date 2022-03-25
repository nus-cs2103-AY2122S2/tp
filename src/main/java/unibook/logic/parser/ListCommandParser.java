package unibook.logic.parser;

import java.lang.reflect.InvocationTargetException;
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
                    CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_VIEW, CliSyntax.PREFIX_GROUP);

            //List all (empty list command)
            if (args.strip().equals("")) {
                return new ListCommand();
            }

            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION)) {
                //Compulsory option field not present
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListCommand.MESSAGE_USAGE));
            }
            String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get();

            switch (option) {
            case "view":
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_VIEW)) {
                    String view = argMultimap.getValue(CliSyntax.PREFIX_VIEW).get().toLowerCase();
                    if (view.equals("modules")) {
                        //Change to module view
                        return new ListCommand(ListCommand.ListView.MODULES, ListCommand.ListCommandType.VIEW);
                    } else if (view.equals("people")) {
                        //Change to people view
                        return new ListCommand(ListCommand.ListView.PEOPLE, ListCommand.ListCommandType.VIEW);
                    } else {
                        //Invalid view argument given
                        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                Messages.MESSAGE_INVALID_VIEW));
                    }
                } else {
                    //View type argument not present
                    throw new ParseException(
                            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                    Messages.MESSAGE_VIEW_FIELD_MISSING));
                }
            case "module":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
                    //module argument not present
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            Messages.MESSAGE_MODULE_FIELD_MISSING));
                }
                String moduleCode = argMultimap.getValue(CliSyntax.PREFIX_MODULE).get().toUpperCase();
                if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    //list module with specific type, e.g. list o/module m/cs2103 ty/professors
                    String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                    if (!(type.equals("professors") || type.equals("students"))) {
                        throw new ParseException(
                            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                    Messages.MESSAGE_WRONG_TYPE));
                    }
                    return new ListCommand(moduleCode, type, ListCommand.ListCommandType.MODULEANDTYPE);
                } else {
                    //list module only e.g. list o/module m/cs2103
                    return new ListCommand(moduleCode, ListCommand.ListCommandType.MODULE);
                }
            case "type":
                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TYPE)) {
                    //type argument not present
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_TYPE_FIELD_MISSING));
                }
                String type = argMultimap.getValue(CliSyntax.PREFIX_TYPE).get().toLowerCase();
                if (!(type.equals("professors") || type.equals("students"))) {
                    //invalid type argument
                    throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_WRONG_TYPE));
                }
                return new ListCommand(type, ListCommand.ListCommandType.TYPE);
            case "group":

                if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP)) {
                    throw new ParseException(
                            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                    Messages.MESSAGE_GROUP_FIELD_MISSING));
                }
                String group = argMultimap.getValue(CliSyntax.PREFIX_GROUP).get().toLowerCase();
                return new ListCommand(group, ListCommand.ListCommandType.GROUP);

            default:
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_INVALID_LIST_OPTION));

            }
        } catch (ParseException pe) {
            throw pe;
        }

    }

}
