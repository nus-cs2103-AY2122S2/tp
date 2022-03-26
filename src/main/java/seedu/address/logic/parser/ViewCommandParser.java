package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LineupNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.schedule.ScheduleNameContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_LINEUP, PREFIX_SCHEDULE);

        boolean hasPSlash = arePrefixesPresent(argMultimap, PREFIX_PLAYER); // P/
        boolean hasLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP); // L/
        boolean hasSSlash = arePrefixesPresent(argMultimap, PREFIX_SCHEDULE); // P/

        //impossible scenario (brute force)
        boolean hasLSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_PLAYER); // L/ P/
        boolean hasSSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_SCHEDULE, PREFIX_PLAYER); // S/ P/
        boolean hasSSlashAndLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_SCHEDULE); // L/ S/
        boolean hasSSlashLSlashAndPSlash = arePrefixesPresent(argMultimap,
                PREFIX_LINEUP, PREFIX_PLAYER, PREFIX_SCHEDULE); // S/ L/ P/

        // both P/ and L/ are missing -> false or L/ and P/ coexist -> false
        if ((!hasLSlash && !hasPSlash && !hasSSlash) || hasLSlashAndPSlash || hasSSlashAndPSlash
                || hasSSlashAndLSlash || hasSSlashLSlashAndPSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // view P/[PLAYER_NAME...]
        if (hasPSlash) {
            // capture the type of view
            List<String> prefixAndArgument = new ArrayList<>();
            prefixAndArgument.add(PREFIX_PLAYER.toString());

            // view P/ --> view all player
            if (args.equals(" P/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS, null, prefixAndArgument);
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
                return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), null,
                        prefixAndArgument);
            }
        }

        // view L/[LINEUP_NAME...]
        if (hasLSlash) {
            List<String> prefixAndArgument = new ArrayList<>();
            prefixAndArgument.add(PREFIX_LINEUP.toString());
            // view L/ -> view all lineups
            if (args.equals(" L/")) {
                return new ViewCommand(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP, null, prefixAndArgument);
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
                return new ViewCommand(new LineupNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), null,
                        prefixAndArgument);
            }
        }

        // view S/[SCHEDULE_NAME...]
        if (hasSSlash) {
            // capture the type of view
            List<String> prefixAndArgument = new ArrayList<>();
            prefixAndArgument.add(PREFIX_SCHEDULE.toString());

            // view P/ --> view all player
            if (args.equals(" S/")) {
                return new ViewCommand(null, PREDICATE_SHOW_ALL_SCHEDULES, prefixAndArgument);
            }

            // check has preamble. check here because if arg = "S/", preamble = "S/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // view S/[SCHEDULE_NAME...]
            String scheduleNameArg = argMultimap.getValue(PREFIX_SCHEDULE).get();
            if (!scheduleNameArg.equals("")) {
                String trimmedArgs = scheduleNameArg.trim();
                prefixAndArgument.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(null, new ScheduleNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        prefixAndArgument);
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
