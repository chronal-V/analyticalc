import kotlin.math.pow

fun square(a: Double) = a * a

fun root (num: Double, root: Double) : Double {
    var x: Double = 1.0;
    for (i in 0..100) {
        val f = x.pow(root) - num
        val g = root * (x.pow(root-1))
        x -= ((f / g))
    }
    return x;
}

fun sqrt(num: Double) = root(num, 2.0)


