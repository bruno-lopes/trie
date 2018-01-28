package com.portalbll.trie

import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.xml.soap.Node

/**
 * Created by bruno on 1/28/18.
 */
class TrieSpecification extends Specification {
    @Shared
    Trie tree = new Trie();

    @Unroll
    void "Find #word returns #expectedResult"() {
        given: "A tree with the following configuration"
        NodeTrie root = new NodeTrie("")
        NodeTrie a = new NodeTrie("A");
        NodeTrie b = new NodeTrie("B");
        NodeTrie c = new NodeTrie("C");
        NodeTrie d = new NodeTrie("D");
        NodeTrie e = new NodeTrie("E");
        NodeTrie f = new NodeTrie("F");
        NodeTrie g = new NodeTrie("G")
        NodeTrie a1 = new NodeTrie("A");
        NodeTrie j = new NodeTrie("J");
        b.addChild(c)
        c.addChild((String)null)
        d.addChild((String)null)
        f.addChild((String)null)
        b.addChild(d)
        e.addChild(f)
        e.addChild(g)
        a.addChild(b)
        g.addChild(a1)
        a1.addChild(j)
        j.addChild((String)null)

        root.addChild(a)
        root.addChild(e)

        Trie trie = new Trie(root)

        when:"Search a specific word"
        boolean result = trie.find(word)

        then: "The result is correct"
        result == expectedResult

        where:
        word || expectedResult
        "ABC" || true
        "AB" || false
        "ABD" || true
        "ABE" || false
        "EF"  || true
        "EG"  || false
        "EGAJ" || true
        ""    || true
    }

    @Unroll
    void "After adding the new word #word, it is present in the tree"() {

        when: "Add a new word"
        tree.add(word)

        then: "Finds the word in tree"
        tree.find(word) == result

        where:
        word || result
        "A" || true
        "E" || true
        "ABC" || true
        "ABCD" || true
        "EF" || true

    }
}
