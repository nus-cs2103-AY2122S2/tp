package woofareyou.model.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import woofareyou.logic.parser.ParserUtil;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.Appointment;
import woofareyou.model.pet.Pet;

public class AppointmentContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {
    private final LocalDate appointmentDate;

    /**
     * Constructor class. Checks if filter word is 'today'. If so, sets {@code appointmentDate} to a
     * {@code LocalDate} of today's date. Else, parse filter word to be a {@code LocalDate}.
     * @param filterWord String keyword provided after filter field.
     * @throws ParseException Throws exception if keyword (date) is not in the correct format.
     */
    public AppointmentContainsFilterWordPredicate(String filterWord) throws ParseException {
        super(filterWord);
        if (filterWord.equals("today")) {
            this.appointmentDate = LocalDate.now();
        } else {
            this.appointmentDate = ParserUtil.parseFilterAppointmentDate(filterWord);
        }
    }

    @Override
    public boolean test(Pet pet) {
        Appointment petAppointment = pet.getAppointment();
        LocalDateTime petAppointmentDateTime = petAppointment.getDateTime();
        if (petAppointmentDateTime != null) {
            LocalDate petAppointmentDate = petAppointmentDateTime.toLocalDate();
            return petAppointmentDate.equals(this.appointmentDate);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        return (other instanceof AppointmentContainsFilterWordPredicate// instanceof handles nulls
                && appointmentDate.equals(((AppointmentContainsFilterWordPredicate) other)
                .appointmentDate)); // state check
    }
}
