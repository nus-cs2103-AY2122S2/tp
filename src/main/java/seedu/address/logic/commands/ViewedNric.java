package seedu.address.logic.commands;

import seedu.address.model.patient.Nric;

public class ViewedNric {
    private static Nric viewedNric = null;

    public static Nric getViewedNric() {
        return viewedNric;
    }

    public static void setViewedNric(Nric nric) {
        viewedNric = nric;
    }
}
