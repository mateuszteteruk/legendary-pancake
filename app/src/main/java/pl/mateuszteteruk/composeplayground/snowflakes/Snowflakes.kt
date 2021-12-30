package pl.mateuszteteruk.composeplayground.snowflakes

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.isActive
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.roundToInt

@Composable
fun Snowflakes() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.Black)
            .snow()
    )
}

fun Modifier.snow(): Modifier = composed {
    var snowflakesState by remember {
        mutableStateOf(
            SnowfallState(
                listOf()
            )
        )
    }

    var lastTick by remember { mutableStateOf(-1L) }

    LaunchedEffect(Unit) {
        while (isActive) {
            withFrameMillis { newTick ->
                if (lastTick < 0) {
                    lastTick = newTick
                    return@withFrameMillis
                }
                val elapsed = newTick - lastTick
                lastTick = newTick

                snowflakesState.snowflakes.forEach {
                    it.update(elapsed)
                }
            }
        }
    }

    onSizeChanged { size ->
        snowflakesState = snowflakesState.resize(size)
    }
        .clipToBounds()
        .drawWithContent {
            drawContent()
            val canvas = drawContext.canvas
            snowflakesState.snowflakes.forEach { it.draw(canvas = canvas) }
        }
}

data class SnowfallState(
    val snowflakes: List<Snowflake>,
    val sizeRange: ClosedRange<Float> = 5F..12F,
    val incrementFactorRange: ClosedRange<Float> = 0.4F..0.8F,
    @FloatRange(from = 0.0, to = 1.0) val density: Float = .1F,
) {

    init {
        require(density in 0F..1F) { "Density must be between 0F - 1F" }
    }

    fun resize(size: IntSize): SnowfallState =
        copy(
            snowflakes = createSnowflakes(
                sizeRange = sizeRange,
                canvasSize = size,
                density = density,
                incrementFactorRange = incrementFactorRange,
            )
        )

    private companion object {

        private const val DENSITY_DIVIDER = 500F

        fun createSnowflakes(
            sizeRange: ClosedRange<Float>,
            incrementFactorRange: ClosedRange<Float>,
            canvasSize: IntSize,
            density: Float,
        ): List<Snowflake> {
            val count = (canvasSize.area * density / DENSITY_DIVIDER).roundToInt()
            return List(count) {
                Snowflake(
                    radius = sizeRange.random(),
                    position = canvasSize.randomPosition(),
                    canvasSize = canvasSize,
                    incrementFactor = incrementFactorRange.random(),
                )
            }
        }

        private fun ClosedRange<Float>.random(): Float =
            ThreadLocalRandom.current().nextFloat() * (endInclusive - start) + start

        private fun IntSize.randomPosition(): Offset =
            Offset(
                x = width.random().toFloat(),
                y = height.random().toFloat(),
            )

        private fun Int.random(): Int = ThreadLocalRandom.current().nextInt(this)

        private val IntSize.area: Int
            get() = width * height
    }
}

class Snowflake(
    private val radius: Float = 100F,
    private val canvasSize: IntSize,
    position: Offset,
    private val incrementFactor: Float,
) {

    var paint = Paint().apply {
        isAntiAlias = true
        color = Color.White
        style = PaintingStyle.Fill
        alpha = incrementFactor
    }

    private var position by mutableStateOf(position)

    fun draw(canvas: Canvas) {
        canvas.drawCircle(
            center = position,
            radius = radius,
            paint = paint
        )
    }

    fun update(elapsed: Long) {
        val deltaY = incrementFactor * (elapsed / frameDurationAt60Fps) * baseSpeedAt60Fps
        position = Offset(position.x, position.y + deltaY)
        if (position.y - radius > canvasSize.height) {
            position = position.copy(y = -radius)
        }
    }

    private companion object {

        const val frameDurationAt60Fps = 16F
        const val baseSpeedAt60Fps = 2F
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Snowflakes()
}
