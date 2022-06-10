import expressions.Division
import expressions.Multiplication
import expressions.NumberExpr
import expressions.Sum
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
                "1 * 2" to Multiplication(NumberExpr(1), NumberExpr(2)),
                "3 / 4" to Division(NumberExpr(3), NumberExpr(4)),
                "1+2*3" to Sum(NumberExpr(1), Multiplication(NumberExpr(2), NumberExpr(3)))

            ) {
                val input = CharStreams.fromString(it.first)
                val lexer = CalcLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = CalcParser(tokens)
                val parseTree = parser.expr()

                val result = ExpressionVisitor().visit(parseTree)
                result shouldBe it.second
            }
        }

    }
})