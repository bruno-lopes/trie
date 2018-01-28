package com.portalbll.trie

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by bruno on 1/28/18.
 */
class NodeTrieSpecification extends Specification {

    @Unroll
    void "Intersection between a node with value #value and the word #word returns #expectedIntersection"() {
        given: "A node with a determined value"
        NodeTrie nodeABC = new NodeTrie(value)

        when: "Verifies the intersection with a word"

        String returnedIntersection = nodeABC.intersect(word)

        then: "The returned intersection must be equal to the expected"
        returnedIntersection == expectedIntersection

        where:
        value | word  || expectedIntersection
        "ABC" | "ABC" || "ABC"
        "ABC" | "A"   || "A"
        "ABC" | "B"   || ""
        "ABC" | ""    || ""


    }
}
