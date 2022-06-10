import expressions.*
import family.haschka.analyticalc.CalcBaseVisitor
import family.haschka.analyticalc.CalcParser

class ExpressionVisitor : CalcBaseVisitor<Expression>() {

    override fun visitNumber(ctx: CalcParser.NumberContext): Expression {
        return NumberExpr(ctx.NUM().text.toInt())
    }

    override fun visitOpAdd(ctx: CalcParser.OpAddContext): Expression {

        val left = this.visit(ctx.left)
        val right = this.visit(ctx.right)

        if(ctx.ADD().text == "+") {
            return Sum(left, right)
        } else if (ctx.ADD().text == "-") {
            return Subtracion(left, right)
        }

        throw RuntimeException("Unexpected operand ${ctx.ADD().text}")
    }

    override fun visitOpMul(ctx: CalcParser.OpMulContext): Expression {

        val left = this.visit(ctx.left)
        val right = this.visit(ctx.right)

        if (ctx.MUL().text == "*") {
            return Multiplication(left, right)
        } else if (ctx.MUL().text == "/") {
            return Division(left, right)
        }

        throw RuntimeException("Unexpected operand ${ctx.MUL().text}")
    }
}