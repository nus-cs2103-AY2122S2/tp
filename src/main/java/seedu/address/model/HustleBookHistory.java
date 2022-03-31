package seedu.address.model;

import java.util.ArrayList;

/**
 * Handles the HustleBook history.
 * Contains data of HustleBook before modification. Uses a Singleton design pattern.
 */
public class HustleBookHistory {
    private static HustleBookHistory hustleBookHistoryObj;
    private ArrayList<ReadOnlyHustleBook> historyList;
    private int currStatePointer;

    /**
     * Constructs the HustleBookHistory Object.
     */
    private HustleBookHistory() {
        historyList = new ArrayList<>();
        currStatePointer = -1;
    }

    /**
     * Allows only one instance of the History obj to be created and used.
     * @return The single hustleBookHistoryObj instance.
     */
    public static HustleBookHistory getInstance() {
        if (hustleBookHistoryObj == null) {
            hustleBookHistoryObj = new HustleBookHistory();
        }
        return hustleBookHistoryObj;
    }

    /**
     * Provides the last data state of the HustleBook.
     * @return The last data state of the HustleBook.
     * @throws IndexOutOfBoundsException If no commands left to undo.
     */
    public ReadOnlyHustleBook getPrevState() throws IndexOutOfBoundsException {
        ReadOnlyHustleBook result = historyList.get(currStatePointer - 1);
        currStatePointer = Math.max(-1, currStatePointer - 1);
        return result;
    }

    /**
     * Updates the History with new updated data state of HustleBook.
     * @param readOnlyHustleBook The new modified data state of HustleBook.
     */
    public void update(ReadOnlyHustleBook readOnlyHustleBook) {
        ReadOnlyHustleBook cloneBook = new HustleBook(readOnlyHustleBook);
        currStatePointer++;
        historyList.add(currStatePointer, cloneBook);
    }

    /**
     * Checks if the history is empty.
     * @return True is history is empty.
     */
    public boolean isEmpty() {
        return historyList.isEmpty();
    }
}
