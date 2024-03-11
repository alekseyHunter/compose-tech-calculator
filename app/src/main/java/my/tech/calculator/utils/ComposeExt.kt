package my.tech.calculator.utils

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.innerShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetY: Dp,
    offsetX: Dp,
    spread: Dp
) = drawWithContent {
    drawContent() // Rendering the content

    val rect = Rect(Offset.Zero, size)
    val paint = Paint().apply {
        this.color = color
        this.isAntiAlias = true
    }

    val shadowOutline = shape.createOutline(size, layoutDirection, this)

    drawIntoCanvas { canvas ->

        // Save the current layer.
        canvas.saveLayer(rect, paint)
        // Draw the first layer of the shadow.
        canvas.drawOutline(shadowOutline, paint)

        // Convert the paint to a FrameworkPaint.
        val frameworkPaint = paint.asFrameworkPaint()
        // Set xfermode to DST_OUT to create the inner shadow effect.
        frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        // Apply blur if specified.
        if (blur.toPx() > 0) {
            frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }

        // Change paint color to black for the inner shadow.
        paint.color = Color.Black

        // Calculate offsets considering spread.
        val spreadOffsetX =
            offsetX.toPx() + if (offsetX.toPx() < 0) -spread.toPx() else spread.toPx()
        val spreadOffsetY =
            offsetY.toPx() + if (offsetY.toPx() < 0) -spread.toPx() else spread.toPx()

        // Move the canvas to specific offsets.
        canvas.translate(spreadOffsetX, spreadOffsetY)

        // Draw the second layer of the shadow.
        canvas.drawOutline(shadowOutline, paint)

        // Restore the canvas to its original state.
        canvas.restore()
    }
}