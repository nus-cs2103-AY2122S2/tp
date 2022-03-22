package seedu.contax.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.awt.*;

public class HelpMenu extends UiPart<Stage> {
    private static final String FXML = "HelpMenu.fxml";
    private static final Paint HEADER_COLOR = Paint.valueOf("#383838");
    private static final Paint CELL_COLOR = Paint.valueOf("#555555");

    private int rows = 1;


    @FXML
    private GridPane table;

    public HelpMenu(Stage root) {
        super(FXML, root);
        init();
    }

    public HelpMenu() {
        this(new Stage());
    }

    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    private void init() {
        table.add(makeCell("Action", HEADER_COLOR), 0, 0);
        table.add(makeCell("Format", HEADER_COLOR), 1, 0);
        table.add(makeCell("Example", HEADER_COLOR), 2, 0);

        insertRow("Add Person", "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…   ",
                "addperson n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd t/friend t/colleague");
        insertRow("Clear", "clear", "-");
        insertRow("Delete Person", "deleteperson INDEX", "delete 3");
        insertRow("Edit Person", "editperson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…",
                "editperson 2 n/James Lee e/jameslee@example.com");
        insertRow("Find Person", "findperson KEYWORD [MORE_KEYWORDS] [by/SEARCH_TYPE]",
                "findperson James Jake by/name");
        insertRow("List Person", "listpersons", "-");
        insertRow("Add Tag", "addtag n/TAGNAME", "addtag n/Potential Clients");
        insertRow("Edit Tag", "edittag INDEX t/NEW_TAGNAME", "edittag 1 t/Prospective Clients");
        insertRow("List Tags", "listtags", "-");
        insertRow("Delete Tag", "deletetag INDEX", "deletetag 1");
        insertRow("Find Contacts By Tag", "findbytag t/TAGNAME", "findbytag t/friends");
        insertRow("Add Appointment", "addappointment n/NAME d/DATE t/TIME l/DURATION p/PERSON",
                "addappointment n/Call Bob d/14-02-2022 t/11:00 p/2 l/60");
        insertRow("List Appointments", "listappointments", "-");
        insertRow("Delete Appointment", "deleteappointment INDEX", "deleteappointment 2");
        insertRow("Edit Appointment", "editappointment INDEX [n/NAME] [d/DATE] [t/TIME] [p/PERSON] [l/DURATION]",
                "editappointment 2 n/Call Juliet t/13:45");
        insertRow("List Appointments Within Period", "appointmentsbetween sd/STARTDATE st/STARTTIME ed/ENDDATE et/ENDTIME",
                "appointmentsbetween sd/21-10-2022 st/12:00 ed/23-10-2022 et/17:00");
        insertRow("Help", "help", "-");
        insertRow("Export CSV", "exportcsv", "-");
        insertRow("Import CSV", "importcsv f/FILEPATH [n/COLUMNNUM] [p/COLUMN_PERSON] [e/COLUMN_EMAIL] [a/COLUMN_ADDRESS] [t/COLUMN_TAGS]",
                "importCSV n/2 p/3 e/5 a/6 t/4");
        insertRow("Operate on Contacts by Conditional Clause", "batch COMMAND where/CONDITION",
                "batch Edit p/87438806 where/ p/Phone = 87438807");
        insertRow("Operate on Contacts within Range", "range COMMAND from/INDEX to/INDEX",
                "range editperson e/johndoe@example.com from/6 to/10");
        insertRow("Chaining Commands", "chain COMMAND_A && COMMAND_B", "chain editappointment 6 l/360 && listappointments");
    }



    private void insertRow(String action, String format, String example) {
        table.add(makeCell(action, CELL_COLOR), 0, rows);
        table.add(makeCell(format, CELL_COLOR), 1, rows);
        table.add(makeCell(example, CELL_COLOR), 2, rows);
        rows += 1;
    }

    private Node makeCell(String text, Paint paint) {
        HBox p = new HBox();
        p.setAlignment(Pos.CENTER_LEFT);
        Label l = new Label();
        l.setWrapText(true);
        l.setPrefWidth(350);
        l.setPrefHeight(text.length() > 60 ? 50 : 30);
        TextFlow t = new TextFlow();
        Text text_1 = new Text(text);
        t.getChildren().add(text_1);
        l.setPadding(new Insets(0,0,0,5));
        text_1.setWrappingWidth(350);
        l.setText(text);
        l.setWrapText(true);
        l.setTextFill(Paint.valueOf("#FFFFFF"));
        p.getChildren().add(l);
        p.setBackground(new Background(new BackgroundFill(paint, new CornerRadii(0), Insets.EMPTY)));
        return p;
    }

}
