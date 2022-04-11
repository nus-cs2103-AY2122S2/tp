package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HelpCommandBox extends UiPart<Region> {
    private static final String FXML = "HelpCommandBox.fxml";

    @FXML
    private TitledPane title;
    @FXML
    private Text description;

    /**
     * Constructor for a titled pane containing the description of a command.
     */
    public HelpCommandBox(String title, String description) {
        super(FXML);
        this.title.setText(title);
        this.description.setText(description);
        this.description.wrappingWidthProperty().bind(this.title.widthProperty().subtract(50));
    }

    public TitledPane getBox() {
        return title;
    }
}
