import expressions.*
import family.haschka.analyticalc.CalcBaseVisitor
import family.haschka.analyticalc.CalcParser

class AnotherExpressionVisitor : CalcBaseVisitor<String>() {

    override fun visitNumber(ctx: CalcParser.NumberContext?): String {
        return if (ctx != null) {
            "The Number ${ctx.number.text} \n"
        } else "Null"
    }

    override fun visitVariable(ctx: CalcParser.VariableContext?): String {
        return if (ctx != null) {
            "The Variable ${ctx.variable.text} \n"
        } else "Null"
    }

    override fun visitOpAdd(ctx: CalcParser.OpAddContext?): String {
        return if (ctx != null) {
            visitChildren(ctx) + "The Sum of ${ctx.left.text} and ${ctx.right.text} \n"
        } else "Null"
    }

    override fun visitOpMul(ctx: CalcParser.OpMulContext?): String {
        return if (ctx != null) {
            visitChildren(ctx) +  "\n" + "The Product of ${ctx.left.text} and ${ctx.right.text} \n"
        } else "Null"
    }

    override fun visitOpPow(ctx: CalcParser.OpPowContext?): String {
        return if (ctx != null) {
            visitChildren(ctx) + "${ctx.base.text} to the power of ${ctx.exponent.text} \n"
        } else "Null"
    }

    override fun visitOpParan(ctx: CalcParser.OpParanContext?): String {
        return if (ctx != null) {
            visitChildren(ctx) + "The Expression ${ctx.children} in Parentheses \n"
        } else "Null"
    }

    override fun aggregateResult(aggregate: String?, nextResult: String?): String {
        if (aggregate != null && nextResult != null) {
            return "$aggregate $nextResult"
        }
        else if (aggregate == null && nextResult != null) {
            return nextResult
        }
        else if(aggregate != null) {
            return aggregate
        }
        throw NullPointerException("Whatever")
    }
}