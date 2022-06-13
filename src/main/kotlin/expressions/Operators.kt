package expressions

operator fun Expression.times(expression: Expression): Expression {
    return Multiplication(this, expression)
}

operator fun Expression.div(expression: Expression): Expression {
    return Division(this, expression)
}

operator fun Expression.plus(expression: Expression): Expression {
    return Sum(this, expression)
}

operator fun Expression.minus(expression: Expression): Expression {
    return Subtraction(this, expression)
}

// Diesen Operator wirst du vielleicht benötigen, um die Polynomdivision
// umzusetzen.
operator fun Expression.rem(expression: Expression): Expression {
    TODO("Übungsaufgabe")
}

operator fun Int.plus(variable: String): Expression {
    return this.toNumberExpr() + variable.toVariable()
}


fun Int.toNumberExpr() = NumberExpr(this)
fun Char.toVariable() = Variable(this.toString())
fun String.toVariable() = Variable(this)