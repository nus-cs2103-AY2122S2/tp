package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditLessonCommand.EditLessonDescriptor;
import static seedu.address.logic.commands.EditLessonCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditLessonCommandParser implements Parser<EditLessonCommand> {

    private Optional<Integer> getDurationHours(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_DURATION_HOURS).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get()));
    }

    private Optional<Integer> getDurationMinutes(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_DURATION_MINUTES).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get()));
    }

    @Override
    public EditLessonCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_LESSON, PREFIX_LESSON_NAME,
                PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS, PREFIX_DATE, PREFIX_START_TIME,
                PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES);
        if (argMultimap.getValue(PREFIX_LESSON).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        Index lessonId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LESSON).get());
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        if (argMultimap.getValue(PREFIX_LESSON_NAME).isPresent()) {
            editLessonDescriptor.setName(ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            editLessonDescriptor.setSubject(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        if (argMultimap.getValue(PREFIX_LESSON_ADDRESS).isPresent()) {
            editLessonDescriptor.setAddress(
                    ParserUtil.parseLessonAddress(argMultimap.getValue(PREFIX_LESSON_ADDRESS).get()));
        }

        if (argMultimap.getValue(PREFIX_DURATION_HOURS).isPresent()) {
            ParserUtil.checkDurationIsValid(
                    getDurationHours(argMultimap).orElse(0), getDurationMinutes(argMultimap).orElse(0));
            editLessonDescriptor.setDurationHours(
                    ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION_MINUTES).isPresent()) {
            ParserUtil.checkDurationIsValid(
                    getDurationHours(argMultimap).orElse(0), getDurationMinutes(argMultimap).orElse(0));
            editLessonDescriptor.setDurationMinutes(
                    ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editLessonDescriptor.setStartDate(
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editLessonDescriptor.setStartTime(
                    ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }

        return new EditLessonCommand(lessonId, editLessonDescriptor);
    }
}
