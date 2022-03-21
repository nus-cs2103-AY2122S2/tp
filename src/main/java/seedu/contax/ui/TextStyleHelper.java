package seedu.contax.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextStyleHelper {

    /**
     * Parser to identify bold/italic components within a string
     * @param text String to parse
     * @return List of Javafx Text elements
     */
    public static List<Text> formattedTextParser(String text) {
        ArrayList<Text> resultingList = new ArrayList<>();
        boolean bold = false;
        boolean italic = false;
        boolean boldAndItalic = false;
        int start = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '*') {
                //close bold/italic section
                if (bold || italic || boldAndItalic) {
                    if (italic) {
                        resultingList.add(newText(text.substring(start, i), true, false));
                        start = i + 1;
                        italic = false;
                        continue;
                    }
                    if (bold && text.charAt(i + 1) == '*') {
                        resultingList.add(newText(text.substring(start, i), false, true));
                        bold = false;
                        i += 1;
                        start = i + 1;
                        continue;
                    }
                    if (boldAndItalic && text.charAt(i + 1) == '*' && text.charAt(i + 2) == '*') {
                        resultingList.add(newText(text.substring(start, i), true, true));
                        boldAndItalic = false;
                        i += 2;
                        start = i + 1;
                        continue;
                    }
                    //Open bold/italic section
                } else if (text.charAt(i + 1) != '*') {
                    resultingList.add(newText(text.substring(start, i), false, false));
                    start = i + 1;
                    italic = true;
                } else if (text.charAt(i + 2) != '*') {
                    resultingList.add(newText(text.substring(start, i), false, false));
                    start = i + 2;
                    i += 1;
                    bold = true;
                } else {
                    resultingList.add(newText(text.substring(start, i), false, false));
                    start = i + 3;
                    i += 2;
                    boldAndItalic = true;
                }
            } else if (i == (text.length() - 1)) {
                resultingList.add(newText(text.substring(start, i + 1), false, false));
            }
        }

        return resultingList;
    }

    private static Text newText(String text, boolean bold, boolean italic) {
        Text newText = new Text(text);
        if (bold && italic) {
            newText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 17));
        } else if (italic) {
            newText.setFont(Font.font("Arial", FontPosture.ITALIC, 17));
        } else if (bold) {
            newText.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        } else {
            newText.setFont(Font.font("Arial", 17));
        }
        newText.setFill(Color.WHITE);
        return newText;
    }
}
