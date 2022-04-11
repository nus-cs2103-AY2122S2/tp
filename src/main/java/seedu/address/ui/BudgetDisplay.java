package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Date;

/**
 * A ui for the budget dashboard bar that is displayed at the header of the application.
 */
public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";
    private static final String MONTHLY_BUDGET = "Budget remaining is $%1$s";

    @FXML
    private TextArea budgetDisplay;

    public BudgetDisplay() {
        super(FXML);
    }

    /**
     * Initializes BudgetDisplay with a given String
     */
    public BudgetDisplay(String string) {
        super(FXML);
        budgetDisplay.setText(string);
    }

    /**
     * Reflects the budget set in the application, if it has been set
     */
    public void showMonthlyBudget(Budget budget) {
        requireNonNull(budget);
        String budgetAmount = budget.getBudgetAmount().toString();
        Date budgetDate = budget.getBudgetDate();
        if (!(budgetAmount.equals("0.00")) || !budgetDate.equals("1900-01-01")) {
            budgetDisplay.setText(String.format(MONTHLY_BUDGET, budgetAmount));
        } else {
            budgetDisplay.setText("Budget for this month has not been set yet!");
        }
    }

}
