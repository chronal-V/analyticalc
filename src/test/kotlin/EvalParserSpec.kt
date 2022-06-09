import family.haschka.analyticalc.EvalBaseVisitor
import family.haschka.analyticalc.EvalLexer
import family.haschka.analyticalc.EvalParser
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class EvalParserSpec : FunSpec({


    test("Mein erster Parser test") {
        val ausdruck = "f(x)=x^2+4*x+10"

        val input = CharStreams.fromString(ausdruck)
        val lexer = EvalLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = EvalParser(tokens)

        parser.numberOfSyntaxErrors shouldBe 0
    }

    class FuncVisitor : EvalBaseVisitor<Function>() {
        // (a + a) => 2a
        // (a * a) => a^2
        // a + a * a => (expression a '+' (expression a '*' a))
        // f(x) = ... => speichern
        // eval f'(x)
    }


    test("Mein zweiter Parser test") {
        val ausdruck = "f(x)=x^2+4*x+10"

        val input = CharStreams.fromString(ausdruck)
        val lexer = EvalLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = EvalParser(tokens)
        val parserTree = parser.function()

        val result = FunctionVisitor().visit(parserTree)
        result shouldBe "f(x)="
    }
})