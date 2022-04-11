package seedu.trackbeau.ui;

import static seedu.trackbeau.logic.parser.customer.AddCustomerCommandParser.EMPTY_HAIR_TYPE;
import static seedu.trackbeau.logic.parser.customer.AddCustomerCommandParser.EMPTY_SKIN_TYPE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.logic.Logic;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.tag.Tag;
import seedu.trackbeau.ui.charts.MonthlyCustomerChartWindow;
import seedu.trackbeau.ui.charts.PieChartWindow;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final int MAX_POINTS = 10;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CustomerListPanel customerListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private PieChartWindow staffChartWindow;
    private PieChartWindow serviceChartWindow;
    private PieChartWindow allergyChartWindow;
    private PieChartWindow skinChartWindow;
    private PieChartWindow hairChartWindow;
    private MonthlyCustomerChartWindow monthlyCustomerChartWindow;
    private ServiceListPanel serviceListPanel;
    private BookingListPanel bookingListPanel;
    private SchedulePanel schedulePanel;
    private Panel currPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane detailsPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Label customersLabel;

    @FXML
    private Label servicesLabel;

    @FXML
    private Label bookingsLabel;

    @FXML
    private Label scheduleLabel;

    private ArrayList<Label> labels;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
        staffChartWindow = new PieChartWindow("Staff Preference Chart",
                "Most Popular Staff Amongst Customers", "preferred staff");
        serviceChartWindow = new PieChartWindow("Service Preference Chart",
                "Most In-Demand Services Amongst Customers", "preferred services");
        allergyChartWindow = new PieChartWindow("Allergy Preference Chart",
                "Common Allergies Amongst Customers", "allergy");
        skinChartWindow = new PieChartWindow("Skin Type Chart",
                "Common Skin Types Amongst Customers", "skin type");
        hairChartWindow = new PieChartWindow("Hair Type Chart",
                "Common Hair Types Amongst Customers", "hair type");
        int maxMonthlyCustomerCount = this
            .getMonthlyCustomerMaxCount(this.getMonthlyCustomerMap());
        int totalCustomerCount = this.getCustomerCount();
        monthlyCustomerChartWindow = new MonthlyCustomerChartWindow(maxMonthlyCustomerCount, totalCustomerCount);

        this.labels = new ArrayList<>();
        this.labels.add(customersLabel);
        this.labels.add(servicesLabel);
        this.labels.add(bookingsLabel);
        this.labels.add(scheduleLabel);

        customersLabel.getStyleClass().add("selected");
        for (Label l : this.labels) {
            setLabelOnMouseClickEvent(l);
        }
    }

    // @@author flairekq-reused
    // Reused from https://stackoverflow.com/questions/49097747/javafx-determining-which-label-has-been-clicked
    // with minor modifications
    private void setLabelOnMouseClickEvent(Label label) {
        label.setOnMouseClicked(event -> {
            for (Label l : labels) {
                l.getStyleClass().remove("selected");
            }
            label.getStyleClass().add("selected");
            switchPanel(stringToPanel(label.getId()));
        });
    }
    // @@author

    private Panel stringToPanel(String id) {
        switch (id) {
        case "customersLabel":
            return Panel.CUSTOMER_PANEL;
        case "servicesLabel":
            return Panel.SERVICE_PANEL;
        case "bookingsLabel":
            return Panel.BOOKING_PANEL;
        case "scheduleLabel":
            return Panel.SCHEDULE_PANEL;
        default:
            return Panel.NO_CHANGE;
        }
    }

    private void switchPanel(Panel panel) {
        detailsPanelPlaceholder.getChildren().clear();
        for (Label l : labels) {
            l.getStyleClass().remove("selected");
        }

        switch (panel) {
        case CUSTOMER_PANEL:
            customersLabel.getStyleClass().add("selected");
            customerListPanel = new CustomerListPanel(logic.getFilteredCustomerList());
            detailsPanelPlaceholder.getChildren().add(customerListPanel.getRoot());
            break;
        case SERVICE_PANEL:
            servicesLabel.getStyleClass().add("selected");
            serviceListPanel = new ServiceListPanel(logic.getFilteredServiceList());
            detailsPanelPlaceholder.getChildren().add(serviceListPanel.getRoot());
            break;
        case BOOKING_PANEL:
            bookingsLabel.getStyleClass().add("selected");
            bookingListPanel = new BookingListPanel(logic.getFilteredBookingList());
            detailsPanelPlaceholder.getChildren().add(bookingListPanel.getRoot());
            break;
        case SCHEDULE_PANEL:
            scheduleLabel.getStyleClass().add("selected");
            schedulePanel = new SchedulePanel(logic.getFilteredBookingList(), logic.getSelectedDate());
            detailsPanelPlaceholder.getChildren().add(schedulePanel.getRoot());
            break;
        default:
            // nothing to add to details panel placeholder
            break;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        customerListPanel = new CustomerListPanel(logic.getFilteredCustomerList());
        detailsPanelPlaceholder.getChildren().add(customerListPanel.getRoot());
        currPanel = Panel.CUSTOMER_PANEL;

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTrackBeauFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Plots windows that contains pie charts.
     */
    @FXML
    public void plotPieChart(PieChartWindow window, HashMap infoMap) {
        addPieChartData(infoMap, window.getPieChart());
        if (!window.isShowing()) {
            window.show();
        } else {
            window.focus();
        }
    }

    /**
     * Opens the staff chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotStaffChart() {
        plotPieChart(staffChartWindow, getTagMap("getStaffs"));
    }

    /**
     * Opens the service chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotServiceChart() {
        plotPieChart(serviceChartWindow, getTagMap("getServices"));
    }

    /**
     * Opens the allergy chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotAllergyChart() {
        plotPieChart(allergyChartWindow, getTagMap("getAllergies"));
    }

    /**
     * Opens the skin type chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotSkinChart() {
        plotPieChart(skinChartWindow, getTypeMap("getSkinType", EMPTY_SKIN_TYPE));
    }

    /**
     * Opens the hair type chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotHairChart() {
        plotPieChart(hairChartWindow, getTypeMap("getHairType", EMPTY_HAIR_TYPE));
    }

    /**
     * Opens the monthly customer chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotMonthlyCustomerChart() {
        HashMap<Integer, Integer> monthlyCustomerMap = this.getMonthlyCustomerMap();
        int totalCustomerCount = this.getCustomerCount();
        int maxMonthlyCustomerCount = this
            .getMonthlyCustomerMaxCount(monthlyCustomerMap);
        //update the axis limits manually
        monthlyCustomerChartWindow = new MonthlyCustomerChartWindow(maxMonthlyCustomerCount,
            totalCustomerCount);
        addMonthlyCustomerChartData(monthlyCustomerMap,
            monthlyCustomerChartWindow.getLineChart(), "Customers Gained This Year");
        if (!monthlyCustomerChartWindow.isShowing()) {
            monthlyCustomerChartWindow.show();
        } else {
            monthlyCustomerChartWindow.focus();
        }
    }

    /**
     * Adds data to the pie charts in a specific window.
     */
    void addPieChartData(HashMap<String, Integer> hm, PieChart pieChart) {
        pieChart.getData().clear();
        int dataCount = 0; //add only top 10 most count to prevent cluttering
        Map<String, Integer> map = sortByValue(hm);
        for (String key : map.keySet()) {
            if (dataCount == MAX_POINTS) {
                break;
            }
            String detail = key;
            Integer count = map.get(key);
            String dataLabel = String.format("%s: %d", detail, count);
            pieChart.getData()
                .add(new PieChart.Data(dataLabel, count));
            dataCount++;
        }
        //.layout() prevents labels from moving to top left after opening chart a few times
        pieChart.layout();
    }

    /**
     * Adds data to a line chart in a specific window.
     */
    void addMonthlyCustomerChartData(HashMap<Integer, Integer> hm, LineChart lineChart, String chartName) {
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        for (int key : hm.keySet()) {
            int month = key;
            int count = hm.get(key);
            series.getData().add(new XYChart.Data(month, count));
        }
        series.setName(chartName);
        lineChart.getData().add(series);
        //.layout() prevents labels from moving to top left after opening chart a few times
        lineChart.layout();
    }

    /**
     * Get maximum customer count gained a month in the current year to bound y-axis of chart.
     */
    int getMonthlyCustomerMaxCount(HashMap<Integer, Integer> hm) {
        int maxCount = 0;
        for (int key : hm.keySet()) {
            int count = hm.get(key);
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    /**
     * Get total customer count in TrackBeau.
     */
    int getCustomerCount() {
        int count = 0;
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            count++;
        }
        return count;
    }

    /**
    * Returns a hashmap for count of allergies, preferred services and staffs of customers, which are stored as tags.
    */
    HashMap<String, Integer> getTagMap(String methodName) {
        try {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            ObservableList<Customer> customerList = logic.getFilteredCustomerList();
            Class<?> customerClass = Class.forName("seedu.trackbeau.model.customer.Customer");
            Method method = customerClass.getDeclaredMethod(methodName);
            for (Customer customer : customerList) {
                Set<Tag> tagList = (Set<Tag>) method.invoke(customer);
                for (Tag tag : tagList) {
                    String key = tag.tagName.toUpperCase(); //key is the staff name
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 1);
                    }
                }
            }
            return map;
        } catch (NoSuchMethodException
                | ClassNotFoundException | InvocationTargetException
                | IllegalAccessException e) {
            logger.info("Invalid tag map");
            resultDisplay.setFeedbackToUser(e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Returns a hashmap for count of different skin type and hair type information of customer.
     */
    HashMap<String, Integer> getTypeMap(String methodName, String emptyType) {
        try {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            ObservableList<Customer> customerList = logic.getFilteredCustomerList();
            Class<?> customerClass = Class.forName("seedu.trackbeau.model.customer.Customer");
            Method method = customerClass.getDeclaredMethod(methodName);
            for (Customer customer : customerList) {
                String key = method.invoke(customer).toString().toUpperCase();
                if (!key.equals(emptyType.toUpperCase())) {
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 1);
                    }
                }
            }
            return map;
        } catch (NoSuchMethodException
                | ClassNotFoundException | InvocationTargetException
                | IllegalAccessException e) {
            logger.info("Invalid type map");
            resultDisplay.setFeedbackToUser(e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * Returns a hashmap with the month as the key and count as the value.
     * Count refers to the number of customers who have registered on that month this year.
     *
     * @return HashMap
     */
    HashMap<Integer, Integer> getMonthlyCustomerMap() {
        HashMap<Integer, Integer> monthlyCustomerMap = new HashMap<Integer, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        for (Customer customer : customerList) {
            Integer key = customer.getRegDate().value.getMonthValue();
            if (customer.getRegDate().value.getYear() == currentYear) {
                if (monthlyCustomerMap.containsKey(key)) {
                    monthlyCustomerMap.put(key, monthlyCustomerMap.get(key) + 1);
                } else {
                    monthlyCustomerMap.put(key, 1);
                }
            }
        }
        return monthlyCustomerMap;
    }


    /**
     * Returns a sorted HashMap by value.
     */
    //function is taken from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
    private HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
            new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        staffChartWindow.hide();
        serviceChartWindow.hide();
        allergyChartWindow.hide();
        skinChartWindow.hide();
        hairChartWindow.hide();
        monthlyCustomerChartWindow.hide();
        primaryStage.hide();
    }

    public CustomerListPanel getCustomerListPanel() {
        return customerListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.trackbeau.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isPlotStaffChart()) {
                plotStaffChart();
            }

            if (commandResult.isPlotServiceChart()) {
                plotServiceChart();
            }

            if (commandResult.isPlotAllergyChart()) {
                plotAllergyChart();
            }

            if (commandResult.isPlotSkinChart()) {
                plotSkinChart();
            }

            if (commandResult.isPlotMonthlyCustomerChart()) {
                plotMonthlyCustomerChart();
            }

            if (commandResult.isPlotHairChart()) {
                plotHairChart();
            }

            if (commandResult.getPanel() != Panel.NO_CHANGE) {
                switchPanel(commandResult.getPanel());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
