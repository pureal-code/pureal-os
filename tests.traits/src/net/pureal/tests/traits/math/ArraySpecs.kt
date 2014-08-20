package net.pureal.tests.traits.math

import org.spek.*
import net.pureal.traits.math.replaceElements

public class ArraySpecs : Spek() {{
    given("a [2 3 4] array"){
        val a = array(2,3,4)
        on("testing the replaceElements function") {
            it("should be [4 6 8]") {
                val b = a.replaceElements {2*it}
                shouuldEqual(4, b[0])
            }
        }
    }
}
}