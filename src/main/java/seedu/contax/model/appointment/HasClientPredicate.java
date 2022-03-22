package seedu.contax.model.appointment;

import seedu.contax.model.person.Person;

import java.util.Locale;
import java.util.function.Predicate;

public class HasClientPredicate implements Predicate<Appointment> {
    private final String clientName;


    public HasClientPredicate(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getPerson().getName().toString()
                .toLowerCase(Locale.ROOT).equals(clientName.toLowerCase(Locale.ROOT));
    }
}
