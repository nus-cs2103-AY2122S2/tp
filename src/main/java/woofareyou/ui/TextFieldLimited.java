package woofareyou.ui;

import javafx.scene.control.TextField;


public class TextFieldLimited extends TextField {

    private int maxStringLength;

    /**
     *  Constructs a new TextFieldLimited Object.
     */
    public TextFieldLimited() {
        this.maxStringLength = 10;
    }

    /**
     * Sets a maximum string length.
     * @param maxStringLength user input length
     */
    public void setMaxStringLength(int maxStringLength) {
        this.maxStringLength = maxStringLength;
    }

    /**
     * Replaces a range of characters with the given text as long as the length of the text does not
     * exceed the maximum limit.
     * @param start Start index in string.
     * @param end End index in string.
     * @param text User input.
     */
    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxStringLength) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * Replaces selection with a given limited string.
     * @param text Selection text.
     */
    @Override
    public void replaceSelection(String text) {
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxStringLength) {
            if (text.length() > maxStringLength - getText().length()) {
                text = text.substring(0, maxStringLength - getText().length());
            }
            super.replaceSelection(text);
        }
    }
}
