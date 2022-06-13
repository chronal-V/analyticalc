import expressions.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OperatorsSpec : FunSpec({

    test("expr + expr") {
        42.toNumberExpr() * 2.toNumberExpr() shouldBe Multiplication(NumberExpr(42), NumberExpr(2))
    }

    test("expr * expr") {
        val left =  4 + "x" // => Sum(NumberExpr(4), Variable("x"))
        val right = NumberExpr(2)

        left * right shouldBe Multiplication(left, right)
    }

    test("expr - expr") {
        val left = NumberExpr(2)
        val right = Sum(NumberExpr(4), Variable("x"))

        left - right shouldBe Subtraction(left, right)
    }

    test("expr / expr") {
        // TODO
    }
})