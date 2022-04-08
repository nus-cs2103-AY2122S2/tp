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
import seedu.address.model.person.HeightContainsKeywordsPredicate;
import seedu.address.model.person.LineupNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.WeightContainsKeywordsPredicate;
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
            prefixAndArgument.add(PREFIX_LINEUP.toString());
            // view L/ -> view all lineups
            if (args.equals(" L/")) {
                predicates.add(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP);
                return new ViewCommand(predicates, null, prefixAndArgument);
            }

            // check has preamble. check here because arg = "L/", preamble = "L/" as well
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewCommand.MESSAGE_USAGE_LINEUP));
            }

            // view L/[LINEUP_NAME...]
            String lineupNameArg = argMultimap.getValue(PREFIX_LINEUP).get();
            if (!lineupNameArg.equals("")) {
                if (!lineupNameArg.matches(VALIDATION_REGEX)) {
                    throw new ParseException("You have provided an invalid name criteria!");
                }
                String trimmedArgs = lineupNameArg.trim();
                prefixAndArgument.add(trimmedArgs);
                String[] nameKeywords = trimmedArgs.split("\\s+");
                predicates.add(new LineupNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
                return new ViewCommand(predicates, null, prefixAndArgument);
            } else {
                // view L/ N/ -> view all players without lineup
                if (hasBigNSlash) {
                    if (!argMultimap.getValue(PREFIX_WITHOUT_LINEUP).get().equals("")) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                ViewCommand.MESSAGE_USAGE_NO_LINEUP));
                    }
                    predicates.add(PREDICATE_SHOW_ALL_PERSONS_WITHOUT_LINEUP);
                    prefixAndArgument.add(PREFIX_WITHOUT_LINEUP.toString());
                    return new ViewCommand(predicates, null, prefixAndArgument);
                }
            }
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

    private static String parseRelationalOperator(String trimmedArgs, String regex) throws ParseException {
        String heightOrWeightOrName = "";
        if (trimmedArgs.matches(regex)) {
            return trimmedArgs;
        }
        switch (regex) {
        case VALID_HEIGHT_OPERATOR_REGEX:
            heightOrWeightOrName = "height";
            break;
        case VALID_WEIGHT_OPERATOR_REGEX:
            heightOrWeightOrName = "weight";
            break;
        case VALIDATION_REGEX:
            heightOrWeightOrName = "name";
            break;
        }
        throw new ParseException("Please check your parameter for " + heightOrWeightOrName + "!");
    }

    private static ViewCommand parseViewPlayer(ArgumentMultimap argMultimap) throws ParseException {
        // capture the type of view
        List<String> prefixAndArg = new ArrayList<>();

        // store the predicate for all the predicate that applies to player
        List<Predicate<Person>> predicates = new ArrayList<>();

        // capture the type of view
        prefixAndArg.add(PREFIX_PLAYER.toString());

        boolean hasBigNSlash = arePrefixesPresent(argMultimap, PREFIX_WITHOUT_LINEUP); // N/

        // check has preamble and corner case of having N/ in P/
        if (!argMultimap.getPreamble().isEmpty() || hasBigNSlash) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE_PLAYER));
        }

        // player name
        if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
            String playerNameArg = argMultimap.getValue(PREFIX_PLAYER).get();
            if (playerNameArg.equals("")) {
                predicates.add(PREDICATE_SHOW_ALL_PERSONS);
            } else {
                String[] nameKeywords = parseName(playerNameArg);
                prefixAndArg.add(playerNameArg);
                predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            }
        }

        // height
        if (arePrefixesPresent(argMultimap, PREFIX_HEIGHT)) {
            String heightArg = argMultimap.getValue(PREFIX_HEIGHT).get();
            prefixAndArg.add(heightArg);
            predicates.add(new HeightContainsKeywordsPredicate(parseHeightArg(heightArg)));
        }

        // weight
        if (arePrefixesPresent(argMultimap, PREFIX_WEIGHT)) {
            String weightArg = argMultimap.getValue(PREFIX_WEIGHT).get();
            prefixAndArg.add(weightArg);
            predicates.add(new WeightContainsKeywordsPredicate(parseWeightArg(weightArg)));
        }

        // tag
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            String tagArg = argMultimap.getValue(PREFIX_TAG).get();
            prefixAndArg.add(tagArg);
            predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(parseTagArg(tagArg))));
        }
        return new ViewCommand(predicates, null, prefixAndArg);
    }

    private static String[] parseName(String NameArg) throws ParseException {
        try {
            String trimmedArgs = parseRelationalOperator(NameArg.trim(), VALIDATION_REGEX);
            return trimmedArgs.split("\\s+");
        } catch (ParseException pe) {
            throw pe;
        }
    }

    private static String parseHeightArg(String heightArg) throws ParseException {
        if (!heightArg.equals("")) {
            return parseRelationalOperator(heightArg.trim(), VALID_HEIGHT_OPERATOR_REGEX);
        }
        throw new ParseException("You have provided an empty height criteria!");
    }

    private static String parseWeightArg(String weightArg) throws ParseException {
        if (!weightArg.equals("")) {
            return parseRelationalOperator(weightArg.trim(), VALID_WEIGHT_OPERATOR_REGEX);
        } else {
            throw new ParseException("You have provided an empty weight criteria!");
        }
    }

    private static String[] parseTagArg(String tagArg) throws ParseException {
        if (!tagArg.equals("")) {
            String trimmedArgs = tagArg.trim();
            return trimmedArgs.split("\\s+");
        } else {
            throw new ParseException("You have provided an empty tag criteria!");
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
