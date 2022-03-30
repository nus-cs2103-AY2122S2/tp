package seedu.address.ui;

import javafx.scene.control.TextField;


public class TextFieldLimited extends TextField {

    private int maxStringLength;

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

    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxStringLength) {
            super.replaceText(start, end, text);
        }
    }

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
