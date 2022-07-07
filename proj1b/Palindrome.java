public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characterDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            characterDeque.addLast(word.charAt(i));
        }
        return characterDeque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return true;
        }
        int n = word.length();
        if (n <= 1) {
            return true;
        }
        int i = 0;
        while (i < n / 2) {
            if (word.charAt(i) != word.charAt(n - 1 - i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /*
    *   Overload the method
    * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int n = word.length();
        if (word == null || n <= 1) {
            return true;
        }
        int i = 0;
        while (i < n / 2) {
            if (!cc.equalChars(word.charAt(i), word.charAt(n - 1 - i))) {
                return false;
            }
            i++;
        }
        return true;
    }

}
