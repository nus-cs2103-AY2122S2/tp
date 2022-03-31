package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.model.client.Client;

public class ComparatorUtil {
    public static final Comparator<Client> NAME_COMPARATOR_ASC =
            Comparator.comparing(p -> p.getName().fullName.toUpperCase());
    public static final Comparator<Client> NAME_COMPARATOR_DESC = NAME_COMPARATOR_ASC.reversed();
    public static final Comparator<Client> TIME_COMPARATOR_ASC = new Comparator<>() {
        @Override
        public int compare(Client c1, Client c2) {
            LocalDateTime firstTime = c1.getAppointment().appointmentTime;
            LocalDateTime secondTime = c2.getAppointment().appointmentTime;
            if (firstTime == null && secondTime == null) {
                return 0;
            } else if (firstTime == null && secondTime != null) {
                return 1;
            } else if (firstTime != null & secondTime == null) {
                return -1;
            } else if (c1.getAppointment().appointmentTime.isBefore(c2.getAppointment().appointmentTime)) {
                return -1;
            } else if (c1.getAppointment().appointmentTime.isAfter(c2.getAppointment().appointmentTime)) {
                return 1;
            } else {
                return 0;
            }
        }
    };
    public static final Comparator<Client> TIME_COMAPRATOR_DESC = new Comparator<>() {
        @Override
        public int compare(Client c1, Client c2) {
            LocalDateTime firstTime = c1.getAppointment().appointmentTime;
            LocalDateTime secondTime = c2.getAppointment().appointmentTime;
            if (firstTime == null && secondTime == null) {
                return 0;
            } else if (firstTime == null && secondTime != null) {
                return 1;
            } else if (firstTime != null & secondTime == null) {
                return -1;
            } else if (c1.getAppointment().appointmentTime.isBefore(c2.getAppointment().appointmentTime)) {
                return 1;
            } else if (c1.getAppointment().appointmentTime.isAfter(c2.getAppointment().appointmentTime)) {
                return -1;
            } else {
                return 0;
            }
        }
    };

}
