import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import kotlin.math.pow
import kotlin.random.Random

// Denke daran, das Kotest Plugin zu installieren!
// Das ist standardmäßig nicht installiert.
class MathTest : FunSpec({

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
        val rn = Random.nextDouble()
        sqrt(rn) shouldBe (kotlin.math.sqrt(rn) plusOrMinus epsilon)
        val rBase = Random.nextDouble()
        val rRoot = Random.nextDouble()

        // Ja, manchmal funktioniert die Funktion. Manchmal auch nicht.
        root(rBase, rRoot).pow(rRoot) shouldBe (rBase plusOrMinus epsilon)
    }

    // Hierfür musst du testImplementation("io.kotest:kotest-framework-datatest:$kotest_version")
    // in build.gradle.kts hinzufügen.
    // Datengetriebene Tests haben den Vorteil, dass du für unterschiedliche Testwerte
    // nicht immer einen neuen Test schreiben musst. Regel DRY: Don't repeat yourself.
    // Wenn DRY angewendet wird, ist es einfacher, Änderungen am Code vorzunehmen.
    // Siehe https://kotest.io/docs/framework/datatesting/data-driven-testing.html
    context("sqrt(square(a)) sollte immer a sein") {
        withData(
            nameFn = { "sqrt(square(${it})) sollte $it sein" },
            4.0,
            5.0,
            384530475.23,
            Random.nextDouble()
        ) { number ->
            sqrt(square(number)) shouldBe (number plusOrMinus epsilon)
        }
    }

    data class RootTest(val a: Double, val n: Double, val erwartet: Double)
    context("root(a) sollte richtig berechnet werden") {
        withData(
            nameFn = { "root($it.a, $it.n) = $it.b" },
            RootTest(4.0, 2.0, 2.0),
            RootTest(64.0, 3.0, 4.0)
        ) { testfall ->
            root(testfall.a, testfall.n) shouldBe (testfall.erwartet plusOrMinus epsilon)
        }
    }
})
