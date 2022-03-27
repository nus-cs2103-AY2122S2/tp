package manageezpz.ui;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Event;
import manageezpz.model.task.Task;
import manageezpz.model.task.Todo;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private GridPane detailsPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Label dateTimeLabel;
    @FXML
    private Label dateTime;
    @FXML
    private Label employeesTagLabel;
    @FXML
    private Label employeesTag;
    @FXML
    private Label priorityTagLabel;
    @FXML
    private ImageView priorityTagIcon;
    @FXML
    private Label priorityTag;
    @FXML
    private Label statusIsDoneLabel;
    @FXML
    private Label statusIsDone;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;

        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().description);
        type.setText(task.getType());

        if (task instanceof Deadline || task instanceof Event) {
            dateTime.setText(task.getDateTime());
        }

        if (!task.getAssignees().isEmpty()) {
            String assigneesNames = task.getAssignees()
                    .stream()
                    .flatMap(person -> Stream.of(person.getName().fullName))
                    .collect(Collectors.joining(", "));

            employeesTag.setText(assigneesNames);
        }

        Image priorityIcon;

        switch (task.getPriority().name()) {
        case "LOW":
            priorityIcon = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/priorities_low.png")));
            priorityTagIcon.setImage(priorityIcon);
            priorityTag.setText(task.getPriority().name());
            break;
        case "MEDIUM":
            priorityIcon = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/priorities_medium.png")));
            priorityTagIcon.setImage(priorityIcon);
            priorityTag.setText(task.getPriority().name());
            break;
        case "HIGH":
            priorityIcon = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/priorities_high.png")));
            priorityTagIcon.setImage(priorityIcon);
            priorityTag.setText(task.getPriority().name());
            break;
        case "NONE":
            break;
        default:
            throw new RuntimeException("Invalid Task Priority");
        }

        if (task.isDone()) {
            statusIsDone.setText("Done");
            statusIsDone.getStyleClass().add("cell_completion_done_label");
        } else {
            statusIsDone.setText("Not Done");
            statusIsDone.getStyleClass().add("cell_completion_not_done_label");
        }

        // Remove date/time row if it is a Todo task
        if (task instanceof Todo) {
            removeRow(detailsPane, GridPane.getRowIndex(dateTimeLabel));
        }

        // Remove assignees row if there are no employees assigned to the task
        if (task.getAssignees().isEmpty()) {
            removeRow(detailsPane, GridPane.getRowIndex(employeesTagLabel));
        }

        // Remove priority row if there are no priority tagged to the task
        if (task.getPriority().name().equals("NONE")) {
            // Remove 'priority' row if there are no priority tagged to the task
            removeRow(detailsPane, GridPane.getRowIndex(priorityTagLabel));
        }
    }

    /**
     * Gets row index constrain for given node, forcefully as integer: 0 as null.
     * @param node Node to look up the constraint for
     * @return The row index as primitive integer
     */
    public int getRowIndexAsInteger(Node node) {
        return GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
    }

    /**
     * Removes row from grid pane by index.
     * @param grid Grid pane to be affected
     * @param targetRowIndexIntegerObject Target row index to be removed. Integer object type,
     *                                    because for some reason `getRowIndex` returns null
     *                                    for children at 0th row.
     */
    public void removeRow(GridPane grid, Integer targetRowIndexIntegerObject) {
        int targetRowIndex = targetRowIndexIntegerObject == null ? 0 : targetRowIndexIntegerObject;

        // Remove children from row
        grid.getChildren().removeIf(node -> (getRowIndexAsInteger(node) == targetRowIndex));

        // Update indexes of other rows, i.e., shift rows up
        grid.getChildren().forEach(node -> {
            int rowIndex = getRowIndexAsInteger(node);

            if (targetRowIndex < rowIndex) {
                GridPane.setRowIndex(node, rowIndex - 1);
            }
        });

        // Remove row constraints
        grid.getRowConstraints().remove(targetRowIndex);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
