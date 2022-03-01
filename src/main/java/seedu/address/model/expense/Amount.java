package seedu.address.model.expense;

public class Amount {
    public final int amount;

    public Amount (int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && this.amount == ((Amount) other).amount); // state check
    }

    @Override
    public String toString() {
        return String.valueOf(this.amount);
    }
}
