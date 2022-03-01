package seedu.address.model.expense;

public class Category {
    public final String category;

    public Category(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && this.category.equals(((Category)other).category));
    }

    @Override
    public String toString() {
        return this.category;
    }
}
