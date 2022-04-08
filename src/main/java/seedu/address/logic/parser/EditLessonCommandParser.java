package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_NO_INDEX_OR_PREFIX_PROVIDED;
import static seedu.address.logic.commands.EditLessonCommand.EditLessonDescriptor;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_LESSON_NAME,
                PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS, PREFIX_DATE, PREFIX_START_TIME,
                PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES);

        Index lessonId;

        try {
            lessonId = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_NO_INDEX_OR_PREFIX_PROVIDED,
                    EditLessonCommand.MESSAGE_USAGE));
        }

        EditLessonDescriptor editLessonDescriptor = createEditLessonDescriptor(argMultimap);
        return new EditLessonCommand(lessonId, editLessonDescriptor);
    }

    private EditLessonDescriptor createEditLessonDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        setLessonName(editLessonDescriptor, argMultimap);
        setSubject(editLessonDescriptor, argMultimap);
        setAddress(editLessonDescriptor, argMultimap);
        setHours(editLessonDescriptor, argMultimap);
        setMinutes(editLessonDescriptor, argMultimap);
        setStartDate(editLessonDescriptor, argMultimap);
        setStartTime(editLessonDescriptor, argMultimap);

        return editLessonDescriptor;
    }

    /**
     * Parses and sets the LessonName field in a {@code EditLessonDescriptor} from the provided arguments.
     * LessonName must be provided in the arguments.
     */
    private void setLessonName(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isNamePrefixPresent(argMultimap)) {
            String nameInString = argMultimap.getValue(PREFIX_LESSON_NAME).get();
            LessonName name = ParserUtil.parseLessonName(nameInString);
            editLessonDes.setName(name);
        }
    }

    /**
     * Parses and sets the Subject field in a {@code EditLessonDescriptor} from the provided arguments.
     * Subject must be provided in the arguments.
     */
    private void setSubject(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isSubjectPrefixPresent(argMultimap)) {
            String subjectInString = argMultimap.getValue(PREFIX_SUBJECT).get();
            Subject subject = ParserUtil.parseSubject(subjectInString);
            editLessonDes.setSubject(subject);
        }
    }

    /**
     * Parses and sets the Address field in a {@code EditLessonDescriptor} from the provided arguments.
     * Address must be provided in the arguments.
     */
    private void setAddress(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isAddressPrefixPresent(argMultimap)) {
            String addressInString = argMultimap.getValue(PREFIX_LESSON_ADDRESS).get();
            LessonAddress address = ParserUtil.parseLessonAddress(addressInString);
            editLessonDes.setAddress(address);
        }
    }

    /**
     * Parses and sets the StartDate field in a {@code EditLessonDescriptor} from the provided arguments.
     * StartDate must be provided in the arguments.
     */
    private void setStartDate(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isStartDatePrefixPresent(argMultimap)) {
            String dateInString = argMultimap.getValue(PREFIX_DATE).get();
            LocalDate date = ParserUtil.parseDate(dateInString);
            editLessonDes.setStartDate(date);
        }
    }

    /**
     * Parses and sets the StartTime field in a {@code EditLessonDescriptor} from the provided arguments.
     * StartTime must be provided in the arguments.
     */
    private void setStartTime(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isStartTimePrefixPresent(argMultimap)) {
            String timeInString = argMultimap.getValue(PREFIX_START_TIME).get();
            LocalTime time = ParserUtil.parseStartTime(timeInString);
            editLessonDes.setStartTime(time);
        }
    }

    /**
     * Parses and sets the DurationHours field in a {@code EditLessonDescriptor} from the provided arguments.
     * DurationHours must be provided in the arguments.
     */
    private void setHours(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isDurationHoursPrefixPresent(argMultimap)) {
            String hoursInString = argMultimap.getValue(PREFIX_DURATION_HOURS).get();
            int hours = ParserUtil.parseDurationHours(hoursInString);
            editLessonDes.setDurationHours(hours);
        }
    }

    /**
     * Parses and sets the DurationMinutes field in a {@code EditLessonDescriptor} from the provided arguments.
     * DurationMinutes must be provided in the arguments.
     */
    private void setMinutes(EditLessonDescriptor editLessonDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isDurationMinutesPrefixPresent(argMultimap)) {
            String minutesInString = argMultimap.getValue(PREFIX_DURATION_MINUTES).get();
            int minutes = ParserUtil.parseDurationMinutes(minutesInString);
            editLessonDes.setDurationMinutes(minutes);
        }
    }

    private boolean isNamePrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_LESSON_NAME).isPresent();
    }

    private boolean isSubjectPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_SUBJECT).isPresent();
    }

    private boolean isAddressPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_LESSON_ADDRESS).isPresent();
    }

    private boolean isDurationHoursPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_DURATION_HOURS).isPresent();
    }

    private boolean isDurationMinutesPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_DURATION_MINUTES).isPresent();
    }

    private boolean isStartDatePrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_DATE).isPresent();
    }

    private boolean isStartTimePrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_START_TIME).isPresent();
    }
}
