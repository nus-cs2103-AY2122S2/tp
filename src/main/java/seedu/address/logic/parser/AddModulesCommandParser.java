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
import seedu.address.logic.commands.AddModulesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

/**
 * Parses input arguments and creates a new AddModulesCommand object
 */
public class AddModulesCommandParser implements Parser<AddModulesCommand> {

    public static final String MESSAGE_EMPTY = "You did not input modules to be added.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddModulesCommand
     * and returns an AddModulesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddModulesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModulesCommand.MESSAGE_USAGE), pe);
        }

        List<Module> modules = new ArrayList<>(parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULE)));

        return new AddModulesCommand(index, modules);
    }

    /**
     * @param modules Collection of Modules in string form to be parsed
     * @return Set of parsed Modules if @param modules not empty, else Empty Set
     */
    private Set<Module> parseModulesForEdit(Collection<String> modules) throws ParseException {
        assert modules != null;

        if (modules.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY);
        }

        Collection<String> moduleSet = modules.size() == 1 && modules.contains("") ? Collections.emptySet() : modules;
        return ParserUtil.parseModules(moduleSet);
    }
}
