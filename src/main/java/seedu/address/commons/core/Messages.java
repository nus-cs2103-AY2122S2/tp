package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_RECIPE_INDEX = "The recipe index provided is "
            + "not a valid non-zero positive integer.";
    public static final String MESSAGE_MISSING_RECIPE_INDEX_OR_NAME = "Invalid recipe name or index value found. "
            + "Please enter a valid recipe name or recipe index";
    public static final String MESSAGE_RECIPES_LISTED_OVERVIEW = "%1$d recipes listed!";
    public static final String MESSAGE_FIND_RETURNS_NO_RECIPES_FOUND = "No recipes were found that matches %s\n\n"
            + "Use \"list\" to see all available recipes again.";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "The recipe for \"%s\" is not found in the recipe book! \n";
    public static final String MESSAGE_DELETE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";
    public static final String MESSAGE_DELETE_RECIPE_NOT_EXIST = "Recipe does not exist in the recipe book";
    public static final String MESSAGE_MISSING_INGREDIENT_FIELDS =
            "Ingredient name should not contain any numbers and should be in the <name> <quantity> "
                    + "[<quantifier>] format.";
    public static final String MESSAGE_INDEX_NOT_EXIST = "Recipe does not exist in the recipe book";

}
