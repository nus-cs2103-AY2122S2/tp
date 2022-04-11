package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.ui.UiPart;

public class HelpTable extends UiPart<Region> {
    private static final String FXML = "HelpTable.fxml";

    @FXML
    private TableView<CommandFormat> helpTable;

    @FXML
    private TableColumn<CommandFormat, String> typeColumn;

    @FXML
    private TableColumn<CommandFormat, String> formatColumn;

    /**
     * creates a table containing the list of commands
     */
    public HelpTable() {
        super(FXML);
        initializeTable();
    }

    private void initializeTable() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("commandType"));
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("commandFormat"));
        helpTable.getItems().add(new CommandFormat("add", "add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS "
                                + "[pr/PROPERTY]... [pf/PREFERENCE] i/FILEPATH[:DESCRIPTION] "));
        helpTable.getItems().add(new CommandFormat("clear", "clear"));
        helpTable.getItems().add(new CommandFormat("delete", "delete INDEX"));
        helpTable.getItems().add(new CommandFormat("edit", "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]"
                            + "[pr/PROPERTY]… [i/FILEPATH[:DESCRIPTION]]"));
        helpTable.getItems().add(new CommandFormat("find", "find ATTRIBUTE KEYWORD [MORE KEYWORDS...]"));
        helpTable.getItems().add(new CommandFormat("sort", "sort [!]KEYWORD [[!]MORE_KEYWORDS]…"));
        helpTable.getItems().add(new CommandFormat("remind", "remind INDEX r/reminderDetails "));
        helpTable.getItems().add(new CommandFormat("upload", "upload INDEX i/FilePath[:Description]"));
        helpTable.getItems().add(new CommandFormat("viewimage", "viewimage INDEX"));
        helpTable.getItems().add(new CommandFormat("help", "help"));
        helpTable.getItems().add(new CommandFormat("match", "match"));
        helpTable.getItems().add(new CommandFormat("favourite", "favourite INDEX"));
        helpTable.getItems().add(new CommandFormat("fw", "fw"));
        helpTable.getItems().add(new CommandFormat("stats", "stats"));
        helpTable.getItems().add(new CommandFormat("rm", "rm"));

        //@@author {howardwhw2}-reused
        //Reused from https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
        // with minor modifications
        //warps text in format column automatically
        formatColumn.setCellFactory(tc -> {
            TableCell<CommandFormat, String> cell = new TableCell<>();
            Text text = new Text();
            text.getStyleClass().add("my-cell-text");
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(formatColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }

}

