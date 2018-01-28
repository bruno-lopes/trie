package com.portalbll.trie;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bruno on 1/28/18.
 */
public class Trie {

    public Trie(){
        this.root = new NodeTrie(null);
    }

    public Trie(NodeTrie root) {
        this.root = root;
    }

    private NodeTrie root;

    public static void main(String[] args){
        Trie trie = new Trie();
        ArrayList names = new ArrayList(Arrays.asList("amy", "ann", "emma", "rob", "roger", "robson", "emma", "robsison"));

        names.forEach(nome -> {
            trie.add((String)nome);
        });
        trie.print();
    }

    /**
     * Print all the paths and words
     */
    public void print() {
        this.root.children.forEach(child->{
            printNode(child, "","");
        });


    }

    /**
     * If it reaches an end, prints the word and its path. Other way, it adds the node value to the path and continues
     * @param node Node to be visited
     * @param word The word that is formed, or being formed.
     * @param path The path of the word
     */
    public void printNode(NodeTrie node, String word, String path) {
        if (node.value==null){
            System.out.print(word + " = " + path + "\n");
            return;
        }
        node.children.forEach( child-> {
            printNode(child, word.concat(node.value), path + "->" + node.value);
        });
    }


    public void add(String word) {
        for (NodeTrie node : this.root.children) {
            if (addInNode(node, word) == true) {
                return;
            }
        }
        NodeTrie node = new NodeTrie(String.valueOf(word.charAt(0)));
        this.root.children.add(node);
        addInNode(node, word);

    }

    private boolean addInNode(NodeTrie node, String word) {
        if (word.equals("") && node.value==null){
            return true;
        }
        //find intersection
        String intersection = node.intersect((word));

        if (intersection.length()==0) {
            return false;
        }

        //If the intersection is complete, and the node doesn't have children, then it is already added
        if (intersection.equals(word) && intersection.equals(node.value) && node.children.size()==0) {
            node.addChild((String) null);
            return true;
        }

        for (NodeTrie child : node.children) {
            if (addInNode(child, word.substring(intersection.length(), word.length())) == true) {
                return true;
            }
        }

        NodeTrie nodeAux = null;
        for (int i = 1; i < word.length(); i++) {
            nodeAux = new NodeTrie(String.valueOf(word.charAt(i)));
            node.addChild(nodeAux);
            node = nodeAux;
        }
        node.addChild((String)null);
        return true;
    }

    public boolean find(String word) {
        if (word=="") {
            return true;
        }
        for (NodeTrie node : this.root.children) {
            if (findInNode(node, word) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean findInNode(NodeTrie node, String word) {


        if (word.equals("") && node.value == null) {
            return true;
        }
        int i = 0;

        //find intersection
        String intersection = node.intersect(word);

        //if there is no intersection, return false
        if (intersection.length()==0) {
            return false;
        }
        for (NodeTrie child : node.children) {
            if (findInNode(child, word.substring(intersection.length(), word.length())) == true) {
                return true;
            }
        }
        return false;
    }

}
