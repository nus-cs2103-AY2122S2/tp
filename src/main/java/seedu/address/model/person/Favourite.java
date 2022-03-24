package seedu.address.model.person;

/**
 * Represents a Client's favourited status in the address book.
 */
public class Favourite {
    private boolean value;

    public Favourite(boolean status) {
        this.value = status;
    }

    public boolean getStatus() {
        return value;
    }

    public void setStatus(boolean status) {
        value = status;
    }

    public boolean isUnfavourited() {
        return !value;
    }

    @Override
    public String toString() {
        return value
                ? "🌟"
                : "Unfavourited";
    }
}
