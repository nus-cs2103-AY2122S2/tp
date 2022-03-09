package seedu.address.model.show;

public enum Status {

    COMPLETED("completed"),
    WATCHING("watching");

    private final String status;

    Status(String status) {
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
        if (status.equals("completed")) {
            return true;
        }
        return false;
    }
}
