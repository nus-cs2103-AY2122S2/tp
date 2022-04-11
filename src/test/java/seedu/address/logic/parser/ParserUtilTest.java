// package seedu.address.logic.parser;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static seedu.address.logic.parser.RecipeBookParserUtil.MESSAGE_INVALID_INDEX;
// import static seedu.address.testutil.Assert.assertThrows;
// import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
// import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
//
// import java.util.*;
// import java.util.stream.Collectors;
//
// import org.junit.jupiter.api.Test;
//
// import seedu.address.logic.parser.exceptions.ParseException;
// import seedu.address.model.recipe.Name;
// import seedu.address.model.recipe.CompletionTime;
// import seedu.address.model.recipe.ServingSize;
// import seedu.address.model.recipe.Ingredient;
// import seedu.address.model.recipe.Step;
// import seedu.address.model.tag.Tag;
// import seedu.address.newstorage.JsonAdaptedIngredient;
// import seedu.address.newstorage.JsonAdaptedStep;
// import seedu.address.newstorage.JsonAdaptedTag;
//
// public class ParserUtilTest {
//     private static final String INVALID_NAME = "";
//     private static final Integer INVALID_COMPLETION_TIME = -12;
//     private static final String INVALID_STEP = "";
//     private static final Integer INVALID_SERVING_SIZE = -5;
//     private static final String INVALID_TAG = "";
//     private static final String INVALID_INGREDIENT_NAME = "Spaghetti";
//     private static final JsonAdaptedIngredient INVALID_INGREDIENT =
//             new JsonAdaptedIngredient("", "-5", "");
//
//     //Extract parameters from Valid Recipe AGLIO_OGLIO
//     private static final String VALID_NAME = AGLIO_OLIO.getName().toString();
//     private static final List<JsonAdaptedIngredient> VALID_INGREDIENTS =
//             AGLIO_OLIO.getIngredients().stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList());
//     private static final Integer VALID_COMPLETION_TIME = AGLIO_OLIO.getCompletionTime().value;
//     private static final List<JsonAdaptedStep> VALID_STEPS =
//             AGLIO_OLIO.getSteps().stream().map(JsonAdaptedStep::new).collect(Collectors.toList());
//     private static final Integer VALID_SERVING_SIZE = AGLIO_OLIO.getServingSize().value;
//     private static final List<JsonAdaptedTag> VALID_TAGS =
//             AGLIO_OLIO.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList());
//
//     private static final String WHITESPACE = " \t\r\n";
//
//     @Test
//     public void parseIndex_invalidInput_throwsParseException() {
//         assertThrows(ParseException.class, () -> RecipeBookParserUtil.parseIndex("10 a"));
//     }
//
//     @Test
//     public void parseIndex_outOfRangeInput_throwsParseException() {
//         assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
//             -> RecipeBookParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
//     }
//
//     @Test
//     public void parseIndex_validInput_success() throws Exception {
//         // No whitespaces
//         assertEquals(INDEX_FIRST_RECIPE, RecipeBookParserUtil.parseIndex("1"));
//
//         // Leading and trailing whitespaces
//         assertEquals(INDEX_FIRST_RECIPE, RecipeBookParserUtil.parseIndex("  1  "));
//     }
//
//     @Test
//     public void parseName_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseName((String) null));
//     }
//
//     @Test
//     public void parseName_invalidValue_throwsParseException() {
//         assertThrows(ParseException.class, () -> RecipeBookParserUtil.parseName(INVALID_NAME));
//     }
//
//     @Test
//     public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
//         Name expectedName = new Name(VALID_NAME);
//         assertEquals(expectedName, RecipeBookParserUtil.parseName(VALID_NAME));
//     }
//
//     @Test
//     public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
//         String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
//         Name expectedName = new Name(VALID_NAME);
//         assertEquals(expectedName, RecipeBookParserUtil.parseName(nameWithWhitespace));
//     }
//
//     @Test
//     public void parseIngredient_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseIngredient((String) null));
//     }
//
//     @Test
//     public void parseIngredient_invalidValue_throwsParseException() {
//         assertThrows(ParseException.class, () ->
//         RecipeBookParserUtil.parseIngredient(String.valueOf(INVALID_INGREDIENT)));
//     }
//
//     @Test
//     public void parseIngredient_validValueWithoutWhitespace_returnsIngredient() throws Exception {
//         Ingredient expectedIngredient = new Ingredient(VALID_INGREDIENTS, VALID_SERVING_SIZE);
//         assertEquals(expectedIngredient, RecipeBookParserUtil.parseIngredient(String.valueOf(VALID_INGREDIENTS)));
//     }
//
//     @Test
//     public void parseIngredient_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
//         String phoneWithWhitespace = WHITESPACE + VALID_INGREDIENTS + WHITESPACE;
//         Ingredient expectedIngredient = new (VALID_INGREDIENTS,);
//         assertEquals(expectedIngredient, RecipeBookParserUtil.parseIngredient(phoneWithWhitespace));
//     }
//
//     @Test
//     public void parseAddress_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseAddress((String) null));
//     }
//
//     @Test
//     public void parseAddress_invalidValue_throwsParseException() {
//         assertThrows(ParseException.class, () -> RecipeBookParserUtil.parseAddress(INVALID_ADDRESS));
//     }
//
//     @Test
//     public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
//         Address expectedAddress = new Address(VALID_ADDRESS);
//         assertEquals(expectedAddress, RecipeBookParserUtil.parseAddress(VALID_ADDRESS));
//     }
//
//     @Test
//     public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
//         String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
//         Address expectedAddress = new Address(VALID_ADDRESS);
//         assertEquals(expectedAddress, RecipeBookParserUtil.parseAddress(addressWithWhitespace));
//     }
//
//     @Test
//     public void parseEmail_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseEmail((String) null));
//     }
//
//     @Test
//     public void parseEmail_invalidValue_throwsParseException() {
//         assertThrows(ParseException.class, () -> RecipeBookParserUtil.parseEmail(INVALID_EMAIL));
//     }
//
//     @Test
//     public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
//         Email expectedEmail = new Email(VALID_EMAIL);
//         assertEquals(expectedEmail, RecipeBookParserUtil.parseEmail(VALID_EMAIL));
//     }
//
//     @Test
//     public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
//         String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
//         Email expectedEmail = new Email(VALID_EMAIL);
//         assertEquals(expectedEmail, RecipeBookParserUtil.parseEmail(emailWithWhitespace));
//     }
//
//     @Test
//     public void parseTag_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseTag(null));
//     }
//
//     @Test
//     public void parseTag_invalidValue_throwsParseException() {
//         assertThrows(ParseException.class, () -> RecipeBookParserUtil.parseTag(INVALID_TAG));
//     }
//
//     @Test
//     public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
//         Tag expectedTag = new Tag(VALID_TAG_1);
//         assertEquals(expectedTag, RecipeBookParserUtil.parseTag(VALID_TAG_1));
//     }
//
//     @Test
//     public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
//         String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
//         Tag expectedTag = new Tag(VALID_TAG_1);
//         assertEquals(expectedTag, RecipeBookParserUtil.parseTag(tagWithWhitespace));
//     }
//
//     @Test
//     public void parseTags_null_throwsNullPointerException() {
//         assertThrows(NullPointerException.class, () -> RecipeBookParserUtil.parseTags(null));
//     }
//
//     @Test
//     public void parseTags_collectionWithInvalidTags_throwsParseException() {
//         assertThrows(ParseException.class,
//         () -> RecipeBookParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
//     }
//
//     @Test
//     public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
//         assertTrue(RecipeBookParserUtil.parseTags(Collections.emptyList()).isEmpty());
//     }
//
//     @Test
//     public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
//         Set<Tag> actualTagSet = RecipeBookParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
//         Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
//
//         assertEquals(expectedTagSet, actualTagSet);
//     }
// }
