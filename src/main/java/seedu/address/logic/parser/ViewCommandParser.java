package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;


import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.team.TeamName;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_TEAM, PREFIX_LINEUP);
//        boolean hasPreamblePlayer = !argMultimapForPlayer.getPreamble().isEmpty();
        //String s1 = argMultimapForPlayer.getPreamble();
//        boolean hasPreambleTeam = !argMultimapForPlayer.getPreamble().isEmpty();
        //String s2 = argMultimapForPlayer.getPreamble();
//        boolean hasPreambleTeamAndLineup = !argMultimapForPlayer.getPreamble().isEmpty();
        //String s3 = argMultimapForPlayer.getPreamble();
        boolean hasTSlash = arePrefixesPresent(argMultimap, PREFIX_TEAM); // T/
        boolean hasTSlashAndLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_TEAM); // T/ L/
        boolean hasPSlash = arePrefixesPresent(argMultimap, PREFIX_PLAYER); // P/

        //brute force other scenario
        boolean hasTSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_PLAYER, PREFIX_TEAM); // T/ P/
        boolean hasLSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_PLAYER); // L/ P/
        boolean hasTSlashAndLSlashAndPSlash = arePrefixesPresent(argMultimap,
                PREFIX_LINEUP, PREFIX_TEAM, PREFIX_PLAYER); // T/ L/ P/


        Name name = new Name("Player");
        TeamName teamName = new TeamName("Team");
        LineupName lineupName = new LineupName("Lineup");
        String playerNameArg = "empty";
        String teamNameArg = "empty";
        String lineupNameArg = "empty";

        // both P/ and T/ are missing -> false, P/ and T/, L/ and T/ and T/,L/ and P/ cannot coexist
        if ((!hasTSlash && !hasPSlash) || hasTSlashAndLSlashAndPSlash
                || hasLSlashAndPSlash || hasTSlashAndPSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // view P/[PLAYER_NAME]
        if (hasPSlash) {
            // view P/
            if (args.equals("P/")) {
                // view P/
            }

            // view P/[PLAYER_NAME]
            playerNameArg = argMultimap.getValue(PREFIX_PLAYER).get();
            if (!playerNameArg.equals("")) {
                name = ParserUtil.parseName(playerNameArg);
            }
        }

        // viewing T/[TEAM_NAME]
        if (hasTSlash) {
            // view T/
            if (args.equals("T/")) {
                // view T/
            }

            // view T/TEAM_NAME
            teamNameArg = argMultimap.getValue(PREFIX_TEAM).get();
            if (!teamNameArg.equals("")) {
                teamName = ParserUtil.parseTeamName(teamNameArg);
            }
        }

        // view T/TEAM_NAME L/[LINEUP_NAME]
        if (hasTSlashAndLSlash) {
            teamNameArg = argMultimap.getValue(PREFIX_TEAM).get();
            lineupNameArg = argMultimap.getValue(PREFIX_LINEUP).get();

            // view T/TEAM_NAME L/
            if (!teamNameArg.equals("") && lineupNameArg.equals("")) {
                // view T/TEAM_NAME L/
                teamName = ParserUtil.parseTeamName(teamNameArg);
            }

            // view T/TEAM_NAME L/LINEUP_NAME
            if (!teamNameArg.equals("") && !lineupNameArg.equals("")) {
                // view T/TEAM_NAME L/LINEUP_NAME
                teamName = ParserUtil.parseTeamName(teamNameArg);
                lineupName = ParserUtil.parseLineupName(lineupNameArg);
            }
        }

        return new ViewCommand("PlayerName: " + name + "\nTeamName: " + teamName + "\nLineupName: " + lineupName);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
