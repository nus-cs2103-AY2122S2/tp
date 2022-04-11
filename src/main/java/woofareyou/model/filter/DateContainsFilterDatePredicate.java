package woofareyou.model.filter;

import java.time.LocalDate;
import java.util.Optional;

import woofareyou.logic.parser.ParserUtil;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.attendance.AttendanceEntry;
import woofareyou.model.pet.AttendanceHashMap;
import woofareyou.model.pet.Pet;

/**
 * Tests that a {@code Pet}'s {@code AttendanceEntry} matches the filter date given.
 */
public class DateContainsFilterDatePredicate extends FilterByContainsFilterWordPredicate {
    private final LocalDate filterDate;

    /**
     * Constructor for class
     * @param filterWord Date string provided as filter word
     * @throws ParseException Throws exception if date provided is not of correct format
     */
    public DateContainsFilterDatePredicate(String filterWord) throws ParseException {
        super(filterWord);
        if (filterWord.equals("today")) {
            this.filterDate = LocalDate.now();
        } else {
            this.filterDate = ParserUtil.parseFilterDate(filterWord);
        }

    }

    @Override
    public boolean test(Pet pet) {
        AttendanceHashMap petAttendance = pet.getAttendanceHashMap();
        Optional<AttendanceEntry> checkAttendanceEntry = petAttendance.getAttendance(filterDate);
        boolean petHasEntry = checkAttendanceEntry.isPresent();

        if (petHasEntry) {
            AttendanceEntry attendanceEntry = checkAttendanceEntry.get();
            Optional<Boolean> hasMarkedAttendance = attendanceEntry.getIsPresent();

            if (hasMarkedAttendance.isPresent()) {
                return hasMarkedAttendance.get();
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        return (other instanceof DateContainsFilterDatePredicate) // instanceof handles nulls
                && getFilterWord().equals(((DateContainsFilterDatePredicate) other).getFilterWord()); // state check
    }
}
