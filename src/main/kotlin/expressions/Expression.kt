package expressions


interface Expression {
    fun simplify(): Expression
}

data class Sum(val left: Expression, val right: Expression) : Expression {

    /*
    *   left + right
    *    --------
    *   | Regeln |
    *    --------
    *
    *   - Zahlen können addiert werden: 2 + 3 => 5
    *   - Bei gleichwertigen Variablen werden Koeffizienten addiert: x + x => 2*x
    *   - Ausdrücke, die nicht gleichwertig sind, bleiben bestehen: 2 + x => 2 + x
    *
    */
    override fun simplify(): Expression {

        val leftExpression = left.simplify()
        val rightExpression = right.simplify()

        //TODO("Wenn rechts und links gleichwertige Variablen sind, werden die Koeffizienten addiert")


        //Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        return if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            leftExpression + rightExpression
        }
        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        else {
            Sum(leftExpression, rightExpression)
        }
    }



    override fun toString(): String {
        return "[+ $left $right]"
    }
}

data class Subtraction(val left: Expression, val right: Expression) : Expression {
    /*
    *   left - right
    *    --------
    *   | Regeln |
    *    --------
    *
    *   - Zahlen können subtrahiert werden: 5 - 3 => 2
    *   - Bei gleichwertigen Variablen werden die Koeffizienten subtrahiert: 4*x - x => 3*x
    *   - Ausdrücke, die nicht gleichwertig sind, bleiben bestehen: 2 - x => 2 - x
    *
    */
    override fun simplify(): Expression {

        val leftExpression = left.simplify()
        val rightExpression = right.simplify()

        //TODO("Das selbe wie bei Addieren")

        //Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        return if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            leftExpression - rightExpression
        }
        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        else {
            Subtraction(leftExpression, rightExpression)
        }

    }

    override fun toString(): String {
        return "[- $left $right]"
    }
}

data class Multiplication(val left: Expression, val right: Expression) : Expression {
    /*
    *   left * right
    *    --------
    *   | Regeln |
    *    --------
    *
    *   - Zahlen können multipliziert werden: 2 * 3 => 6
    *   - Bei gleichwertigen Variablen werden die Exponenten addiert: x * x => x^(1 + 1) => x^2
    *   - Ausdrücke, die nicht gleichwertig sind, bleiben bestehen: 2 * x => 2 * x
    *
    */
    override fun simplify(): Expression {


        val leftExpression = left.simplify()
        val rightExpression = right.simplify()

        //TODO("Wenn rechts und links gleichwertige Variablen sind, werden die Exponenten addiert")


        //Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        return if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            leftExpression * rightExpression
        }
        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        else {
            Multiplication(leftExpression, rightExpression)
        }
    }

    override fun toString(): String {
        return "[* $left $right]"
    }

}

data class Division(val left: Expression, val right: Expression) : Expression {
    /*
    *   left / right
    *    --------
    *   | Regeln |
    *    --------
    *
    *   - Zahlen können dividiert werden: 6 / 3 => 2
    *   - Bei gleichwertigen Variablen wird der exponent subtrahiert => x / x => x^(1 - 1) => x^0
    *   - Ausdrücke, die nicht gleichwertig sind, bleiben bestehen: 2 / x => 2 / x
    *
    */
    override fun simplify(): Expression {


        val leftExpression = left.simplify()
        val rightExpression = right.simplify()

        //TODO("Wenn rechts und links gleichwertige Variablen sind, werden die Koeffizienten addiert")


        //Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        return if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            leftExpression / rightExpression
        }
        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        else {
            Division(leftExpression, rightExpression)
        }
    }

    override fun toString(): String {
        return "[/ $left $right]"
    }
}

data class NumberExpr(val n: Int) : Expression {
    /*
    *    --------
    *   | Regeln |
    *    --------
    *   - Gibt lediglich sich selbst zurück.
    */
    override fun simplify(): Expression {
        return this
    }

    operator fun plus(number: NumberExpr): NumberExpr {
        return NumberExpr(n + number.n)
    }

    operator fun minus(number: NumberExpr) : NumberExpr {
        return NumberExpr(n - number.n)
    }

    operator fun times(number: NumberExpr) : NumberExpr {
        return NumberExpr(n * number.n)
    }

    operator fun div(number: NumberExpr) : NumberExpr {
        //TODO("Rationals einführen")
        return NumberExpr(n / number.n)
    }

    override fun toString(): String {
        return n.toString()
    }
}

data class Variable(val v: String) : Expression {
    /*
    *    --------
    *   | Regeln |
    *    --------
    *   - Gibt lediglich sich selbst zurück
    */
    override fun simplify(): Expression {
        return this
    }

    override fun toString(): String {
        return v
    }
}

