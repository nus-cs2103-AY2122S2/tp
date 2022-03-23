package seedu.trackermon.model.show;

public enum Status {

    COMPLETED("completed"),
    WATCHING("watching"),
    PLANTOWATCH("plan-to-watch");

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be completed or watching or plan to watch!";

    private final String status;
    private static final String PATTERN = "PLAN.*TO.*WATCH";

    Status(String status) {
        this.status = status;
    }
    public static Status getStatus(String status) {
        status = status.trim().toUpperCase();
        if (status.matches(PATTERN)) {
            status = PLANTOWATCH.name();
        }
        return Status.valueOf(status);
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
     * Checks the status of the show.
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStatus(String status) {
        //removes space in case user key in "plan to watch"
        if (status.matches(PATTERN)) {
            return true;
        }
        status = status.toUpperCase().trim();
        return status.equals(COMPLETED.name()) || status.equals(WATCHING.name())
            || status.equals(PLANTOWATCH.name());
    }

    /**
     * Compare this status with other
     */
    public int compareStatus(Status other) {
        return this.status.compareTo(other.status);
    }
}
