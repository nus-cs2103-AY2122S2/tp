package seedu.address.model.filter;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate{
    LocalDate appointmentDate;

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
}
