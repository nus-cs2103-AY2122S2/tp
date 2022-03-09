package seedu.address.model.show;

import java.util.Locale;

public class Status {

    private static final String COMPLETED = "completed";
    private static final String WATCHING = "watching";
    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be completed or watching!!!";

    private final String status;

    public Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + status + "]";
    }

    /**
     * Checks the status of the show.
     * @return true if the show status is completed.
     */
    public Boolean isCompleted() {
        return status.equals(COMPLETED);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        String stest = test.toLowerCase().trim();
        return (stest.equals(COMPLETED) || stest.equals(WATCHING));
    }

}
