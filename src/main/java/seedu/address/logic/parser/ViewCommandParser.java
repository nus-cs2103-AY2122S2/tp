package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
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

        //brute force other scenario
        boolean hasLSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_PLAYER); // L/ P/

        Name name = new Name("Player");
        LineupName lineupName = new LineupName("Lineup");
        String playerNameArg = "empty";
        String lineupNameArg = "empty";

        // both P/ and L/ are missing -> false, L/ and P/ cannot coexist
        if ((!hasLSlash && !hasPSlash) || hasLSlashAndPSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // view P/[PLAYER_NAME...]
        if (hasPSlash) {
            // view P/ --> view all player
            List<String> keywords = new ArrayList<>();
            keywords.add(PREFIX_PLAYER.toString());
            if (args.equals("P/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS, keywords);
            }

            // check has preamble. check here because arg = "P/", preamble = "P/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // view P/[PLAYER_NAME...]
            playerNameArg = argMultimap.getValue(PREFIX_PLAYER).get();
            if (!playerNameArg.equals("")) {
                name = ParserUtil.parseName(playerNameArg);
                String trimmedArgs = name.toString().trim();
                keywords.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), keywords);
            }
        }

        // view L/[LINEUP_NAME...]
        if (hasLSlash) {
            List<String> keywords = new ArrayList<>();
            keywords.add(PREFIX_LINEUP.toString());
            // view L/ -> view all lineups
            if (args.equals("L/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS, keywords); // should be SHOW_ALL_LINEUP
            }

            // check has preamble. check here because arg = "L/", preamble = "L/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // view L/[LINEUP_NAME...]
            lineupNameArg = argMultimap.getValue(PREFIX_LINEUP).get();
            if (!playerNameArg.equals("")) {
                lineupName = ParserUtil.parseLineupName(lineupNameArg);
                String trimmedArgs = lineupName.toString().trim();
                keywords.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), keywords);
            }
        }

        return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS, new ArrayList<>());
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
