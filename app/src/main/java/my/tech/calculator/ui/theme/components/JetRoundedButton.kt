package my.tech.calculator.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.tech.calculator.ui.theme.MyTechCalculatorTheme
import my.tech.calculator.utils.innerShadow

@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = buttonColors.contentColor()
        )
    }
}

@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = buttonColors.contentColor()
        )
    }
}

@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = null,
            tint = buttonColors.contentColor()
        )
    }
}

object JetRoundedButtonDefaults {

    @Composable
    fun numberButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        shadowContainerColor: Color = Color.Black.copy(alpha = 0.6f),
    ): JetRoundedButtonColors = JetRoundedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        shadowContainerColor = shadowContainerColor
    )

    @Composable
    fun operationButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.secondary,
        contentColor: Color = MaterialTheme.colorScheme.onSecondary,
        shadowContainerColor: Color = Color.Black.copy(alpha = 0.6f),
    ): JetRoundedButtonColors = JetRoundedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        shadowContainerColor = shadowContainerColor
    )
}

@Immutable
class JetRoundedButtonColors internal constructor(
    private val containerColor: Color,
    private val contentColor: Color,
    private val shadowContainerColor: Color
) {
    @Composable
    internal fun containerColor(): Color {
        return containerColor
    }

    @Composable
    internal fun contentColor(): Color {
        return contentColor
    }

    @Composable
    internal fun shadowContainerColor(): Color {
        return shadowContainerColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is JetRoundedButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (shadowContainerColor != other.shadowContainerColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + shadowContainerColor.hashCode()
        return result
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview() {
    MyTechCalculatorTheme {
        JetRoundedButton(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp), text = "+",
            JetRoundedButtonDefaults.numberButtonColors()
        ) {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview2() {
    MyTechCalculatorTheme {
        JetRoundedButton(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp), text = "+",
            JetRoundedButtonDefaults.operationButtonColors()
        ) {

        }
    }
}