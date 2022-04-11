package seedu.trackermon.model.show;

/***
 * Represents a Show's watch status.
 */
public enum Status {

    COMPLETED("completed"),
    WATCHING("watching"),
    PLAN_TO_WATCH("plan-to-watch");

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be completed, watching, or plan-to-watch!";

    private static final String PATTERN = "PLAN-TO-WATCH";

    private final String status;

    /**
     * Constructs a {@code Status} with the provided {@code String}.
     * @param status provided {@code String}.
     */
    Status(String status) {
        this.status = status;
    }

    public static Status getStatus(String status) {
        status = status.trim().toUpperCase();
        //find match pattern similar to "plan-to-watch" for example, user key in "plan to watch"
        if (status.matches(PATTERN)) {
            status = PLAN_TO_WATCH.name();
        }
        return Status.valueOf(status);
    }

    /**
     * Returns the {@code String} representation of {@code Status}.
     */
    @Override
    public String toString() {
        return status;
    }

    /**
     * Checks the status of the show.
     * Returns true if provided {@code String} is a valid name.
     * @param status provided {@code string}.
     */
    public static boolean isValidStatus(String status) {
        status = status.toUpperCase().trim();
        //find match pattern similar to "plan-to-watch" for example, user key in "plan to watch"
        if (status.matches(PATTERN)) {
            return true;
        }
        return status.equals(COMPLETED.name()) || status.equals(WATCHING.name())
            || status.equals(PLAN_TO_WATCH.name());
    }
}
