import family.haschka.analyticalc.EvalBaseVisitor
import family.haschka.analyticalc.EvalParser

class FunctionVisitor : EvalBaseVisitor<String>() {

    class ExpressionVisitor : EvalBaseVisitor<Expression>() {

        override fun visitExpression(ctx: EvalParser.ExpressionContext?): Expression {
            return Expression(/** ctx **/)
        }
    }

    override fun visitFunction(ctx: EvalParser.FunctionContext?): String {
        if (ctx == null) {
            return "Context ist Null"
        }


        val expr = ctx.expression()
        var variable = ctx.VAR().text
        return Function(variable, expr)
        return "f(${ctx.VAR().text})="
    }
}