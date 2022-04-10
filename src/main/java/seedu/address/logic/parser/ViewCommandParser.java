package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ACTIVE_SCHEDULE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VIEW_DATE_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WITHOUT_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ACTIVE_SCHEDULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITHOUT_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_SCHEDULES;
import static seedu.address.model.person.Name.VALIDATION_REGEX;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.HeightOrWeightInRangePredicate;
import seedu.address.model.person.LineupNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ScheduleNameContainsKeywordsPredicate;
import seedu.address.model.schedule.ScheduleOnThisDatePredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    private static final String ALL_SCHEDULES = "all";
    private static final String ARCHIVED_SCHEDULES = "archive";
    private static final String VALID_HEIGHT_OPERATOR_REGEX =
            "^((gte|lte|gt|lt|eq)([1-9]|[1-9][0-9]|[1-2][0-9][0-9]|30[0-0]))$";
    private static final String VALID_WEIGHT_OPERATOR_REGEX =
            "^((gte|lte|gt|lt|eq)([1-9]|[1-9][0-9]|[1][0-9][0-9]|20[0-0]))$";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String NAME = "name";

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_LINEUP, PREFIX_SCHEDULE,
                PREFIX_ALL_SCHEDULE, PREFIX_WEIGHT, PREFIX_HEIGHT, PREFIX_TAG, PREFIX_WITHOUT_LINEUP, PREFIX_DATE);

        boolean hasPSlash = arePrefixesPresent(argMultimap, PREFIX_PLAYER); // P/
        boolean hasLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP); // L/
        boolean hasSSlash = arePrefixesPresent(argMultimap, PREFIX_SCHEDULE); // S/
        boolean hasBigNSlash = arePrefixesPresent(argMultimap, PREFIX_WITHOUT_LINEUP); // N/


        //impossible scenario (brute force)
        boolean hasLSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_PLAYER); // L/ P/
        boolean hasSSlashAndPSlash = arePrefixesPresent(argMultimap, PREFIX_SCHEDULE, PREFIX_PLAYER); // S/ P/
        boolean hasSSlashAndLSlash = arePrefixesPresent(argMultimap, PREFIX_LINEUP, PREFIX_SCHEDULE); // L/ S/
        boolean hasSSlashLSlashAndPSlash = arePrefixesPresent(argMultimap,
                PREFIX_LINEUP, PREFIX_PLAYER, PREFIX_SCHEDULE); // S/ L/ P/

        // all P/, L/ and S/ are missing -> false || L/, P/ or S/ coexist -> false
        if ((!hasLSlash && !hasPSlash && !hasSSlash) || hasLSlashAndPSlash || hasSSlashAndPSlash
                || hasSSlashAndLSlash || hasSSlashLSlashAndPSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // capture the type of view
        List<String> prefixAndArgument = new ArrayList<>();

        // store the predicate for all the predicate that applies to player
        List<Predicate<Person>> predicates = new ArrayList<>();

        // view P/[PLAYER_NAME...]
        if (hasPSlash) {
            return parseViewPlayer(argMultimap);
        }

        // view L/[LINEUP_NAME...]
        if (hasLSlash) {
            return parseViewLineup(argMultimap);
        }

        // view S/[SCHEDULE_NAME...]
        if (hasSSlash) {
            // capture the type of view
            prefixAndArgument.add(PREFIX_SCHEDULE.toString());

            // view S/ -> view all active schedules by default
            if (args.equals(" S/")) {
                return new ViewCommand(null, PREDICATE_SHOW_ACTIVE_SCHEDULES, prefixAndArgument);
            }

            boolean isViewAll = arePrefixesPresent(argMultimap, PREFIX_ALL_SCHEDULE)
                    && !arePrefixesPresent(argMultimap, PREFIX_DATE);

            boolean isViewDate = arePrefixesPresent(argMultimap, PREFIX_DATE)
                    && !arePrefixesPresent(argMultimap, PREFIX_ALL_SCHEDULE);

            System.out.println("isViewAll: " + isViewAll);
            System.out.println("isViewDate: " + isViewDate);
            // view all schedules
            if (isViewAll) {
                if (argMultimap.getValue(PREFIX_ALL_SCHEDULE).get().equals(ARCHIVED_SCHEDULES)) {
                    System.out.println("archived");
                    return new ViewCommand(null, PREDICATE_SHOW_ARCHIVED_SCHEDULES, prefixAndArgument);
                }
                if (argMultimap.getValue(PREFIX_ALL_SCHEDULE).get().equals(ALL_SCHEDULES)) {
                    System.out.println("all");
                    return new ViewCommand(null, PREDICATE_SHOW_ALL_SCHEDULES, prefixAndArgument);
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_ACTIVE_SCHEDULE_FORMAT,
                            ViewCommand.MESSAGE_ACTIVE_SCHEDULE_USAGE));
                }
            }

            if (isViewDate) {
                try {
                    LocalDate date = ParserUtil.parseScheduleDate(
                            argMultimap.getValue(PREFIX_DATE).get()
                    );
                    return new ViewCommand(null, new ScheduleOnThisDatePredicate(date), prefixAndArgument);
                } catch (DateTimeParseException e) {
                    throw new ParseException(String.format(MESSAGE_INVALID_VIEW_DATE_FORMAT,
                            ViewCommand.MESSAGE_VIEW_DATE_USAGE));
                }
            }

            // check has preamble. check here because if arg = "S/", preamble = "S/" as well
            if (!argMultimap.getPreamble().isEmpty() || hasBigNSlash) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewCommand.MESSAGE_USAGE_SCHEDULE));
            }

            // view S/[SCHEDULE_NAME...]
            String scheduleNameArg = argMultimap.getValue(PREFIX_SCHEDULE).get();
            if (!scheduleNameArg.equals("")) {
                if (!scheduleNameArg.matches(VALIDATION_REGEX)) {
                    throw new ParseException("You have provided an invalid name criteria!");
                }
                String trimmedArgs = scheduleNameArg.trim();
                prefixAndArgument.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new ViewCommand(null,
                        new ScheduleNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), prefixAndArgument);
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    /** Checks if the parameters for h/, w/ and player/lineup/schedule name is valid and returns the string */
    private static String parseRelationalOperator(String trimmedArgs, String regex) throws ParseException {
        String heightOrWeightOrName = "";
        if (trimmedArgs.matches(regex)) {
            return trimmedArgs;
        }
        switch (regex) {
        case VALID_HEIGHT_OPERATOR_REGEX:
            heightOrWeightOrName = HEIGHT;
            break;
        case VALID_WEIGHT_OPERATOR_REGEX:
            heightOrWeightOrName = WEIGHT;
            break;
        case VALIDATION_REGEX:
            heightOrWeightOrName = NAME;
            break;
        default:
            break;
        }
        throw new ParseException("Please check your parameter for " + heightOrWeightOrName + "!");
    }

    /** Returns ViewCommand when P/ is detected in the user input */
    private static ViewCommand parseViewPlayer(ArgumentMultimap argMultimap) throws ParseException {
        // capture the type of view
        List<String> prefixAndArg = new ArrayList<>();
        prefixAndArg.add(PREFIX_PLAYER.toString());

        // store the predicate for all the predicate that applies to player
        List<Predicate<Person>> predicates = new ArrayList<>();

        boolean hasBigNSlash = arePrefixesPresent(argMultimap, PREFIX_WITHOUT_LINEUP); // N/

        // check has preamble and corner case of having N/ in P/
        if (!argMultimap.getPreamble().isEmpty() || hasBigNSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE_PLAYER));
        }

        // player name
        if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
            parsePlayerFlag(argMultimap, predicates, prefixAndArg);
        }

        // height
        if (arePrefixesPresent(argMultimap, PREFIX_HEIGHT)) {
            parseHeightOrWeightFlag(argMultimap, predicates, prefixAndArg, PREFIX_HEIGHT);
        }

        // weight
        if (arePrefixesPresent(argMultimap, PREFIX_WEIGHT)) {
            parseHeightOrWeightFlag(argMultimap, predicates, prefixAndArg, PREFIX_WEIGHT);
        }

        // tag
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            parseTagFlag(argMultimap, predicates, prefixAndArg);
        }
        return new ViewCommand(predicates, null, prefixAndArg);
    }

    /** Returns ViewCommand when P/ is detected */
    private static void parsePlayerFlag(ArgumentMultimap argMultimap,
                                        List<Predicate<Person>> predicates,
                                        List<String> prefixAndArg) throws ParseException {
        String playerNameArg = argMultimap.getValue(PREFIX_PLAYER).get();
        if (playerNameArg.equals("")) {
            predicates.add(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            String[] nameKeywords = parseName(playerNameArg);
            prefixAndArg.add(playerNameArg);
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

    /** Extracts the keywords from P/, L/ or S/ parameter*/
    private static String[] parseName(String nameArg) throws ParseException {
        try {
            String trimmedArgs = parseRelationalOperator(nameArg.trim(), VALIDATION_REGEX);
            return trimmedArgs.split("\\s+");
        } catch (ParseException pe) {
            throw pe;
        }
    }

    /** Accumulates {@code Predicate<Person>} when h/ or w/ are detected */
    private static void parseHeightOrWeightFlag(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates,
                                        List<String> prefixAndArg, Prefix prefix) throws ParseException {
        String heightOrWeightArg = argMultimap.getValue(prefix).get();
        String parsedArg = "";
        prefixAndArg.add(heightOrWeightArg);
        if (prefix.equals(PREFIX_HEIGHT)) {
            parsedArg = parseHeightOrWeightArg(heightOrWeightArg, HEIGHT);
        } else if (prefix.equals(PREFIX_WEIGHT)) {
            parsedArg = parseHeightOrWeightArg(heightOrWeightArg, WEIGHT);
        }
        predicates.add(new HeightOrWeightInRangePredicate(parsedArg, prefix));
    }

    /** Returns the valid parameter for {@code Prefix} h/ or w/ */
    private static String parseHeightOrWeightArg(String arg, String heightOrWeight) throws ParseException {
        assert(heightOrWeight.equals(HEIGHT) || heightOrWeight.equals(WEIGHT));

        if (arg.equals("")) {
            throw new ParseException(String.format("You have provided an empty %s criteria!", heightOrWeight));
        }

        switch (heightOrWeight) {
        case HEIGHT:
            return parseRelationalOperator(arg.trim(), VALID_HEIGHT_OPERATOR_REGEX);
        case WEIGHT:
            return parseRelationalOperator(arg.trim(), VALID_WEIGHT_OPERATOR_REGEX);
        default: // unreachable
            return "";
        }
    }

    /** Accumulates {@code Predicate<Person>} when t/ is detected */
    private static void parseTagFlag(ArgumentMultimap argMultimap,
                                        List<Predicate<Person>> predicates,
                                        List<String> prefixAndArg) throws ParseException {
        String tagArg = argMultimap.getValue(PREFIX_TAG).get();
        prefixAndArg.add(tagArg);
        predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(parseTagArg(tagArg))));
    }

    /** Extracts keyword from t/ */
    private static String[] parseTagArg(String tagArg) throws ParseException {
        if (tagArg.matches(VALIDATION_REGEX)) {
            String trimmedArgs = tagArg.trim();
            return trimmedArgs.split("\\s+");
        } else if (tagArg.trim().equals("")) {
            throw new ParseException("You have provided an empty tag criteria!");
        } else {
            throw new ParseException("You have provided an invalid tag criteria!");
        }
    }

    /** Returns {@code ViewCommand} when L/ and its optional parameters and prefix is detected */
    private static ViewCommand parseViewLineup(ArgumentMultimap argMultimap) throws ParseException {
        // capture the type of view
        List<String> prefixAndArg = new ArrayList<>();
        prefixAndArg.add(PREFIX_LINEUP.toString());

        // store the predicate for all the predicate that applies to player
        List<Predicate<Person>> predicates = new ArrayList<>();

        String lineupNameArg = argMultimap.getValue(PREFIX_LINEUP).get();
        boolean hasBigNSlash = arePrefixesPresent(argMultimap, PREFIX_WITHOUT_LINEUP); // N/
        boolean hasNoLineup = lineupNameArg.equals("") && hasBigNSlash;

        // check has preamble
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE_LINEUP));
        }

        if (hasNoLineup) {
            return parseViewWithoutLineup(argMultimap, predicates, prefixAndArg);
        } else {
            return parseViewWithLineup(predicates, prefixAndArg, lineupNameArg);
        }
    }

    /** Returns {@code ViewCommand} when L/ and N/ are detected */
    private static ViewCommand parseViewWithoutLineup(ArgumentMultimap argMultimap,
                                                      List<Predicate<Person>> predicates,
                                                      List<String> prefixAndArg) throws ParseException {
        // when N/ follows with something
        if (!argMultimap.getValue(PREFIX_WITHOUT_LINEUP).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE_NO_LINEUP));
        }
        predicates.add(PREDICATE_SHOW_ALL_PERSONS_WITHOUT_LINEUP);
        prefixAndArg.add(PREFIX_WITHOUT_LINEUP.toString());
        return new ViewCommand(predicates, null, prefixAndArg);
    }

    /** Returns {@code ViewCommand} when L/ and/or parameter is detected */
    private static ViewCommand parseViewWithLineup(List<Predicate<Person>> predicates, List<String> prefixAndArg,
                                                   String lineupNameArg) throws ParseException {
        if (lineupNameArg.equals("")) {
            predicates.add(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP);
        } else {
            String[] nameKeywords = parseName(lineupNameArg);
            prefixAndArg.add(lineupNameArg);
            predicates.add(new LineupNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        return new ViewCommand(predicates, null, prefixAndArg);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
