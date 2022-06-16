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

        // Ich glaube, dass ist der falsche Ansatz. Was wäre, wenn du prüfst, ob der rechte und der
        // linke Ausdruck ein Polynom ist und dann Polynomarithmetik verwendets:

        // if (left.isPoly() && right.isPoly() {
        //   val lpoly: Polynom = left.poly()
        //   val rpoly: Polynom = right.poly()
        //
        // sofern plus Operator für Polynome implementiert ist
        // und Polynom eine Expression ist.
        //     Eine Summe von Monomen ist ein Polynom
        //     Ein Monom ist Multiplicatin(NumberExpr(42), Potenz(Variable("x), NumberExpr(3)))
        //     Und NumberExpr(zahl) ist ein Sonderfall für ein Monom (zahl * x^0)
        //     Und Multiplication(NumberExpr(zahl), Variable("x)) ist ein Monom zahl * x^1
        //     evtl. kannst du Monomen und Polynome direkt beim Parsen erkennen und erzeugen
        //   return left.poly() + right.poly()
        // }
        //TODO("Wenn rechts und links gleichwertige Variablen sind, werden die Koeffizienten addiert")


        // Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            return NumberExpr(leftExpression.n + rightExpression.n)
        }

        if (leftExpression is Variable && rightExpression is Variable) {
            if (leftExpression.v == rightExpression.v) {
                return 2.toNumberExpr() * leftExpression // => Multiplication(NumberExpr(2), leftExpression)
            }
        }

        return Sum(leftExpression, rightExpression)
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
        if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            return NumberExpr(leftExpression.n - rightExpression.n)
        }

        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        return Subtraction(leftExpression, rightExpression)
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

        if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            return NumberExpr(leftExpression.n * rightExpression.n)
        }

        // Andernfalls (Die Ausdrücke sind nicht gleichwertig) bleibt der Ausdruck einfach bestehen
        return Multiplication(leftExpression, rightExpression)
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
    *   - mh: Das ist der spaßige Teil der Aufgabe:
    *   a/3a => 1/3, 3a/a => 3. Das ist der simpelste Fall einer Polynomdivision.
    *   hier wäre es hilfreich zu prüfen ob Nenner und Zähler Polynome sind bzw. in Polynome
    *   umgewandelt werden können. Was passiert, wenn eines oder beides keine Polynome sind oder
    *  nur teilweise in Polynome und einem nicht polynomiellen Teil aufgelöst werden können?
    *   d.h. es ist zwischen rationalen Funktionen (nenner und zähler sind polynome) und nicht
    *   rationalen Funktionen (ln e^x) zu unterscheiden. Ist es möglich die symbolische Algebra
    *   in diese beiden Probleme aufzuteilen und unterschiedlich zu behandeln? Geht es auch wenn
    *   Polynome und andere Ausdrücke gemischt werden?
    *
    *   vielleicht angenommen left und right sind polynome:
    *
    *   return left.poly() op right.poly() // für op = +, -, *, /, %
    *
    *   und wenn polynome und exponentielle funktionen einschließlich logarithmus gemischt sind:
    *
    *   val polynom = left.poly() op right.poly()
    *   val rest = left.rest() op right.rest()
    *   return EineExpression(polynom, rest)
    */
    override fun simplify(): Expression {


        val leftExpression = left.simplify()
        val rightExpression = right.simplify()

        //TODO("Wenn rechts und links gleichwertige Variablen sind, werden die Koeffizienten addiert")


        //Wenn beide Expression zusammengefasst Zahlen sind, können sie einfach addiert werden: Bsp. 2 + 3 = 5
        if (leftExpression is NumberExpr && rightExpression is NumberExpr) {
            return NumberExpr(leftExpression.n / rightExpression.n)
        }

        return Division(leftExpression, rightExpression);
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

