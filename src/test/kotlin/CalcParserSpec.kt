import expressions.*
import family.haschka.analyticalc.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class CalcParserSpec : FunSpec({


    test("Mein erster Parser test") {
        val ausdruck = "f(x)=x^2+4*x+10"

        val input = CharStreams.fromString(ausdruck)
        val lexer = CalcLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = CalcParser(tokens)
        val parseTree = parser.function()

        println(FunctionVisitor().visit(parseTree))

        parser.numberOfSyntaxErrors shouldBe 0
    }

    context("Expression Parsen") {
        withData("3*4*5", "2x", "2(a+b)") {
            val input = CharStreams.fromString(it)
            val lexer = CalcLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = CalcParser(tokens)
            val parseTree = parser.expr()

            println(parseTree.toStringTree(parser))

            parser.numberOfSyntaxErrors shouldBe 0
        }

        context("Expression Parser -> DataClasses korrekt übersetzen") {
            withData(
                "1 * 2 + x" to Sum(Multiplication(NumberExpr(1), NumberExpr(2)), Variable("x")),
                "3 / 4" to Division(NumberExpr(3), NumberExpr(4)),
                "1+2*3" to Sum(NumberExpr(1), Multiplication(NumberExpr(2), NumberExpr(3))),
                "1*2 + 3*4" to Sum(Multiplication(NumberExpr(1), NumberExpr(2)), Multiplication(NumberExpr(3), NumberExpr(4)))
            ) {
                val input = CharStreams.fromString(it.first)
                val lexer = CalcLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = CalcParser(tokens)
                val parseTree = parser.expr()

                val result = ExpressionVisitor().visit(parseTree)
                println(result)
                result shouldBe it.second
            }

            test("Trivialitäten") {
            }


        }
        data class NumberExprOperatorTest(val a: NumberExpr, val b: NumberExpr, val erwartet: NumberExpr)
        context("NumberExpr(a) + NumberExpr(b) sollte NumberExpr(a + b) sein") {
            withData(
                NumberExprOperatorTest(NumberExpr(3), NumberExpr(4), NumberExpr(7)),
                NumberExprOperatorTest(NumberExpr(345), NumberExpr(5647), NumberExpr(5992)),
            ) { testfall ->
                testfall.a + testfall.b shouldBe testfall.erwartet
            }
        }
        context("NumberExpr(a) - NumberExpr(b) sollte NumberExpr(a - b) sein") {
            withData(
                NumberExprOperatorTest(NumberExpr(10), NumberExpr(4), NumberExpr(6)),
                NumberExprOperatorTest(NumberExpr(345), NumberExpr(350), NumberExpr(-5)),
            ) {testfall ->
                testfall.a - testfall.b shouldBe testfall.erwartet
            }
        }
        context("NumberExpr(a) * NumberExpr(b) sollte NumberExpr(a * b) sein") {
            withData(
                NumberExprOperatorTest(NumberExpr(5), NumberExpr(6), NumberExpr(30)),
                NumberExprOperatorTest(NumberExpr(100), NumberExpr(5), NumberExpr(500)),
            ) {testfall ->
                testfall.a * testfall.b shouldBe testfall.erwartet
            }
        }
        context("NumberExpr(a) / NumberExpr(b) sollte NumberExpr(a / b) sein") {
            withData(
                NumberExprOperatorTest(NumberExpr(40), NumberExpr(4), NumberExpr(10)),
                NumberExprOperatorTest(NumberExpr(100), NumberExpr(100), NumberExpr(1)),
            ) {testfall ->
                testfall.a / testfall.b shouldBe testfall.erwartet
            }
        }


        context("Rein numerische Rechenausdrücke können vollständig zu NumberExpr vereinfacht werden. Symbolische bleiben einfach stehen") {
            withData(
                "1 * 2" to NumberExpr(2),
                "20 / 4" to NumberExpr(5),
                "1+2*3" to NumberExpr(7),
                "1*2 + 3*4" to NumberExpr(14),
                "2*3 + x" to Sum(NumberExpr(6), Variable("x")),
                ) {

                    val input = CharStreams.fromString(it.first)
                    val lexer = CalcLexer(input)
                    val tokens = CommonTokenStream(lexer)
                    val parser = CalcParser(tokens)
                    val parseTree = parser.expr()

                    val result = ExpressionVisitor().visit(parseTree).simplify()
                    println(result)
                    result shouldBe it.second
                }
        }

    }
})