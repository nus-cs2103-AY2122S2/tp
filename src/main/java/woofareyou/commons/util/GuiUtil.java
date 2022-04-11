package woofareyou.commons.util;

/**
 * A container for GUI specific utility functions
 */
public class GuiUtil {

    /**
     * A utility function to check if the card index is even.
     * @param index the index of the card.
     * @return true if index is even, false otherwise.
     */
    public static boolean isEvenIndexCard(int index) {
        return index % 2 == 0;
    }
}
