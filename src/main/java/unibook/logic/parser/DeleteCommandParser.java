package unibook.logic.parser;

import java.util.Locale;
import java.util.stream.Stream;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.DeleteCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;

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

        try {

            // if no params provided case
            if (args.trim().length() == 0) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));
            }

            // delete index case
            if (Character.isDigit(args.trim().charAt(0))) {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);

            } else { // delete m/MODULECODE case
                ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_MODULE,
                            CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_GROUP);

                boolean moduleCodePresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE);
                boolean optionPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OPTION);
                boolean groupPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_GROUP);

                boolean moduleCodeOnly = moduleCodePresent && !optionPresent && !groupPresent;
                boolean moduleCodeAndOptionOnly = moduleCodePresent && optionPresent && !groupPresent;
                boolean moduleCodeAndGroupOnly = moduleCodePresent && !optionPresent && groupPresent;

                if (moduleCodeOnly) {
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(
                            argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                    return new DeleteCommand(moduleCode);

                } else if (moduleCodeAndOptionOnly) {
                    String option = argMultimap.getValue(CliSyntax.PREFIX_OPTION).get().toLowerCase();
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(
                            argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                    return new DeleteCommand(moduleCode, option);

                } else if (moduleCodeAndGroupOnly) {
                    ModuleCode moduleCode = ParserUtil.parseModuleCode(
                            argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                    Group group = ParserUtil.parseGroup(argMultimap.getValue(CliSyntax.PREFIX_GROUP).get());
                    return new DeleteCommand(moduleCode, group);
                }

                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));
            }

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
