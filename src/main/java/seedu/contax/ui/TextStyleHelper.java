package seedu.contax.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Defines helper functions for text styling.
 */
public class TextStyleHelper {

    private static final String FONT_NAME = "Arial";
    private static final String MONOSPACED_FONT_NAME = "Courier New";
    private static final int FONT_SIZE = 17; //JavaFx accepts font size in px, converted to pt becomes 13pt

    /**
     * Parser to identify bold/italic/monospaced components within a string.
     *
     * @param text String to parse.
     * @return List of Javafx Text elements.
     */
    public static List<Text> formattedTextParser(String text) {
        ArrayList<Text> resultingList = new ArrayList<>();
        boolean bold = false;
        boolean italic = false;
        boolean boldAndItalic = false;
        boolean monospaced = false;
        int start = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '*') {
                boolean isLookaheadOneStar = (i + 1 < text.length()) && text.charAt(i + 1) == '*';
                boolean isLookaheadTwoStar = (i + 2 < text.length()) && text.charAt(i + 2) == '*';
                if (bold || italic || boldAndItalic) {
                    resultingList.add(createTextElement(text.substring(start, i), (boldAndItalic || bold),
                            boldAndItalic || italic));
                    i += (bold ? 1 : 0) + (boldAndItalic ? 2 : 0);
                    if (italic) {
                        italic = false;
                    } else if (bold && isLookaheadOneStar) {
                        bold = false;
                    } else if (boldAndItalic && isLookaheadTwoStar) {
                        boldAndItalic = false;
                    }
                } else {
                    resultingList.add(createTextElement(text.substring(start, i), false, false));
                    italic = !isLookaheadOneStar;
                    bold = isLookaheadOneStar && !isLookaheadTwoStar;
                    boldAndItalic = isLookaheadOneStar && isLookaheadTwoStar;
                    i = skipNumbers(bold, boldAndItalic, i);
                }
                start = i + 1;
            } else if (text.charAt(i) == '`') {
                monospaced = processMonospaced(monospaced, resultingList, text, start, i);
                start = i + 1;
            } else if (i == (text.length() - 1)) {
                resultingList.add(createTextElement(text.substring(start, i + 1), false, false));
            }
        }
        return resultingList;
    }

    /**
     * Helper function to do index skipping from the result.
     */
    private static int skipNumbers(boolean bold, boolean boldAndItalic, int i) {
        if (bold) {
            i += 1;
        } else if (boldAndItalic) {
            i += 2;
        }
        return i;
    }

    /**
     * Helper function to process monospaced text.
     */
    private static boolean processMonospaced(boolean currentState, List<Text> currentList,
                                             String text, int start, int current) {
        if (currentState) {
            currentList.add(createMonospacedText(text.substring(start, current)));
            return false;
        } else {
            currentList.add(createTextElement(text.substring(start, current), false, false));
            return true;
        }
    }

    /**
     * Helper function to create individual Text elements.
     */
    private static Text createTextElement(String text, boolean bold, boolean italic) {
        Text newText = new Text(text);
        if (bold && italic) {
            newText.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FontPosture.ITALIC, FONT_SIZE));
        } else if (italic) {
            newText.setFont(Font.font(FONT_NAME, FontPosture.ITALIC, FONT_SIZE));
        } else if (bold) {
            newText.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        } else {
            newText.setFont(Font.font(FONT_NAME, 17));
        }
        newText.setFill(Color.WHITE);
        return newText;
    }

    /**
     * Helper function to create monospaced Text elements.
     */
    private static Text createMonospacedText(String text) {
        Text newText = new Text(text);
        newText.setFill(Color.WHITE);
        newText.setFont(Font.font(MONOSPACED_FONT_NAME, FONT_SIZE));
        return newText;
    }
}
