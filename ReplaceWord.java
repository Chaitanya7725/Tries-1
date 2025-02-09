import java.util.Arrays;
import java.util.List;

class TrieNode {
    TrieNode children[];
    boolean isEnd;

    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
    }
}

// TC: O(mk + nl)
// SC: O(mk + nl)

// where m is the size of dictionary list to be added in tries. And each element
// in the dictionary is of k length
class Tries {
    TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        /*
         * 1. Add dictionary in prefix trienodes
         * 2. Iterate string for each word and then check if the word has root in the
         * tries
         */
        if (sentence == null || sentence.length() == 0)
            return sentence;
        root = new TrieNode();
        for (String word : dictionary) {
            insert(word);
        }
        String[] words = sentence.split(" ");
        StringBuilder answer = new StringBuilder();
        for (String word : words) {
            answer.append(" ");
            StringBuilder sb = new StringBuilder();
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.children[c - 'a'] == null || curr.isEnd == true)
                    break;
                sb.append(c);
                curr = curr.children[c - 'a'];
            }
            if (curr.isEnd) {
                answer.append(sb.toString());
            } else {
                answer.append(word);
            }
        }
        return answer.toString().substring(1);
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

}

public class ReplaceWord {
    public static void main(String[] args) {
        Tries tries = new Tries();
        System.out.println(
                tries.replaceWords(Arrays.asList("cat", "bat", "rat"),
                        "the cattle was rattled by the battery")); // the cat was rat by the bat
    }
}
