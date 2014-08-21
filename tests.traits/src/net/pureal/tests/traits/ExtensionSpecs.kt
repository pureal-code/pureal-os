package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

public class ExtensionSpecs : Spek() {{
    given("a [2 3 4] array"){
        val a = array(2,3,4)
        on("testing the replaceElements function") {
            it("should be [4 6 8]") {
                val b = a.replaceElements { 2 * it }
                shouldEqual(4, b[0])
                shouldEqual(6, b[1])
                shouldEqual(8, b[2])
            }
        }
    }
}
}