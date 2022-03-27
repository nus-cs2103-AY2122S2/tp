package seedu.ibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;

public class ParserUtilTest {

    private static final String VALID_NAME = "apple";
    private static final String VALID_CATEGORY = "fruit";
    private static final String VALID_EXPIRY_DATE = "2022-05-05";
    private static final String VALID_DESCRIPTION = "import from South Africa";
    private static final String VALID_PRICE = "4.95";

    private static final String INVALID_NAME = "&blah&";
    private static final String INVALID_CATEGORY = "!bleh!";
    private static final String INVALID_EXPIRY_DATE = "2020-99-10";
    private static final String INVALID_DESCRIPTION = "@bluh@";
    private static final String INVALID_PRICE = "-1.2";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PRODUCT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PRODUCT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseCategory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCategory((String) null));
    }

    @Test
    public void parseCategory_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory(INVALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithoutWhitespace_returnsCategory() throws Exception {
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, ParserUtil.parseCategory(VALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithWhitespace_returnsTrimmedCategory() throws Exception {
        String categoryWithWhitespace = WHITESPACE + VALID_CATEGORY + WHITESPACE;
        Category expectedCategory = new Category(VALID_CATEGORY);
        assertEquals(expectedCategory, ParserUtil.parseCategory(categoryWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpiryDate((String) null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpiryDate(INVALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(VALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
        String expiryDateWithWhitespace = WHITESPACE + VALID_EXPIRY_DATE + WHITESPACE;
        ExpiryDate expectedExpiryDate = new ExpiryDate(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(expiryDateWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice((String) null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }
}
