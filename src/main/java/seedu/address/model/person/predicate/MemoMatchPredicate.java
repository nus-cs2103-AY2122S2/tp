package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests if the person's memo partially matches the specified memo argument during the execution of the FindCommand.
 */
public class MemoMatchPredicate implements Predicate<Person> {
    private final List<String> memoArgs;

    /**
     * Constructor for MemoMatchPredicate.
     *
     * @param memoArgs Memo argument obtained from the user.
     */
    public MemoMatchPredicate(List<String> memoArgs) {
        this.memoArgs = memoArgs;
    }

    /**
     * Tests if any of the word in the person's memo partially matches to the user's memo argument. This test is
     * case-insensitive, where "Memo" would match with "This is a memo". If an empty argument is specified, i.e "m/",
     * the predicate matches with any person that has no memo.
     *
     * @param person Person to be checked against.
     * @return True if the memo argument partially matches any word in the person memo. False otherwise.
     */
    @Override
    public boolean test(Person person) {
        String personMemo = person.getMemo().toString();
        for (String arg : memoArgs) {
            if (testMemoAttribute(personMemo, arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method for {@link #test(Person)}. Helps to check if any of the words in the given memo argument
     * partially matches the person's memo. The check if case-insensitive.
     *
     * @param personMemo Person memo to be tested.
     * @param memoArg Memo argument to be tested.
     * @return True if any of the memo argument partially matches a substring in the person's memo, false otherwise.
     */
    private boolean testMemoAttribute(String personMemo, String memoArg) {
        if (memoArg.isEmpty()) {
            return personMemo.isEmpty();
        } else {
            return personMemo.toLowerCase().contains(memoArg.toLowerCase());
        }
    }
}
