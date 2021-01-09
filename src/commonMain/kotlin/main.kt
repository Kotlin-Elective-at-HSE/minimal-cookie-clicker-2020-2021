import com.soywiz.korau.sound.PlaybackParameters
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.Korge
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onUp
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.ColorMatrixFilter
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.RectangleInt

const val UPGRADE_COST = 10

suspend fun main() = Korge(
    width = 612,
    height = 612,
    title = "Minimal Cookie Clicker"
) {

    var income = 1
    var current = 0

    val text = text("Click me!")
        .apply { smoothing = false }

    fun placeText() {
        text.alignX(this, 0.5, true)
        text.y = 10.0
    }
    placeText()

    fun updateText() {
        text.text = "Current: $current, income: $income"
        placeText()
    }

    val coin = resourcesVfs["coin.wav"].readSound()
    val cookie = resourcesVfs["perfectCookie.png"].readBitmap()
    val atlas = resourcesVfs["icons.png"].readBitmap()
    val upgrade = atlas.slice(RectangleInt(0, 48, 48, 48))

    val cookieButton = image(cookie).xy(50, 50)

    val upgradeButton = image(upgrade)
        .apply { smoothing = false }

    upgradeButton.alignRightToRightOf(this, 10.0)
    upgradeButton.alignBottomToBottomOf(this, 10.0)

    fun updateUpgradeColor() {
        upgradeButton.filter = when (current >= UPGRADE_COST) {
            true -> null
            false -> ColorMatrixFilter(ColorMatrixFilter.GRAYSCALE_MATRIX)
        }
    }
    updateUpgradeColor()

    cookieButton.onDown {
        coin.play(PlaybackParameters(volume = 0.1))

        current += income
        cookieButton.x += 10
        updateText()

        if (current >= UPGRADE_COST) {
            updateUpgradeColor()
        }
    }

    cookieButton.onUp {
        cookieButton.x -= 10
    }

    upgradeButton.onClick {
        if (current >= UPGRADE_COST) {
            current -= UPGRADE_COST
            ++income
            updateText()
            updateUpgradeColor()
        }
    }
}
