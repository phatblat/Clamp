package example

import kotlin.test.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

object CalculatorSpec: Spek({
    given("a calculator") {
        val calculator = SampleCalculator()
        on("addition") {
            val sum = calculator.sum(2, 4)
            it("should return the result of adding the first number to the second number") {
                assertEquals(6, sum)
            }
        }
        on("subtraction") {
            val subtract = calculator.subtract(4, 2)
            it("should return the result of subtracting the second number from the first number") {
                assertEquals(2, subtract)
            }
        }
    }
})
