package net.pureal.tests.traits

import org.spek.*
import net.pureal.traits.*

class Transform2Specs : Spek() {{
    given("an identity translation") {
        val t = transformOf()

        on("applying it on a vector") {
            val applied = t(vectorOf(1.4,-11))

            it("should be unchanged") {
                shouldEqual(vectorOf(1.4,-11), applied)
            }
        }
    }

    given("a translation") {
        val t = Transforms2.translation(vectorOf(2,-3))

        on("applying it on a vector") {
            val applied = t(vectorOf(1,3))

            it("should be translated accordingly") {
                shouldEqual(vectorOf(3,0), applied)
            }
        }
    }

    given("a rotation") {
        val t = Transforms2.rotation(pi/2)

        on("applying it on a vector") {
            val applied = t(vectorOf(1,3))

            it("should be rotated accordingly") {
                shouldEqual(vectorOf(-3,1), applied)
            }
        }
    }

    given("a scale") {
        val t = Transforms2.scale(-2)

        on("applying it on a vector") {
            val applied = t(vectorOf(1,3))

            it("should be scaled accordingly") {
                shouldEqual(vectorOf(-2,-6), applied)
            }
        }
    }

    given("a reflection") {
        val t = Transforms2.reflection(axisAngle=pi)

        on("applying it on a vector") {
            val applied = t(vectorOf(1,3))

            it("should be reflected accordingly") {
                shouldEqual(vectorOf(1,-3), applied)
            }
        }
    }
}}