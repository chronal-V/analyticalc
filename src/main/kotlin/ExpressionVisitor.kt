import expressions.Division
import expressions.Expression
import expressions.Multiplication
import expressions.NumberExpr
import family.haschka.analyticalc.CalcBaseVisitor
import family.haschka.analyticalc.CalcParser

class ExpressionVisitor : CalcBaseVisitor<Expression>() {

    override fun visitNumber(ctx: CalcParser.NumberContext): Expression {
        // Wegen dem fehlenden ? im Parameter musst du nicht auf null prüfen.
        // Um den Wert zu erhalten, den Text des Token in ein Integer umwandeln.
        // Hier sollte nichts schiefgehen können, weil der Lexer sicherstellt,
        // dass da nur Ziffern drin sind. Wenn nicht wirft toInt selbst die
        // Exception.
        return NumberExpr(ctx.NUM().text.toInt())
    }

    override fun visitOpMul(ctx: CalcParser.OpMulContext): Expression {
        // Das hier ist der ExpressionVisitor, also besuchen wir
        // left und right, weil wir sicher sind, dass es expressions
        // sind.
        val left = this.visit(ctx.left)
        val right = this.visit(ctx.right)

        // Abhängig vom Operator Token MUL erzeugen wir zwei verschiedene
        // Expression Objekte, weil sie sich bei der Auswertung des Ausdrucks
        // (Integration oder Differenzierung etc) unterschiedlich verhalten.
        if (ctx.MUL().text == "*") {
            return Multiplication(left, right)
        } else if (ctx.MUL().text == "/") {
            return Division(left, right)
        }

        // Sollte wegen der Grammatik eigentlich nie erreicht werden können:
        throw java.lang.RuntimeException("Unexpected operand ${ctx.MUL().text}")
    }
}