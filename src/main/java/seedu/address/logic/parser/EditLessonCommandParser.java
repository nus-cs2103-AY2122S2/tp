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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;

public class EditLessonCommandParser implements Parser<EditLessonCommand> {

    private Optional<LessonName> setLessonName(ArgumentMultimap argMultimap, EditLessonDescriptor e) throws ParseException {
        return argMultimap.getValue(PREFIX_LESSON_NAME).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get()));
    }

    private Optional<Subject> getSubject(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_SUBJECT).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));

    }

    private Optional<LessonAddress> getLessonAddress(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_LESSON_ADDRESS).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseLessonAddress(argMultimap.getValue(PREFIX_LESSON_ADDRESS).get()));
    }

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

    private Optional<LocalDate> getDate(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_DATE).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
    }

    private Optional<LocalTime> getTime(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(PREFIX_START_TIME).isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get()));
    }

    @Override
    public EditLessonCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_LESSON, PREFIX_LESSON_NAME,
                PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS, PREFIX_DATE, PREFIX_START_TIME,
                PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES);
        if (CheckPrefixes.arePrefixesAbsent(argMultimap, PREFIX_LESSON) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        Index lessonId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LESSON).get());
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        if (!argMultimap.getValue(PREFIX_LESSON_NAME).isEmpty()) {
            editLessonDescriptor.setName(ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get()));
        }
        if (!argMultimap.getValue(PREFIX_SUBJECT).isEmpty()) {
            editLessonDescriptor.setSubject(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        if (!argMultimap.getValue(PREFIX_LESSON_ADDRESS).isEmpty()) {
            editLessonDescriptor.setAddress(
                    ParserUtil.parseLessonAddress(argMultimap.getValue(PREFIX_LESSON_ADDRESS).get()));
        }
        ParserUtil.checkDurationIsValid(
                getDurationHours(argMultimap).orElse(0), getDurationMinutes(argMultimap).orElse(0));

        if (!argMultimap.getValue(PREFIX_DURATION_HOURS).isEmpty()) {
            editLessonDescriptor.setDurationHours(
                    ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get()));
        }
        if (!argMultimap.getValue(PREFIX_DURATION_MINUTES).isEmpty()) {
            editLessonDescriptor.setDurationMinutes(
                    ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get()));
        }
        if (!argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            editLessonDescriptor.setStartDate(
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (!argMultimap.getValue(PREFIX_START_TIME).isEmpty()) {
            editLessonDescriptor.setStartTime(
                    ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }

        return new EditLessonCommand(lessonId, editLessonDescriptor);
    }
}
