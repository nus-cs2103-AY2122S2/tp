package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.HelpWindowMessages;

public class HelpCommandDisplay extends UiPart<Region> {
    private static final String FXML = "HelpCommandDisplay.fxml";
    @FXML
    private AnchorPane mainWindow;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox commandDisplay;

    @FXML
    private Accordion helpList;

    @FXML
    private TextField helpWindowCommandInput;

    private HashMap<String, HelpCommandBox> helpListDictionary = new HashMap<>();

    /**
     * Constructor for a UI window containing help commands.
     */
    public HelpCommandDisplay() {
        super(FXML);
        scrollPane.setContent(commandDisplay);
    }

    private void setAnchors(Node node, double top, double bottom, double left, double right) {
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setBottomAnchor(node, bottom);
    }

    /**
     * Aligns the help command display window with the parent stage,
     * @param node The parent to be aligned with.
     */
    public void addCommandDisplay(AnchorPane node) {
        mainWindow.prefWidthProperty().bind(node.widthProperty());
        mainWindow.prefHeightProperty().bind(node.heightProperty());
        setAnchors(mainWindow, 0 , 0, 0, 0);
        node.getChildren().add(mainWindow);
        addAccordion();
    }

    @FXML
    private void handleUserInput() {
        String input = helpWindowCommandInput.getText();
        identifyHelpList(input);
        helpWindowCommandInput.clear();
    }

    /**
     * Expands the selected command tooltip window.
     * @param str The name of the command.
     */
    public void identifyHelpList(String str) {
        HelpCommandBox targetBox = helpListDictionary.get(str);
        if (targetBox != null) {
            helpList.setExpandedPane(targetBox.getBox());
            ensureVisible(scrollPane, targetBox.getBox());
        }
        helpWindowCommandInput.requestFocus();
    }

    private void addAccordion() {
        ArrayList<String> list = HelpWindowMessages.getHelpCommandDisplayList();
        for (int i = 0; i < list.size(); i += 2) {
            String commandWord = list.get(i);
            String commandMessage = list.get(i + 1);
            HelpCommandBox curr = new HelpCommandBox(commandWord, commandMessage);
            setAnchors(curr.getBox(), 0, 0, 0, 0);
            helpList.getPanes().add(curr.getBox());
            helpListDictionary.put(commandWord, curr);
        }
    }

    /*adapted from stackoverflow:
    https://stackoverflow.com/questions/12837592/how-to-scroll
    -to-make-a-node-within-the-content-of-a-scrollpane-visible
    */
    private static void ensureVisible(ScrollPane pane, Node node) {
        double width = pane.getContent().getBoundsInLocal().getWidth();
        double height = pane.getContent().getBoundsInLocal().getHeight();

        double x = node.getBoundsInParent().getMaxX();
        double y = node.getBoundsInParent().getMaxY();

        pane.setVvalue(y / height);
        pane.setHvalue(x / width);
    }
}
