package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.expense.Expense;

/**
 * Panel containing the list of expenses.
 */
public class ExpenseListPanel extends UiPart<Region> {
    private static final String LABEL = "Expense List";
    private static final String FXML = "ExpenseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpenseListPanel.class);

    @FXML
    private ListView<Expense> expenseListView;

    @FXML
    private Label expenseListLabel;

    /**
     * Creates a {@code ExpenseListPanel} with the given {@code ObservableList}.
     */
    public ExpenseListPanel(ObservableList<Expense> expenseList) {
        super(FXML);
        expenseListLabel.setText(LABEL);
        expenseListView.setItems(expenseList);
        expenseListView.setCellFactory(listView -> new ExpenseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Expene} using a {@code ExpenseCard}.
     */
    class ExpenseListViewCell extends ListCell<Expense> {
        @Override
        protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);

            if (empty || expense == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpenseCard(expense, getIndex() + 1).getRoot());
            }
        }
    }

}
