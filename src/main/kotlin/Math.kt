import kotlin.math.pow

const val epsilon = 0.000001

fun square(a: Double) = a * a

fun root(num: Double, root: Double): Double {
    var x = 1.0
    for (i in 0..100) {
        val f = x.pow(root) - num
        val g = root * (x.pow(root - 1))
        x -= ((f / g))
    }
    return x
}

fun sqrt(num: Double) = root(num, 2.0)


