package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void constructor_nullValueString_throwsNullPointerException() {
        FullMark op1FullMark = new FullMark(VALID_FULL_MARK_OP1);
        assertThrows(NullPointerException.class, () -> new Score(null, op1FullMark));
    }

    @Test
    public void constructor_nullFullMark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Score(VALID_SCORE_OP1, null));
    }

    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        FullMark op1FullMark = new FullMark(VALID_FULL_MARK_OP1);

        String invalidScore = "";
        assertThrows(IllegalArgumentException.class, () -> new Score(invalidScore, op1FullMark));
    }

    @Test
    public void isValidScore() {
        // null score
        assertThrows(NullPointerException.class, () -> Score.isValidScore(null));

        // invalid score
        assertFalse(Score.isValidScore("")); // empty string
        assertFalse(Score.isValidScore("  ")); // spaces only
        assertFalse(Score.isValidScore("abc")); // non-numeric
        assertFalse(Score.isValidScore("1a00")); // alphabet within input
        assertFalse(Score.isValidScore("-10")); // negative or non-numeric
        assertFalse(Score.isValidScore("10.5")); // decimal or non-numeric
        assertFalse(Score.isValidScore("1001")); // outside allowed range

        // valid score
        assertTrue(Score.isValidScore("1")); // lower bound
        assertTrue(Score.isValidScore("1000")); // upper bound
        assertTrue(Score.isValidScore("100")); // within range
    }

    @Test
    public void isValidScoreGivenFullMark() {
        FullMark op1FullMark = new FullMark(VALID_FULL_MARK_OP1);

        // null score
        assertThrows(NullPointerException.class, () -> Score.isValidScoreGivenFullMark(null, op1FullMark));

        // null full mark
        assertThrows(NullPointerException.class, () -> Score.isValidScoreGivenFullMark(VALID_SCORE_OP1, null));

        // invalid score
        assertFalse(Score.isValidScoreGivenFullMark("", op1FullMark)); // empty string
        assertFalse(Score.isValidScoreGivenFullMark("  ", op1FullMark)); // spaces only
        assertFalse(Score.isValidScoreGivenFullMark("abc", op1FullMark)); // non-numeric
        assertFalse(Score.isValidScoreGivenFullMark("1a00", op1FullMark)); // alphabet within input
        assertFalse(Score.isValidScoreGivenFullMark("-10", op1FullMark)); // negative or non-numeric
        assertFalse(Score.isValidScoreGivenFullMark("10.5", op1FullMark)); // decimal or non-numeric
        assertFalse(Score.isValidScoreGivenFullMark("51", op1FullMark)); // larger than full mark

        // valid score
        assertTrue(Score.isValidScoreGivenFullMark("0", op1FullMark)); // lower bound
        assertTrue(Score.isValidScoreGivenFullMark(VALID_FULL_MARK_OP1, op1FullMark)); // upper bound
        assertTrue(Score.isValidScoreGivenFullMark(VALID_SCORE_OP1, op1FullMark)); // within range
    }
}
