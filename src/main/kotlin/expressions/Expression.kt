package expressions

//
interface Expression {}

data class Sum(val left: Expression, val right: Expression) : Expression {
    override fun toString(): String {
        return "[+ $left $right]"
    }
}

data class Subtracion(val left: Expression, val right: Expression) : Expression {
    override fun toString(): String {
        return "[- $left $right]"
    }
}

data class Multiplication(val left: Expression, val right: Expression) : Expression {
    override fun toString(): String {
        return "[* $left $right]"
    }

}

data class Division(val left: Expression, val right: Expression) : Expression {
    override fun toString(): String {
        return "[/ $left $right]"
    }
}

data class NumberExpr(val n: Int) : Expression {
    override fun toString(): String {
        return n.toString()
    }
}

data class Variable(val v: String) : Expression{}

