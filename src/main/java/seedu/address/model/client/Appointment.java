package seedu.address.model.client;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment should be in the form of"
                    + " 'yyyy-MM-dd-HH-mm'.\nIt can't be empty when using appointment command";

    public static final String EMPTY_APPOINTMENT = "";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    public final String value;
    public final LocalDateTime appointmentTime;


    /**
     * Takes in a string of time of appointment, check for whether it is valid
     * and initializes the value variable.
     * @param time string of time of appointment
     */
    public Appointment(String time) {
        requireNonNull(time);
        checkArgument(isValidAppointment(time), MESSAGE_CONSTRAINTS);
        if (time.equals("")) {
            appointmentTime = null;
        } else {
            appointmentTime = LocalDateTime.parse(time, FORMATTER);
        }
        value = time;
    }

    /**
     * Checks whether the input String is of the correct format.
     */
    public static boolean isValidAppointment(String time) {
        if (time.equals(EMPTY_APPOINTMENT)) {
            return true;
        }
        try {
            LocalDateTime parsedTime = LocalDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        //Handles February, they are guaranteed to be parsed as int as they passed the above parse test
        String[] timeArr = time.split("-");
        int year = Integer.parseInt(timeArr[0]);
        int month = Integer.parseInt(timeArr[1]);
        int day = Integer.parseInt(timeArr[2]);
        return isValidDate(year, month, day);
    }

    /**
     * Checks whether the input year is a leap year
     */
    private static boolean isLeapYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the input date is a valid date
     */

    private static boolean isValidDate(int year, int month, int day) {
        if (month == 2 && isLeapYear(year)) {
            return 1 <= day && day <= 29;
        } else if (month == 2 && !isLeapYear(year)) {
            return 1 <= day && day <= 28;
        } else if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
            || (month == 8) || (month == 10) || (month == 12)) {
            return 1 <= day && day <= 31;
        } else {
            return 1 <= day && day <= 30;
        }
    }

    /**
     * Returns an easy-to-read String of the appointment time
     */
    public String getAppointmentDetail() {
        if (appointmentTime == null) {
            return "No appointment with the person";
        } else {
            String pattern = "hh:mm a";
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(pattern);

            return "Appointment on " + appointmentTime.format(timeFormatter.withLocale(Locale.ENGLISH)) + " "
                    + appointmentTime.getMonth() + " " + appointmentTime.getDayOfMonth() + " "
                    + appointmentTime.getYear();
        }
    }

    /**
     * Checks whether the appointment time is later than current time
     * @return return true if the time later than current time, false if the time is in the past.
     */
    public boolean isLaterThanCurrentTime() {
        return appointmentTime.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && value.equals(((Appointment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
