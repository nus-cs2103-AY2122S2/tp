package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.Step;

public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private CommandResult commandResult;

    @FXML
    private Label response;
    @FXML
    private VBox recipeFields;
    @FXML
    private Label recipeName;
    @FXML
    private Label ingredients;
    @FXML
    private Label steps;
    @FXML
    private Label completionTime;
    @FXML
    private Label servingSize;


    /**
     * The UI component responsible for displaying output to the user.
     */
    public ResultDisplay() {
        super(FXML);
        response.setWrapText(true);
        recipeName.setWrapText(true);
        setVisibleResponseField(false);
        setVisibleRecipeFields(false);
    }

    /**
     * Updates the contents of the result display depending on the contents CommandResult.
     * Displays the recipe contents if CommandResult contains a recipe, otherwise
     * displays the feedbackToUser message.
     *
     * @param commandResult the CommandResult containing the information to be displayed.
     */
    public void setFeedbackToUser(CommandResult commandResult) {
        requireNonNull(commandResult);
        this.commandResult = commandResult;

        if (commandResult.hasRecipe()) {
            setRecipeFields();
        } else {
            setResponse(commandResult.getFeedbackToUser());
        }
    }

    /**
     * Sets the text of the response label.
     *
     * @param message the message to set.
     */
    public void setFeedbackToUser(String message) {
        requireNonNull(message);
        setResponse(message);
    }

    /**
     * Sets the contents of the corresponding recipe labels to display the contents
     * of the recipe.
     */
    private void setRecipeFields() {
        setVisibleResponseField(false);
        setVisibleRecipeFields(true);

        Recipe recipe = commandResult.getRecipe();
        requireNonNull(recipe);

        response.setText("");
        recipeName.setText(recipe.getName().fullName);
        ingredients.setText(getIngredients(recipe));
        steps.setText(getSteps(recipe));
        completionTime.setText(String.valueOf(recipe.getCompletionTime().value) + " mins");
        servingSize.setText(String.valueOf(recipe.getServingSize().value) + " pax");
    }

    /**
     * Sets the contents of the response label to display the message to the result display
     *
     * @param response the message to set into the response label.
     */
    private void setResponse(String response) {
        setVisibleRecipeFields(false);
        setVisibleResponseField(true);
        this.response.setText(response);
    }

    private void setVisibleRecipeFields(boolean isVisible) {
        recipeFields.setVisible(isVisible);
    }

    private void setVisibleResponseField(boolean isVisible) {
        response.setVisible(isVisible);
    }

    /**
     * Formats the {@code the Ingredient}s of a recipe to a formatted String to display on the
     * result display.
     *
     * @param recipe the Recipe to parse.
     * @return the formatted String of ingredients.
     */
    private String getIngredients(Recipe recipe) {
        StringBuilder ingredients = new StringBuilder();
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredients.append(String.format("%s %s %s\n", ingredient.getIngredientName(),
                    ingredient.getQuantity(), ingredient.getQuantifier()));
        }
        return ingredients.toString();
    }

    /**
     * Formats the {@code Step}s of a recipe to a formatted String to display on the
     * result display.
     *
     * @param recipe the Recipe to parse.
     * @return the formatted String of steps.
     */
    private String getSteps(Recipe recipe) {
        int index = 1;
        StringBuilder steps = new StringBuilder();
        for (Step step : recipe.getSteps()) {
            steps.append(String.format("%d. %s\n", index, step.toString()));
            index++;
        }
        return steps.toString();
    }
}
