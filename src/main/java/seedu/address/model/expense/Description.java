package seedu.address.model.expense;

import seedu.address.model.person.Name;

public class Description {
    public final String description;

    public Description(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.description.equals(((Description)other).description));
    }


    @Override
    public String toString() {
        return description;
    }
}
