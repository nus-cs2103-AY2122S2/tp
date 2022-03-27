package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.appointment.DateRangePredicate;
import seedu.contax.model.chrono.TimeRange;

/**
 * Lists all empty slots in the schedule of a minimum length within a period.
 */
public class FreeBetweenCommand extends Command {

    public static final String COMMAND_WORD = "freebetween";
    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`"
            + ": **Lists all empty slots in the schedule of a minimum length within a period.** "
            + "Parameters: *"
            + "[" + PREFIX_DATE_START + "STARTDATE] "
            + "[" + PREFIX_TIME_START + "STARTTIME] "
            + "[" + PREFIX_DATE_END + "ENDDATE "
            + "[" + PREFIX_TIME_END + "ENDTIME]] "
            + PREFIX_DURATION + "DURATION* \n"
            + "Example: `" + COMMAND_WORD + " "
            + PREFIX_DATE_START + "22-10-2022 "
            + PREFIX_TIME_START + "12:30 "
            + PREFIX_DATE_END + "22-10-2022 "
            + PREFIX_TIME_END + "16:30 "
            + PREFIX_DURATION + "45`";

    public static final String MESSAGE_SUCCESS = "Listed all slots of at least %d minutes from %s to %s.";
    public static final String PHRASE_NO_END_RANGE = "forever";
    public static final String MESSAGE_START_DATE_INVALID = "The start date provided is invalid!";
    public static final String MESSAGE_START_TIME_INVALID = "The start time provided is invalid!";
    public static final String MESSAGE_END_DATE_INVALID = "The end date provided is invalid!";
    public static final String MESSAGE_END_TIME_INVALID = "The end time provided is invalid!";
    public static final String MESSAGE_END_BEFORE_START = "The end date time provided is before the"
            + " start date time!";
    private static final String DATETIME_DISPLAY_FORMAT = "dd LLL yyyy hh:mm a";

    private final LocalDateTime rangeStart;
    private final LocalDateTime rangeEnd;
    private final int duration;

    /**
     * Constructs an {@code FreeBetweenCommand} object.
     */
    public FreeBetweenCommand(LocalDateTime rangeStart, LocalDateTime rangeEnd, int duration) {
        requireNonNull(rangeStart);
        requireNonNull(rangeEnd);

        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(new DateRangePredicate(rangeStart, rangeEnd));
        List<TimeRange> slotsFound = model.getSchedule()
                .findSlotsBetweenAppointments(rangeStart, rangeEnd, duration);
        List<AppointmentSlot> slotList = slotsFound.stream()
                .map(AppointmentSlot::new)
                .collect(Collectors.toList());
        model.setDisplayedAppointmentSlots(slotList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_DISPLAY_FORMAT);
        String responseMessage = String.format(MESSAGE_SUCCESS, duration, rangeStart.format(formatter),
                rangeEnd.toLocalDate().equals(LocalDate.MAX) ? PHRASE_NO_END_RANGE : rangeEnd.format(formatter));
        return new CommandResult(responseMessage, GuiListContentType.APPOINTMENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FreeBetweenCommand // instanceof handles nulls
                && rangeStart.equals(((FreeBetweenCommand) other).rangeStart)
                && rangeEnd.equals(((FreeBetweenCommand) other).rangeEnd)
                && duration == (((FreeBetweenCommand) other).duration));
    }
}
