package seedu.contax.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import seedu.contax.commons.core.LogsCenter;


public class HelpWindow extends UiPart<Stage> {
    private static final String FXML = "HelpWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final Paint HEADER_COLOR = Paint.valueOf("#383838");
    private static final Paint CELL_COLOR = Paint.valueOf("#555555");

    private int rowCounter = 1;

    @FXML
    private VBox parentPane;

    @FXML
    private GridPane generalPage;

    @FXML
    private GridPane personsPage;

    @FXML
    private GridPane appointmentsPage;

    @FXML
    private GridPane tagsPage;

    @FXML
    private Button generalBtn;

    @FXML
    private Button personsBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button tagsBtn;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        init();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();

    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Initializes the HelpWindow by populating it with data.
     */
    private void init() {

        // set up table headers
        insertHeaderRow("Action", "Format", "Example", generalPage);
        insertHeaderRow("Action", "Format", "Example", personsPage);
        insertHeaderRow("Action", "Format", "Example", appointmentsPage);
        insertHeaderRow("Action", "Format", "Example", tagsPage);

        // populate general page
        rowCounter = 1;
        insertRow("Clear", "clear", "-", generalPage);
        insertRow("Help", "help", "-", generalPage);
        insertRow("Export CSV", "exportcsv", "-", generalPage);
        insertRow("Import CSV", "importcsv f/FILEPATH [n/COLUMNNUM] "
                        + "[p/COLUMN_PERSON] [e/COLUMN_EMAIL] [a/COLUMN_ADDRESS] [t/COLUMN_TAGS]",
                "importCSV n/2 p/3 e/5 a/6 t/4", generalPage);
        insertRow("Chaining Commands", "chain COMMAND_A && COMMAND_B",
                "chain editappointment 6 l/360 && listappointments", generalPage);

        // populate persons page
        rowCounter = 1;
        insertRow("Add Person", "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…   ",
                "addperson n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd t/friend t/colleague",
                personsPage);
        insertRow("Delete Person", "deleteperson INDEX", "delete 3", personsPage);
        insertRow("Edit Person", "editperson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…",
                "editperson 2 n/James Lee e/jameslee@example.com", personsPage);
        insertRow("List Person", "listpersons", "-", personsPage);
        insertRow("Find Person", "findperson KEYWORD [MORE_KEYWORDS] [by/SEARCH_TYPE]",
                "findperson James Jake by/name", personsPage);
        insertRow("Operate on Contacts by Conditional Clause", "batch COMMAND where/CONDITION",
                "batch Edit p/87438806 where/ p/Phone = 87438807", personsPage);
        insertRow("Operate on Contacts within Range", "range COMMAND from/INDEX to/INDEX",
                "range editperson e/johndoe@example.com from/6 to/10", personsPage);

        // populate appointments page
        rowCounter = 1;
        insertRow("Add Appointment", "addappointment n/NAME d/DATE t/TIME l/DURATION p/PERSON      ",
                "addappointment n/Call Bob d/14-02-2022 t/11:00 p/2 l/60", appointmentsPage);
        insertRow("List Appointments", "listappointments", "-", appointmentsPage);
        insertRow("Delete Appointment", "deleteappointment INDEX", "deleteappointment 2", appointmentsPage);
        insertRow("Edit Appointment", "editappointment INDEX [n/NAME] [d/DATE] [t/TIME] [p/PERSON] [l/DURATION]",
                "editappointment 2 n/Call Juliet t/13:45", appointmentsPage);
        insertRow("List Appointments Within Period", "appointmentsbetween [sd/STARTDATE] [st/STARTTIME] "
                        + "[ed/ENDDATE [et/ENDTIME]]" , "appointmentsbetween sd/21-10-2022 st/12:00 ed/23-10-2022 et/17:00",
                appointmentsPage);
        insertRow("List Available Slots Within Period", "freebetween l/DURATION [sd/STARTDATE] [st/STARTTIME] "
                        + "[ed/ENDDATE [et/ENDTIME]]" , "freebetween l/20 sd/21-10-2022 st/12:00 ed/23-10-2022 et/17:00",
                appointmentsPage);

        // populate tags page
        insertRow("Add Tag", "addtag n/TAGNAME", "addtag n/Potential Clients", tagsPage);
        insertRow("Edit Tag", "edittag INDEX t/NEW_TAGNAME", "edittag 1 t/Prospective Clients", tagsPage);
        insertRow("Delete Tag", "deletetag INDEX", "deletetag 1", tagsPage);
        insertRow("List Tags", "listtags", "-", tagsPage);
        insertRow("Find Contacts By Tag", "findbytag t/TAGNAME", "findbytag t/friends", tagsPage);

        setGeneralPage();
    }

    /**
     * Inserts a row into the help window table at the current row.
     * The current row is indicated by rowCounter.
     * @param s1 string for the first column
     * @param s2 string for the second column
     * @param s3 string for the third column
     * @param table table for row to be inserted into
     */
    private void insertRow(String s1, String s2, String s3, GridPane table) {
        table.add(makeCell(s1, CELL_COLOR), 0, rowCounter);
        table.add(makeCell(s2, CELL_COLOR), 1, rowCounter);
        table.add(makeCell(s3, CELL_COLOR), 2, rowCounter);
        rowCounter += 1;
    }

    /**
     * Inserts a header row into the help window table at the 0 row.
     * The current row is indicated by rowCounter.
     * @param s1 string for the first column
     * @param s2 string for the second column
     * @param s3 string for the third column
     * @param table table for row to be inserted into
     */
    private void insertHeaderRow(String s1, String s2, String s3, GridPane table) {
        table.add(makeCell("Action", HEADER_COLOR), 0, 0);
        table.add(makeCell("Format", HEADER_COLOR), 1, 0);
        table.add(makeCell("Example", HEADER_COLOR), 2, 0);
    }


    /**
     * Makes a cell, which comprises a hbox and a label within it.
     * @param text text of the label
     * @param paint background color of the cell
     * @return a node object which corresponds to a cell in the table
     */
    private Node makeCell(String text, Paint paint) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: #383838; -fx-border-width: 0.7px;");

        Label label = new Label();
        label.setWrapText(true);
        label.setPrefWidth(350);
        label.setMinHeight(Region.USE_PREF_SIZE);
        label.setPadding(new Insets(10, 0, 10, 5));
        label.setText(text);
        label.setTextFill(Paint.valueOf("#FFFFFF"));


        hbox.getChildren().add(label);
        hbox.setBackground(new Background(new BackgroundFill(paint, new CornerRadii(0), Insets.EMPTY)));
        return hbox;
    }



    /**
     * Sets general page to be shown.
     */
    public void setGeneralPage() {
        hideAllPages();
        generalPage.setVisible(true);
        generalBtn.setStyle("-fx-border-color: #FFFFFF;");
    }

    /**
     * Sets persons page to be shown.
     */
    public void setPersonsPage() {
        hideAllPages();
        personsPage.setVisible(true);
        personsBtn.setStyle("-fx-border-color: #FFFFFF;");
    }


    /**
     * Sets appointments page to be shown.
     */
    public void setAppointmentsPage() {
        hideAllPages();
        appointmentsPage.setVisible(true);
        appointmentsBtn.setStyle("-fx-border-color: #FFFFFF;");
    }

    /**
     * Sets tags page to be shown.
     */
    public void setTagsPage() {
        hideAllPages();
        tagsPage.setVisible(true);
        tagsBtn.setStyle("-fx-border-color: #FFFFFF;");
    }

    /**
     * Sets all pages to be hidden.
     */
    public void hideAllPages() {
        generalPage.setVisible(false);
        personsPage.setVisible(false);
        appointmentsPage.setVisible(false);
        tagsPage.setVisible(false);
        generalBtn.setStyle("-fx-border-color: #555555;");
        personsBtn.setStyle("-fx-border-color: #555555;");
        appointmentsBtn.setStyle("-fx-border-color: #555555;");
        tagsBtn.setStyle("-fx-border-color: #555555;");
    }

}
