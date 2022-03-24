package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LineupNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_LINEUP);

        boolean hasPSlash = arePrefixesPresent(argMultimap, PREFIX_PLAYER); // P/
        boolean hasLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP); // L/

        //impossible scenario
        boolean hasLSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_PLAYER); // L/ P/

        // both P/ and L/ are missing -> false or L/ and P/ coexist -> false
        if ((!hasLSlash && !hasPSlash) || hasLSlashAndPSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // view P/[PLAYER_NAME...]
        if (hasPSlash) {
            // capture the type of view
            List<String> prefixAndArgument = new ArrayList<>();
            prefixAndArgument.add(PREFIX_PLAYER.toString());

            // view P/ --> view all player
            if (args.equals(" P/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS, prefixAndArgument);
            }

            // check has preamble. check here because if arg = "P/", preamble = "P/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // view P/[PLAYER_NAME...]
            String playerNameArg = argMultimap.getValue(PREFIX_PLAYER).get();
            if (!playerNameArg.equals("")) {
                String trimmedArgs = playerNameArg.trim();
                prefixAndArgument.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        prefixAndArgument);
            }
        }

        // view L/[LINEUP_NAME...]
        if (hasLSlash) {
            List<String> prefixAndArgument = new ArrayList<>();
            prefixAndArgument.add(PREFIX_LINEUP.toString());
            // view L/ -> view all lineups
            if (args.equals(" L/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP, prefixAndArgument);
            }

            // check has preamble. check here because arg = "L/", preamble = "L/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // view L/[LINEUP_NAME...]
            String lineupNameArg = argMultimap.getValue(PREFIX_LINEUP).get();
            if (!lineupNameArg.equals("")) {
                String trimmedArgs = lineupNameArg.trim();
                prefixAndArgument.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(new LineupNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        prefixAndArgument);
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
