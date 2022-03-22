package seedu.contax.ui;

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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class HelpWindow extends UiPart<Stage> {
    private static final String FXML = "HelpWindow.fxml";
    private static final Paint HEADER_COLOR = Paint.valueOf("#383838");
    private static final Paint CELL_COLOR = Paint.valueOf("#555555");

    private int rowCounter = 1;

    @FXML
    private GridPane page1;

    @FXML
    private GridPane page2;

    @FXML
    private Button firstPageBtn;

    @FXML
    private Button secondPageBtn;


    public HelpWindow(Stage root) {
        super(FXML, root);
        init();
    }

    public HelpWindow() {
        this(new Stage());
    }

    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();

    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void hide() {
        getRoot().hide();
    }

    public void focus() {
        getRoot().requestFocus();
    }

    private void init() {

        // set up table headers
        page1.add(makeCell("Action", HEADER_COLOR), 0, 0);
        page1.add(makeCell("Format", HEADER_COLOR), 1, 0);
        page1.add(makeCell("Example", HEADER_COLOR), 2, 0);
        page2.add(makeCell("Action", HEADER_COLOR), 0, 0);
        page2.add(makeCell("Format", HEADER_COLOR), 1, 0);
        page2.add(makeCell("Example", HEADER_COLOR), 2, 0);

        // populate cells
        insertRow("Add Person", "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…   ",
                "addperson n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd t/friend t/colleague", page1);
        insertRow("Clear", "clear", "-", page1);
        insertRow("Delete Person", "deleteperson INDEX", "delete 3", page1);
        insertRow("Edit Person", "editperson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…",
                "editperson 2 n/James Lee e/jameslee@example.com", page1);
        insertRow("Find Person", "findperson KEYWORD [MORE_KEYWORDS] [by/SEARCH_TYPE]",
                "findperson James Jake by/name", page1);
        insertRow("List Person", "listpersons", "-", page1);
        insertRow("Add Tag", "addtag n/TAGNAME", "addtag n/Potential Clients", page1);
        insertRow("Edit Tag", "edittag INDEX t/NEW_TAGNAME", "edittag 1 t/Prospective Clients", page1);
        insertRow("List Tags", "listtags", "-", page1);
        insertRow("Delete Tag", "deletetag INDEX", "deletetag 1", page1);
        insertRow("Find Contacts By Tag", "findbytag t/TAGNAME", "findbytag t/friends", page1);
        insertRow("Add Appointment", "addappointment n/NAME d/DATE t/TIME l/DURATION p/PERSON",
                "addappointment n/Call Bob d/14-02-2022 t/11:00 p/2 l/60", page1);
        insertRow("List Appointments", "listappointments", "-", page1);
        insertRow("Delete Appointment", "deleteappointment INDEX", "deleteappointment 2", page1);

        rowCounter = 1;
        insertRow("Edit Appointment", "editappointment INDEX [n/NAME] [d/DATE] [t/TIME] [p/PERSON] [l/DURATION]",
                "editappointment 2 n/Call Juliet t/13:45", page2);
        insertRow("List Appointments Within Period", "appointmentsbetween sd/STARTDATE st/STARTTIME ed/ENDDATE et/ENDTIME",
                "appointmentsbetween sd/21-10-2022 st/12:00 ed/23-10-2022 et/17:00", page2);
        insertRow("Help", "help", "-", page2);
        insertRow("Export CSV", "exportcsv", "-", page2);
        insertRow("Import CSV", "importcsv f/FILEPATH [n/COLUMNNUM] [p/COLUMN_PERSON] [e/COLUMN_EMAIL] [a/COLUMN_ADDRESS] [t/COLUMN_TAGS]",
                "importCSV n/2 p/3 e/5 a/6 t/4", page2);
        insertRow("Operate on Contacts by Conditional Clause", "batch COMMAND where/CONDITION",
                "batch Edit p/87438806 where/ p/Phone = 87438807", page2);
        insertRow("Operate on Contacts within Range", "range COMMAND from/INDEX to/INDEX",
                "range editperson e/johndoe@example.com from/6 to/10", page2);
        insertRow("Chaining Commands", "chain COMMAND_A && COMMAND_B", "chain editappointment 6 l/360 && listappointments", page2);

        setPage1();
    }

    private void insertRow(String action, String format, String example, GridPane table) {
        table.add(makeCell(action, CELL_COLOR), 0, rowCounter);
        table.add(makeCell(format, CELL_COLOR), 1, rowCounter);
        table.add(makeCell(example, CELL_COLOR), 2, rowCounter);
        rowCounter += 1;
    }

    private Node makeCell(String text, Paint paint) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: #383838; -fx-border-width: 0.7px;");

        Label label = new Label();
        label.setWrapText(true);
        label.setPrefWidth(350);
        label.setPrefHeight(text.length() > 60 ? 50 : 30);
        label.setPadding(new Insets(0,0,0,5));
        label.setText(text);
        label.setTextFill(Paint.valueOf("#FFFFFF"));

        hbox.getChildren().add(label);
        hbox.setBackground(new Background(new BackgroundFill(paint, new CornerRadii(0), Insets.EMPTY)));
        return hbox;
    }

    public void setPage1() {
        page1.setVisible(true);
        page2.setVisible(false);
        firstPageBtn.setStyle("-fx-border-color: #FFFFFF;");
        secondPageBtn.setStyle("-fx-border-color: #555555;");

    }

    public void setPage2() {
        page1.setVisible(false);
        page2.setVisible(true);
        firstPageBtn.setStyle("-fx-border-color: #555555;");
        secondPageBtn.setStyle("-fx-border-color: #FFFFFF;");
    }

}
