package net.questions.solutions;

import java.util.*;

public class Tries {

    TrieNode head;
    private int wordCount;

    public Tries() {
        head = new TrieNode(true);
        head.nodes = new HashMap<>();
        wordCount = 0;
    }

    public void insertWordFromArray(String[] words) {
        for (String w: words) {
            insertWord(w);
        }
    }

    /**@apiNote time complexity; l: average length of words, n: number of words
     *  O(lxn)
    * */
    public void insertWord(String word) {
        insertWord(head, word, 0);
        wordCount++;
    }

    public int getWordCount() {
        return wordCount;
    }

    private void insertWord(TrieNode node, String word, int pos) {
        if (pos == word.length())
            return;
        TrieNode newNode = node.nodes.get(word.charAt(pos));
        if (newNode!=null) {
            if (newNode.nodes == null)
                newNode.nodes = new HashMap<>();
        } else {
            boolean end = pos == word.length() - 1;
            newNode = new TrieNode(end);
            node.nodes.put(word.charAt(pos), newNode);
        }

        insertWord(newNode, word, ++pos);
    }

    /**@apiNote time complexity; l: length of word, O(l)
     * @param word {@code String}
     * */
    public boolean containsWord(String word){
        if(word == null || (word!=null && word.length()==0))
            throw new IllegalArgumentException("word must be at least one or more character");

        TrieNode node =findSubNodes(head,word,0);

        return node != null ? node.endOfWord : false;

    }

    public String[] startsWith(String prefix) {
        if(prefix == null || (prefix!=null && prefix.length()==0))
            throw new IllegalArgumentException("prefix must be at least one or more character");

        TrieNode node =findSubNodes(head,prefix,0);

        if (node != null){
            StringBuilder sb = new StringBuilder(prefix);
            List<String> words = new ArrayList<>();
            startsWith(node,sb,words);
            return words.toArray(new String[words.size()]);
        }else
            throw new IllegalArgumentException("no word found starts with \"" + prefix + "\"");

    }

    private void startsWith(TrieNode node,StringBuilder sb,List<String> words) {
        if (node.nodes == null)
            return;

        for (Map.Entry<Character, TrieNode> n : node.nodes.entrySet()) {
            sb.append(n.getKey());
            if(n.getValue().endOfWord)
                words.add(sb.toString());

            startsWith(n.getValue(), sb,words);
            sb = sb.deleteCharAt(sb.length()-1);
        }
    }

    public void printStartsWith(String prefix) {
        TrieNode node = findSubNodes(head,prefix,0);

        if (node != null){
            StringBuilder sb = new StringBuilder(prefix);
            printInorder(node,sb);
        }else
            throw new IllegalArgumentException("no word found starts with \"" + prefix + "\"");

    }
    private TrieNode findSubNodes(TrieNode node,String prefix,int position) {
        if(position == prefix.length())
            return node;
        if(node.nodes.get(prefix.charAt(position)) == null)
            return null;
        return findSubNodes(node.nodes.get(prefix.charAt(position)),prefix,++position);
    }

    public void printInorder() {
        StringBuilder sb = new StringBuilder();
        printInorder(head,sb);
    }

    private void printInorder(TrieNode node,StringBuilder sb) {
        if (node.nodes == null)
            return;

        for (Map.Entry<Character, TrieNode> n : node.nodes.entrySet()) {
            sb.append(n.getKey());
            if(n.getValue().endOfWord)
                System.out.println(sb);

            printInorder(n.getValue(), sb);
            sb = sb.deleteCharAt(sb.length()-1);
        }
    }
}
class TrieNode {
    Map<Character, TrieNode> nodes;
    boolean endOfWord;

    public TrieNode(boolean end) {
        this.endOfWord = end;
        if (!endOfWord)
            nodes = new HashMap<Character, TrieNode>();
    }
}