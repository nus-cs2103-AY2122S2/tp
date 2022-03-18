package seedu.ibook.ui;

public abstract class UiComponent<T> extends UiPart<T> {

    private final MainWindow mainWindow;

    /**
     * Initializes a new UiComponent.
     *
     * @param filePath The filepath of the FXML file.
     * @param mainWindow The Main Window that this component resides on.
     */
    public UiComponent(String filePath, MainWindow mainWindow) {
        super(filePath);
        this.mainWindow = mainWindow;
    }

    /**
     * Initializes a new UiComponent with a root object
     *
     * @param filePath The filepath of the FXML file.
     * @param mainWindow The Main Window that this component resides on.
     * @param stage the root object
     */
    public UiComponent(String filePath, MainWindow mainWindow, T stage) {
        super(filePath, stage);
        this.mainWindow = mainWindow;
    }

    /**
     * Get the {@code MainWindow}.
     *
     * @return The {@code MainWindow} that this component resides on.
     */
    public MainWindow getMainWindow() {
        return this.mainWindow;
    }
}
