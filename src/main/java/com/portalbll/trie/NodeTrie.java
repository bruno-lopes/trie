package com.portalbll.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 1/28/18.
 */
public class NodeTrie {

    String value = "";

    List<NodeTrie> children = new ArrayList<NodeTrie>();

    protected NodeTrie(String value) {
        this.value = value;
    }

    /**
     * Returns the intersection between the node value, and the word passed as parameter
     * @param value Word to compute intersection
     * @return Intersection between the node value and the word
     */
    protected String intersect (String value) {
        String intersection = null;
        if (this.value==null) {
            return "";
        }
        int maxIntersectionIndex = 0;
        for (; maxIntersectionIndex < this.value.length() && maxIntersectionIndex < value.length(); maxIntersectionIndex++) {
            if (this.value.charAt(maxIntersectionIndex) != value.charAt(maxIntersectionIndex)){
                break;
            }
        }
        intersection = this.value.substring(0,maxIntersectionIndex);
        return intersection;
    }

    protected void addChild(String word) {
        this.children.add(new NodeTrie(word));
    }

    protected void addChild(NodeTrie child) {
        this.children.add(child);
    }
}
