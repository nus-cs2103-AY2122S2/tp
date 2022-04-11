package seedu.address.model.recipe.exceptions;

public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException() {
        super("Operation would result in a duplicate recipe");
    }
}
