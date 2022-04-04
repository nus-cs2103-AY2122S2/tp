package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteModulesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteModulesCommandParser implements Parser<DeleteModulesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public static final String MESSAGE_EMPTY = "You did not input modules to be deleted.";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModulesCommand
     * and returns an DeleteModulesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteModulesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        Index index;
        List<Module> modules = new ArrayList<>();
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModulesCommand.MESSAGE_USAGE), pe);
        }

        modules.addAll(parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULE)));
        if (modules.isEmpty()) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }

        return new DeleteModulesCommand(index, modules);
    }

    private Set<Module> parseModulesForEdit(Collection<String> modules) throws ParseException {
        assert modules != null;

        if (modules.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY);
        }

        Collection<String> moduleSet = modules.size() == 1 && modules.contains("") ? Collections.emptySet() : modules;
        return ParserUtil.parseModules(moduleSet);
    }
}
