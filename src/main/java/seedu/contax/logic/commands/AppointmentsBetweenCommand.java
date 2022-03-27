package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.DateRangePredicate;

/**
 * Lists all Appointments within a period.
 */
public class AppointmentsBetweenCommand extends Command {

    public static final String COMMAND_WORD = "appointmentsbetween";
    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Lists appointments within a period.** "
            + "Parameters: *"
            + "[" + PREFIX_DATE_START + "STARTDATE] "
            + "[" + PREFIX_TIME_START + "STARTTIME] "
            + "[" + PREFIX_DATE_END + "ENDDATE "
            + "[" + PREFIX_TIME_END + "ENDTIME]]*\n"
            + "Example: `" + COMMAND_WORD + " "
            + PREFIX_DATE_START + "22-10-2022 "
            + PREFIX_TIME_START + "12:30 "
            + PREFIX_DATE_END + "22-10-2022 "
            + PREFIX_TIME_END + "16:30`";

    public static final String MESSAGE_SUCCESS = "Listed appointments from %s to %s.";
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

    /**
     * Constructs an {@code AppointmentsBetweenCommand} object.
     *
     * @param rangeStart
     * @param rangeEnd
     */
    public AppointmentsBetweenCommand(LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        requireNonNull(rangeStart);
        requireNonNull(rangeEnd);

        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(new DateRangePredicate(rangeStart, rangeEnd));
        model.clearDisplayedAppointmentSlots();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_DISPLAY_FORMAT);
        String responseMessage = String.format(MESSAGE_SUCCESS, rangeStart.format(formatter),
                rangeEnd.toLocalDate().equals(LocalDate.MAX) ? PHRASE_NO_END_RANGE : rangeEnd.format(formatter));
        return new CommandResult(responseMessage, GuiListContentType.APPOINTMENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentsBetweenCommand // instanceof handles nulls
                && rangeStart.equals(((AppointmentsBetweenCommand) other).rangeStart)
                && rangeEnd.equals(((AppointmentsBetweenCommand) other).rangeEnd));
    }
}
