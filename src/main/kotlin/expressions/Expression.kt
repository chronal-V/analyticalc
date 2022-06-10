package expressions

open class Expression()  {
}

class Sum() : Expression() {}

class Multiplication(private val of: Expression) : Expression() {
    override fun toString(): String {
        return "Mul $of"
    }
}

class NumberExpr(private val n: Int) : Expression() {
    override fun toString(): String {
        return n.toString()
    }
}

class Variable(v: String) : Expression(){}

