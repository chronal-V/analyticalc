import family.haschka.analyticalc.*
import io.kotest.core.spec.style.FunSpec
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

    test("Mein Expression.Expression-Parser test") {
        val ausdruck = "3*4*5"

        val input = CharStreams.fromString(ausdruck)
        val lexer = CalcLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = CalcParser(tokens)
        val parseTree = parser.expr()

        val result = ExpressionVisitor().visit(parseTree)
        println(result)
    }

    test("Mein Function-Parser test") {
        val ausdruck = "f(x)=x^2+4*x+10"

        val input = CharStreams.fromString(ausdruck)
        val lexer = CalcLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = CalcParser(tokens)
        val parserTree = parser.function()

        val result = FunctionVisitor().visit(parserTree)
        result shouldBe "f(x)="
    }
})