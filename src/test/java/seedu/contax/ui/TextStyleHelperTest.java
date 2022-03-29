package seedu.contax.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextStyleHelperTest {
    private static final String FONT_NAME = "Arial";
    private static final String MONOSPACED_FONT_NAME = "Courier New";
    private static final int FONT_SIZE = 17;

    @Test
    public void parseTextWithItalic_listOfTextReturned() {
        String text = "Test*Italic*test";
        List<Text> fromParser = TextStyleHelper.formattedTextParser(text);
        assertEquals(fromParser.get(0).getText(), "Test");
        assertEquals(fromParser.get(0).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(0).getFill(), Color.WHITE);
        assertEquals(fromParser.get(1).getText(), "Italic");
        assertEquals(fromParser.get(1).getFont(), Font.font(FONT_NAME, FontPosture.ITALIC, FONT_SIZE));
        assertEquals(fromParser.get(1).getFill(), Color.WHITE);
        assertEquals(fromParser.get(2).getText(), "test");
        assertEquals(fromParser.get(2).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(2).getFill(), Color.WHITE);
    }

    @Test
    public void parseTextWithBold_listOfTextReturned() {
        String text = "Test**Bold**test";
        List<Text> fromParser = TextStyleHelper.formattedTextParser(text);
        assertEquals(fromParser.get(0).getText(), "Test");
        assertEquals(fromParser.get(0).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(0).getFill(), Color.WHITE);
        assertEquals(fromParser.get(1).getText(), "Bold");
        assertEquals(fromParser.get(1).getFont(), Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        assertEquals(fromParser.get(1).getFill(), Color.WHITE);
        assertEquals(fromParser.get(2).getText(), "test");
        assertEquals(fromParser.get(2).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(2).getFill(), Color.WHITE);
    }

    @Test
    public void parseTextWithBoldAndItalic_listOfTextReturned() {
        String text = "Test***BoldAndItalic***test";
        List<Text> fromParser = TextStyleHelper.formattedTextParser(text);
        assertEquals(fromParser.get(0).getText(), "Test");
        assertEquals(fromParser.get(0).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(0).getFill(), Color.WHITE);
        assertEquals(fromParser.get(1).getText(), "BoldAndItalic");
        assertEquals(fromParser.get(1).getFont(), Font.font(FONT_NAME, FontWeight.BOLD, FontPosture.ITALIC, FONT_SIZE));
        assertEquals(fromParser.get(1).getFill(), Color.WHITE);
        assertEquals(fromParser.get(2).getText(), "test");
        assertEquals(fromParser.get(2).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(2).getFill(), Color.WHITE);
    }

    @Test
    public void parseTextWithMonospaced_listOfTextReturned() {
        String text = "Test`monospaced`test";
        List<Text> fromParser = TextStyleHelper.formattedTextParser(text);
        assertEquals(fromParser.get(0).getText(), "Test");
        assertEquals(fromParser.get(0).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(0).getFill(), Color.WHITE);
        assertEquals(fromParser.get(1).getText(), "monospaced");
        assertEquals(fromParser.get(1).getFont(), Font.font(MONOSPACED_FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(1).getFill(), Color.WHITE);
        assertEquals(fromParser.get(2).getText(), "test");
        assertEquals(fromParser.get(2).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(2).getFill(), Color.WHITE);
    }

    @Test
    public void parseTextContainingAll_listOfTextReturned() {
        String text = "Test`monospaced`|*Italic*|**Bold**|***BoldAndItalic***|test";
        List<Text> fromParser = TextStyleHelper.formattedTextParser(text);
        assertEquals(fromParser.get(0).getText(), "Test");
        assertEquals(fromParser.get(0).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(0).getFill(), Color.WHITE);
        assertEquals(fromParser.get(1).getText(), "monospaced");
        assertEquals(fromParser.get(1).getFont(), Font.font(MONOSPACED_FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(1).getFill(), Color.WHITE);
        assertEquals(fromParser.get(2).getText(), "|");
        assertEquals(fromParser.get(2).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(2).getFill(), Color.WHITE);
        assertEquals(fromParser.get(3).getText(), "Italic");
        assertEquals(fromParser.get(3).getFont(), Font.font(FONT_NAME, FontPosture.ITALIC, FONT_SIZE));
        assertEquals(fromParser.get(3).getFill(), Color.WHITE);
        assertEquals(fromParser.get(4).getText(), "|");
        assertEquals(fromParser.get(4).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(4).getFill(), Color.WHITE);
        assertEquals(fromParser.get(5).getText(), "Bold");
        assertEquals(fromParser.get(5).getFont(), Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        assertEquals(fromParser.get(5).getFill(), Color.WHITE);
        assertEquals(fromParser.get(6).getText(), "|");
        assertEquals(fromParser.get(6).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(6).getFill(), Color.WHITE);
        assertEquals(fromParser.get(7).getText(), "BoldAndItalic");
        assertEquals(fromParser.get(7).getFont(), Font.font(FONT_NAME, FontWeight.BOLD, FontPosture.ITALIC, FONT_SIZE));
        assertEquals(fromParser.get(7).getFill(), Color.WHITE);
        assertEquals(fromParser.get(8).getText(), "|test");
        assertEquals(fromParser.get(8).getFont(), Font.font(FONT_NAME, FONT_SIZE));
        assertEquals(fromParser.get(8).getFill(), Color.WHITE);
    }
}
