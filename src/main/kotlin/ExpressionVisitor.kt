import expressions.*
import family.haschka.analyticalc.CalcBaseVisitor
import family.haschka.analyticalc.CalcParser

class ExpressionVisitor : CalcBaseVisitor<Expression>() {

    override fun visitNumber(ctx: CalcParser.NumberContext?): Expression {
        if (ctx != null) {
            return NumberExpr(ctx.number.toString().toInt())
        } else throw NullPointerException("Number is null")
    }

    override fun visitOpMul(ctx: CalcParser.OpMulContext?): Expression {
        if(ctx != null) {
            return Multiplication(visitChildren(ctx))
        } else throw NullPointerException("Product is null")
    }

    /*override fun aggregateResult(aggregate: Expression?, nextResult: Expression?): Expression {
        if (nextResult != null) {
            return nextResult
        }
        else if(aggregate != null) {
            return aggregate
        }
        throw NullPointerException("Whatever")
    }*/
}