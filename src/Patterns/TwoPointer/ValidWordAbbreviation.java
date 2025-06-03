package Patterns.TwoPointer;

/**
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths.
 * The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 *
 * The following are not valid abbreviations:
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 *
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */
public class ValidWordAbbreviation {

    public boolean solution(String word, String abbr) {
        int wLen = word.length(), abLen = abbr.length();

        // Compare Word length and abbreviation length,
        // abbreviation length should be always less than word length
        if (abLen > wLen) return false;

        int i = 0, j = 0;

        while(i < wLen && j < abLen) {
            // character is matching
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            // if abbreviation character is not digit and doesn't match with word's current
            // character then its not a valid abbreviation
            if (!Character.isDigit(abbr.charAt(j)) && word.charAt(i) != abbr.charAt(j)) {
                return false;
            }

            // Length can't start with 0
            if (Character.isDigit(abbr.charAt(j)) && abbr.charAt(j) == '0') {
                return false;
            }

            int num = 0;

            while (j < abLen && Character.isDigit(abbr.charAt(j))) {
                num = num*10 + (abbr.charAt(j) - '0');
                j++;
            }
            i += num;
        }
        // If the word matches the abbrevieation then both
        // pointers will exactly reach at the end
        return i==wLen && j == abLen;
    }

    public static void main(String[] args) {
        ValidWordAbbreviation obj = new ValidWordAbbreviation();
        System.out.println("Word=substitution Abbreviation=s10n\n"+obj.solution("substitution", "s10n"));
        System.out.println("\nWord=substitution Abbreviation=s010n\n"+obj.solution("substitution", "s010n"));
        System.out.println("\nWord=substitution Abbreviation=s55n\n"+obj.solution("substitution", "s55n"));
        System.out.println("\nWord=internationalization Abbreviation=i12iz4n\n"+obj.solution("substitution", "s10n"));
    }
}
