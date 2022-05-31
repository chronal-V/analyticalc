import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.shouldBe
import kotlin.math.pow
import kotlin.random.Random


class Test : FunSpec({
    test("my first test") {
        1 + 2 shouldBe 3
    }
    test("Squares") {
        square(2.0) shouldBe 4.0
    }
    test("Roots") {
        root(4.0, 2.0) shouldBe 2.0
        root(64.0, 3.0) shouldBe 4.0
        square(sqrt(4.0)) shouldBe 4.0
        sqrt(square(5.0)) shouldBe 5.0
        sqrt(square(384530475.23)) shouldBe 384530475.23
        val rn = Random.nextDouble();
        sqrt(rn) shouldBeExactly kotlin.math.sqrt(rn)
        val rBase = Random.nextDouble();
        val rRoot = Random.nextDouble();
        root(rBase, rRoot).pow(rRoot) shouldBeExactly rBase
    }
})