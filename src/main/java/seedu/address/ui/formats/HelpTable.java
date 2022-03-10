package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class HelpTable extends UiPart<Region> {
    private static final String FXML = "HelpTable.fxml";

    @FXML
    private TableView<CommandFormat> helpTable;

    @FXML
    private TableColumn<CommandFormat, String> typeColumn;

    @FXML
    private TableColumn<CommandFormat, String> formatColumn;

    public HelpTable(){
        super(FXML);
        initializeTable();
    }

    private void initializeTable() {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("commandType"));
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("commandFormat"));
        helpTable.getItems().add(new CommandFormat("add","add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS\n"
                                                    + " pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE,\n t/USER_TYPE"));
        helpTable.getItems().add(new CommandFormat("clear", "clear"));
        helpTable.getItems().add(new CommandFormat("delete","delete INDEX"));
        helpTable.getItems().add(new CommandFormat("edit", "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]\n"
                                                    + " [pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE]\n [t/USER_TYPE]"));
        helpTable.getItems().add(new CommandFormat("find", "find KEYWORD [MORE_KEYWORDS]"));
        helpTable.getItems().add(new CommandFormat("list","list"));
        helpTable.getItems().add(new CommandFormat("help", "help"));
        helpTable.getItems().add(new CommandFormat("favourite", "favourite INDEX"));
    }

}

