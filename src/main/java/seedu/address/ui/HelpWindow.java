package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListLessonsCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.commands.ViewLessonInfoCommand;
import seedu.address.logic.commands.ViewStudentInfoCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    /**
     * Class to fill TableView for help commands
     */
    public static class CommandCard {

        public final SimpleStringProperty description;
        public final SimpleStringProperty commandWord;
        public final SimpleStringProperty commandShortcut;

        /**
         * Constructor for CommandCard
         *
         * @param description command description
         * @param commandWord command word
         * @param commandShortcut command shortcut
         */
        public CommandCard(String description, String commandWord, String commandShortcut) {
            this.description = new SimpleStringProperty(description);
            this.commandWord = new SimpleStringProperty(commandWord);
            this.commandShortcut = new SimpleStringProperty(commandShortcut);
        }

        /**
         * Getter for description
         *
         * @return description
         */
        public String getDescription() {
            return description.get();
        }

        /**
         * Getter for command word
         *
         * @return command word
         */
        public String getCommandWord() {
            return commandWord.get();
        }

        /**
         * Getter for command shortcut
         *
         * @return command shortcut
         */
        public String getCommandShortcut() {
            return commandShortcut.get();
        }
    }

    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103t-w11-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: ";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Hyperlink helpLink;

    private final HostServices hostServices;

    private ObservableList<CommandCard> list = FXCollections.observableArrayList();

    @FXML
    private TableView<CommandCard> commandView;

    @FXML
    private TableColumn<CommandCard, String> description;

    @FXML
    private TableColumn<CommandCard, String> commandWord;

    @FXML
    private TableColumn<CommandCard, String> commandShortcut;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        hostServices = MainApp.getInstance();
        initCol();
        loadData();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    private void loadData() {
        list.addAll(
                new CommandCard(AddStudentCommand.COMMAND_DESCRIPTION, AddStudentCommand.COMMAND_WORD,
                        AddStudentCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(AddLessonCommand.COMMAND_DESCRIPTION, AddLessonCommand.COMMAND_WORD,
                        AddLessonCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(DeleteStudentCommand.COMMAND_DESCRIPTION, DeleteStudentCommand.COMMAND_WORD,
                        DeleteStudentCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(DeleteLessonCommand.COMMAND_DESCRIPTION, DeleteLessonCommand.COMMAND_WORD,
                        DeleteLessonCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(EditStudentCommand.COMMAND_DESCRIPTION, EditStudentCommand.COMMAND_WORD,
                        EditStudentCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(EditLessonCommand.COMMAND_DESCRIPTION, EditLessonCommand.COMMAND_WORD,
                        EditLessonCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(ListStudentsCommand.COMMAND_DESCRIPTION, ListStudentsCommand.COMMAND_WORD,
                        ListStudentsCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(ListLessonsCommand.COMMAND_DESCRIPTION, ListLessonsCommand.COMMAND_WORD,
                        ListLessonsCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(FindStudentCommand.COMMAND_DESCRIPTION, FindStudentCommand.COMMAND_WORD,
                        FindStudentCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(FindLessonCommand.COMMAND_DESCRIPTION, FindLessonCommand.COMMAND_WORD,
                        FindLessonCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(AssignCommand.COMMAND_DESCRIPTION, AssignCommand.COMMAND_WORD,
                        AssignCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(UnassignCommand.COMMAND_DESCRIPTION, UnassignCommand.COMMAND_WORD,
                        UnassignCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(ViewStudentInfoCommand.COMMAND_DESCRIPTION, ViewStudentInfoCommand.COMMAND_WORD,
                        ViewStudentInfoCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(ViewLessonInfoCommand.COMMAND_DESCRIPTION, ViewLessonInfoCommand.COMMAND_WORD,
                        ViewLessonInfoCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(HelpCommand.COMMAND_DESCRIPTION, HelpCommand.COMMAND_WORD,
                        HelpCommand.SHORTENED_COMMAND_WORD),
                new CommandCard(ExitCommand.COMMAND_DESCRIPTION, ExitCommand.COMMAND_WORD,
                        ExitCommand.SHORTENED_COMMAND_WORD)
        );
        commandView.getItems().addAll(list);
    }
    private void initCol () {
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        commandWord.setCellValueFactory(new PropertyValueFactory<>("commandWord"));
        commandShortcut.setCellValueFactory(new PropertyValueFactory<>("commandShortcut"));
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
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Opens the hyperlink using the default system browser.
     */
    @FXML
    public void openLink() {
        hostServices.showDocument(USERGUIDE_URL);
    }
}
