import family.haschka.analyticalc.CalcBaseVisitor
import family.haschka.analyticalc.CalcParser

class FunctionVisitor : CalcBaseVisitor<String>() {


    override fun visitFunction(ctx: CalcParser.FunctionContext?): String {
        if (ctx == null) {
            throw NullPointerException("Function is null")
        }

        return ctx.toStringTree();
    }
}