package expressions

//
interface Expression {}
//open class Expression()  {
//}

// Der Vorteil von data class gegenüber class:
// Die Methoden equals und hashCode werden anhand der Werte automatisch bestimmt:
// z.B:
// val a = Sum(NumberExpr(1), NumberExpr(2))
// val b = Sum(NumberExpr(1), NumberExpr(2))
// a shouldBe b => true
//
// Wären es normale Klassen, würde nur die Objektreferenz verglichen. Da
// es aber zwei verschiedene Objekte sind, käme false als Ergebnis heraus.
// Semantisch sind die beiden Objekte aber identisch.
//
// Der Vorteil des Interface liegt darin, dass du erstens keine Klassenhierarchie
// brauchst. Klassenhierarchien sollten nach Möglichkeit vermieden werden (auch wenn
// die es in der Schule anders gelernt hasst).
// Das Interface zwingt dich die vom Interface vorgegebenen (aber jetzt noch nicht
// definierten) Methoden in Expression Objekten zu implementieren. Aber jede
// implementierte Methode wird voraussichtlich für jede Klasse, die Expression
// implementiert unterschiedlich sein.

data class Sum(val left: Expression, val right: Expression) : Expression {}

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

