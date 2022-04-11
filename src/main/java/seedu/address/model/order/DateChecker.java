package seedu.address.model.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seedu.address.model.order.NaturalDateParser.Dates;

/**
 * DateChecker is used to help convert Strings into the proper date time formats.
 * It also helps to check for the validity of the date inputted.
 * If the date input is invalid, (e.g. missing the day, short form months), it throws a DukeException.
 */
public class DateChecker {
    private static String dateFormat;
    private static String dateToCheck;
    private static String timeToCheck;
    // boolean which says if the input date is before the current time (true) or after or if the time provided is
    // invalid (false).
    private static boolean isTimeBeforeCurrentTime;

    private static final int NUM_DAYS_IN_WEEK = 7;

    /**
     * Method meant to detect what type of date user inputs.
     * Format inputs that are not valid date types, but are natural dates.
     * Returns the original input if it notices that it is not a natural date.
     */
    private static void naturalDateCheck() {
        // add case to check for time provided
        NaturalDateParser dateChecked = new NaturalDateParser(dateToCheck);
        Dates inputtedDay = dateChecked.getDate(); // This gives the enum date which is like "MONDAY"

        // Solution to get current day of week and next required natural date adapted from:
        // https://coderanch.com/t/385117/java/date-Monday
        Calendar now = Calendar.getInstance();
        int dayToday = now.get(Calendar.DAY_OF_WEEK);

        int nextMonday = (Calendar.SATURDAY - dayToday + 2) % NUM_DAYS_IN_WEEK;
        int nextTuesday = (Calendar.SATURDAY - dayToday + 3) % NUM_DAYS_IN_WEEK;
        int nextWednesday = (Calendar.SATURDAY - dayToday + 4) % NUM_DAYS_IN_WEEK;
        int nextThursday = (Calendar.SATURDAY - dayToday + 5) % NUM_DAYS_IN_WEEK;
        int nextFriday = (Calendar.SATURDAY - dayToday + 6) % NUM_DAYS_IN_WEEK;
        int nextSaturday = (Calendar.SATURDAY - dayToday) % NUM_DAYS_IN_WEEK;
        int nextSunday = (Calendar.SATURDAY - dayToday + 1) % NUM_DAYS_IN_WEEK;

        boolean isValidDate = true;
        int daysAfter = 0;

        switch (inputtedDay) {
        case MONDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.MONDAY, nextMonday);
            break;
        case TUESDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.TUESDAY, nextTuesday);
            break;
        case WEDNESDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.WEDNESDAY, nextWednesday);
            break;
        case THURSDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.THURSDAY, nextThursday);
            break;
        case FRIDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.FRIDAY, nextFriday);
            break;
        case SATURDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.SATURDAY, nextSaturday);
            break;
        case SUNDAY:
            daysAfter = getNumDaysAfter(dayToday, Calendar.SUNDAY, nextSunday);
            break;
        default:
            dateFormat = dateToCheck;
            isValidDate = false;
            break;
        }
        if (isValidDate) {
            dateFormat = formatDate(now, daysAfter);
        }
    }

    /**
     * Formats the natural date input with a time as a proper datetime.
     * @param input Input is the date string provided by the user.
     * @return returns a properly formatted date string with the time
     */
    public static String parsePotentialNaturalDate(String input) {
        try {
            processInput(input);
            inputTimeBeforeCurrentTimeChecker();
            naturalDateCheck();
            return dateFormat + " " + timeToCheck;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            dateToCheck = input;
            return input;
        }
    }

    /**
     * Takes in the input and splits it into two strings, date and time.
     * @param input Inputted date time from the user.
     */
    private static void processInput(String input) {
        String[] inputSplit = input.split("\\s+", 2);
        String date = inputSplit[0];
        String time = inputSplit[1];
        dateToCheck = date;
        timeToCheck = time;
    }

    /**
     * Gets the number of days after today that corresponds with the inputted natural date.
     * @param dayToday This is an integer that ranges from 1-7. It is based on Calendar and 1 is the start of the
     *                 week, which is Sunday. 7 is the end of the week, which is Saturday.
     * @param testingDay This is an integer provided by Calendar as well. It is the Calendar.xday, which returns the
     *                   day that the system should use to check if today is xday.
     * @param nextNaturalDayAfterToday This is the calculated field e.g. nextMonday, nextTuesday. Since this method
     *                                 is being used in the switch case, this input provides flexibility for usage in
     *                                 the switch cases.
     * @return the number of days after today that corresponds with the inputted natural date.
     */
    private static int getNumDaysAfter(int dayToday, int testingDay, int nextNaturalDayAfterToday) {
        if (dayToday == testingDay && isTimeBeforeCurrentTime) {
            return NUM_DAYS_IN_WEEK;
        } else {
            return nextNaturalDayAfterToday;
        }
    }

    /**
     * Checks if the current time is already past the specified time.
     * Mainly used to handle natural dates that match with today's day.
     */
    // @@K.D punnyhuimin-reused
    // Reused from https://stackoverflow.com/questions/18186680/java-check-time-is-greater-time
    // Minor modifications made to fit implementation needs.
    private static void inputTimeBeforeCurrentTimeChecker() {
        Date timeNow = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.format(timeNow);
        try {
            if (timeFormat.parse(timeFormat.format(timeNow)).after(timeFormat.parse(timeToCheck))) {
                isTimeBeforeCurrentTime = true;
            } else {
                isTimeBeforeCurrentTime = false;
            }
            // @@K.D
        } catch (java.text.ParseException e) {
            isTimeBeforeCurrentTime = false;
        }
    }

    /**
     * This formats the Date to String. Used in DateChecker only.
     *
     * @param now Calendar class object meant to format the date.
     * @return Date in String format.
     */
    private static String formatDate(Calendar now, int daysAfterToday) {
        now.add(Calendar.DAY_OF_YEAR, daysAfterToday);
        Date date = now.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }
}
