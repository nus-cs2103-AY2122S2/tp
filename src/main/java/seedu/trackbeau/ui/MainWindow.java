package seedu.trackbeau.ui;

import static seedu.trackbeau.logic.parser.customer.AddCustomerCommandParser.EMPTY_HAIR_TYPE;
import static seedu.trackbeau.logic.parser.customer.AddCustomerCommandParser.EMPTY_SKIN_TYPE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import seedu.trackbeau.ui.charts.AllergyChartWindow;
import seedu.trackbeau.ui.charts.HairChartWindow;
import seedu.trackbeau.ui.charts.MonthlyCustomerChartWindow;
import seedu.trackbeau.ui.charts.ServiceChartWindow;
import seedu.trackbeau.ui.charts.SkinChartWindow;
import seedu.trackbeau.ui.charts.StaffChartWindow;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CustomerListPanel customerListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StaffChartWindow staffChartWindow;
    private ServiceChartWindow serviceChartWindow;
    private AllergyChartWindow allergyChartWindow;
    private SkinChartWindow skinChartWindow;
    private HairChartWindow hairChartWindow;
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
        staffChartWindow = new StaffChartWindow();
        serviceChartWindow = new ServiceChartWindow();
        allergyChartWindow = new AllergyChartWindow();
        skinChartWindow = new SkinChartWindow();
        hairChartWindow = new HairChartWindow();
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
     * Opens the staff chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotStaffChart() {
        HashMap<String, Integer> staffMap = this.getStaffMap();
        addPieChartData(staffMap, staffChartWindow.getPieChart());
        if (!staffChartWindow.isShowing()) {
            staffChartWindow.show();
        } else {
            staffChartWindow.focus();
        }
    }

    /**
     * Opens the service chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotServiceChart() {
        HashMap<String, Integer> serviceMap = this.getServiceMap();
        addPieChartData(serviceMap, serviceChartWindow.getPieChart());
        if (!serviceChartWindow.isShowing()) {
            serviceChartWindow.show();
        } else {
            serviceChartWindow.focus();
        }
    }

    /**
     * Opens the allergy chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotAllergyChart() {
        HashMap<String, Integer> allergyMap = this.getAllergyMap();
        addPieChartData(allergyMap, allergyChartWindow.getPieChart());
        if (!allergyChartWindow.isShowing()) {
            allergyChartWindow.show();
        } else {
            allergyChartWindow.focus();
        }
    }

    /**
     * Opens the skin type chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotSkinChart() {
        HashMap<String, Integer> skinTypeMap = this.getSkinTypeMap();
        addPieChartData(skinTypeMap, skinChartWindow.getPieChart());
        if (!skinChartWindow.isShowing()) {
            skinChartWindow.show();
        } else {
            skinChartWindow.focus();
        }
    }

    /**
     * Opens the hair type chart window or focuses on it if it's already opened.
     */
    @FXML
    public void plotHairChart() {
        HashMap<String, Integer> hairTypeMap = this.getHairTypeMap();
        addPieChartData(hairTypeMap, hairChartWindow.getPieChart());
        if (!hairChartWindow.isShowing()) {
            hairChartWindow.show();
        } else {
            hairChartWindow.focus();
        }
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
            if (dataCount == 10) {
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
     * Returns a hashmap with the staff name as the key and count as the value.
     * Count refers to the number of customers who chose the staff as their favorite.
     *
     * @return HashMap
     */
    HashMap<String, Integer> getStaffMap() {
        HashMap<String, Integer> staffMap = new HashMap<String, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            for (Tag tag : customer.getStaffs()) {
                String key = tag.tagName.toUpperCase(); //key is the staff name
                if (staffMap.containsKey(key)) {
                    staffMap.put(key, staffMap.get(key) + 1);
                } else {
                    staffMap.put(key, 1);
                }
            }
            ;
        }
        return staffMap;
    }

    /**
     * Returns a hashmap with the service name as the key and count as the value.
     * Count refers to the number of customers who chose the service as their favorite.
     *
     * @return HashMap
     */
    HashMap<String, Integer> getServiceMap() {
        HashMap<String, Integer> serviceMap = new HashMap<String, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            for (Tag tag : customer.getServices()) {
                String key = tag.tagName.toUpperCase(); //key is the service name
                if (serviceMap.containsKey(key)) {
                    serviceMap.put(key, serviceMap.get(key) + 1);
                } else {
                    serviceMap.put(key, 1);
                }
            }
            ;
        }
        return serviceMap;
    }

    /**
     * Returns a hashmap with the allergy name as the key and count as the value.
     * Count refers to the number of customers who have the allergy.
     *
     * @return HashMap
     */
    HashMap<String, Integer> getAllergyMap() {
        HashMap<String, Integer> allergyMap = new HashMap<String, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            for (Tag tag : customer.getAllergies()) {
                String key = tag.tagName.toUpperCase(); //key is the allergy name
                if (allergyMap.containsKey(key)) {
                    allergyMap.put(key, allergyMap.get(key) + 1);
                } else {
                    allergyMap.put(key, 1);
                }
            }
            ;
        }
        return allergyMap;
    }

    /**
     * Returns a hashmap with the skin type name as the key and count as the value.
     * Count refers to the number of customers who have the skin type.
     *
     * @return HashMap
     */
    HashMap<String, Integer> getSkinTypeMap() {
        HashMap<String, Integer> skinTypeMap = new HashMap<String, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            String key = customer.getSkinType().toString().toUpperCase();
            if (!key.equals(EMPTY_SKIN_TYPE.toUpperCase())) {
                if (skinTypeMap.containsKey(key)) {
                    skinTypeMap.put(key, skinTypeMap.get(key) + 1);
                } else {
                    skinTypeMap.put(key, 1);
                }
            }
        }
        return skinTypeMap;
    }

    /**
     * Returns a hashmap with the hair type name as the key and count as the value.
     * Count refers to the number of customers who have the hair type.
     *
     * @return HashMap
     */
    HashMap<String, Integer> getHairTypeMap() {
        HashMap<String, Integer> hairTypeMap = new HashMap<String, Integer>();
        ObservableList<Customer> customerList = logic.getFilteredCustomerList();
        for (Customer customer : customerList) {
            String key = customer.getHairType().toString().toUpperCase(); //key is the hair type name
            if (!key.equals(EMPTY_HAIR_TYPE.toUpperCase())) {
                if (hairTypeMap.containsKey(key)) {
                    hairTypeMap.put(key, hairTypeMap.get(key) + 1);
                } else {
                    hairTypeMap.put(key, 1);
                }
            }
        }
        return hairTypeMap;
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
