package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Transform2Specs : Spek() {{
    given("a translation") {
        val t = Transforms2.translation(vectorOf(2,-3))

        on("applying it on a vector") {
            val applied = t(vectorOf(1,3))

            it("should be translated accordingly") {
                shouldEqual(vectorOf(3,0), applied)
            }
        }
    }
}}