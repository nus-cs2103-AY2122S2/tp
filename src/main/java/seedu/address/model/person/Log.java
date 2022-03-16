package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a note or log tied to a Person in the address book.
 */
public class Log {

    // default values
    public static final String DEFAULT_NO_DESCRIPTION = "";

    // immutable attributes
    private final LogName title;
    private final String description;

    /**
     * Constructs a Log object.
     *
     * @param title       Title of the log.
     * @param description Description tied to the log. Can be null.
     */
    public Log(String title, String description) {
        requireNonNull(title);
        this.title = new LogName(title);
        this.description = Objects.requireNonNullElse(description, Log.DEFAULT_NO_DESCRIPTION);
    }

    public String getDescription() {
        requireNonNull(this.description);
        return this.description;
    }

    public LogName getTitle() {
        requireNonNull(this.title);
        return this.title;
    }

    /**
     * Returns true if other Log is considered the same as this Log.
     * By convention, we consider two Logs the same if their titles are the same.
     *
     * @param other The other log.
     * @return True if other log is considered the same.
     */
    public boolean isSameLog(Log other) {
        return this.getTitle().equals(other.getTitle());
    }

    @Override
    public String toString() {
        return this.title + "\n" + this.description + "\n\n";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Log
                && (this.getTitle().equals(((Log) other).getTitle())) //getters ensure non-null
                && (this.getDescription().equals(((Log) other).getDescription())));
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

}
