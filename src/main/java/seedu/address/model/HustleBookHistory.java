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
     * Provides the recent/next data state of the HustleBook.
     * @return The recent/next data state of the HustleBook.
     * @throws IndexOutOfBoundsException If no commands left to redo.
     */
    public ReadOnlyHustleBook getNextState() throws IndexOutOfBoundsException,
            NullPointerException {
        ReadOnlyHustleBook result = historyList.get(currStatePointer + 1);
        currStatePointer = Math.min(historyList.size() - 1, currStatePointer + 1);
        return result;
    }

    /**
     * Updates the History with new updated data state of HustleBook.
     * When HustleBook data is updated, not through undo/redo command and new info is added or existing info modified,
     * all previously existed possible info that could have been redo will be cleared.
     * No further redo can be done.
     *
     * @param readOnlyHustleBook The new modified data state of HustleBook.
     */
    public void update(ReadOnlyHustleBook readOnlyHustleBook) {
        ReadOnlyHustleBook cloneBook = new HustleBook(readOnlyHustleBook);
        currStatePointer++;
        historyList.add(currStatePointer, cloneBook);
        int size = historyList.size();
        // Clears all possible future redo by clearing the elements in the sublist
        historyList.subList(currStatePointer + 1, size).clear();
    }

    /**
     * Checks if the history is empty.
     * @return True if history is empty.
     */
    public boolean isEmpty() {
        return historyList.isEmpty();
    }

    /**
     * Clears the stored history.
     */
    public void clear() {
        this.historyList = new ArrayList<>();
        this.currStatePointer = -1;
    }

    /**
     * Gets the current state of HustleBook
     * @return The current state of the HustleBook
     * @throws IndexOutOfBoundsException When historyList is empty
     */
    public ReadOnlyHustleBook getCurrState() throws IndexOutOfBoundsException {
        return historyList.get(currStatePointer);
    }
}
