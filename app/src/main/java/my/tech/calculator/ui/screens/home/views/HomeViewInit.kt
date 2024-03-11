package my.tech.calculator.ui.screens.home.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.tech.calculator.R
import my.tech.calculator.ui.screens.home.models.ExpressionItem
import my.tech.calculator.ui.screens.home.models.HomeViewState
import my.tech.calculator.ui.theme.MyTechCalculatorTheme
import my.tech.calculator.ui.theme.components.JetRoundedButtonDefaults
import my.tech.calculator.ui.theme.components.JetRoundedButton
import my.tech.calculator.ui.theme.components.JetSwitchButton

@Composable
fun HomeViewInit(
    viewState: HomeViewState,
    onChangeTheme: (Boolean) -> Unit,
    onChangeExpression: (ExpressionItem) -> Unit,
    onClearExpression: () -> Unit,
    onRemoveLastSymbol: () -> Unit,
    onCalculateExpression: () -> Unit
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth()
                .height(208.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(start = 32.dp, end = 64.dp, top = 32.dp, bottom = 16.dp)
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewState.displayExpression.toString(),
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (viewState.expressionResult.isEmpty()) "0" else "=${viewState.expressionResult}",
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            JetSwitchButton(
                modifier = Modifier.align(Alignment.TopStart),
                isChecked = false,
                onValueChange = onChangeTheme
            )
        }

        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (this.maxWidth > 400.dp) {
                val elementMargin = 16.dp
                val elementWidth = this.maxWidth / 4 - elementMargin
                val elementHeight = this.maxHeight / 6 - elementMargin
                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(elementMargin)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(elementMargin)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "C",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onClearExpression.invoke()
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "√",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationSqrt)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "1",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value1)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "4",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value4)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "7",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value7)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = ".",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.ValuePoint)
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(elementMargin)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "(",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.LeftBracket)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "%",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationPercent)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "2",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value2)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "5",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value5)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "8",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value8)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "0",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value0)
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(elementMargin)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = ")",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.RightBracket)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = buildAnnotatedString {
                                append("x")
                                withStyle(
                                    SpanStyle(
                                        baselineShift = BaselineShift.Superscript,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.White
                                    )
                                ) {
                                    append("y")
                                }
                            },
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationSqr)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "3",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value3)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "6",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value6)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "9",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value9)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            iconId = R.drawable.ic_backspace,
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onRemoveLastSymbol.invoke()
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(elementMargin)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "×",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationMul)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "÷",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationDiv)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "+",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationPlus)
                            })

                        JetRoundedButton(modifier = Modifier.size(elementWidth, elementHeight),
                            text = "-",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationMinus)
                            })

                        JetRoundedButton(modifier = Modifier.size(
                            elementWidth,
                            elementHeight.times(2) + 16.dp
                        ),
                            text = "=",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onCalculateExpression.invoke()
                            })
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "C",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onClearExpression.invoke()
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "√",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationSqrt)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "1",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value1)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "4",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value4)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "7",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value7)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = ".",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.ValuePoint)
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "(",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.LeftBracket)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "%",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationPercent)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "2",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value2)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "5",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value5)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "8",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value8)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "0",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value0)
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = ")",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.RightBracket)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = buildAnnotatedString {
                                append("x")
                                withStyle(
                                    SpanStyle(
                                        baselineShift = BaselineShift.Superscript,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.White
                                    )
                                ) {
                                    append("y")
                                }
                            },
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationSqr)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "3",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value3)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "6",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value6)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "9",
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.Value9)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            iconId = R.drawable.ic_backspace,
                            buttonColors = JetRoundedButtonDefaults.numberButtonColors(),
                            onClick = {
                                onRemoveLastSymbol.invoke()
                            })
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "×",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationMul)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "÷",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationDiv)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "+",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationPlus)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp),
                            text = "-",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onChangeExpression.invoke(ExpressionItem.OperationMinus)
                            })

                        JetRoundedButton(modifier = Modifier.size(64.dp, 144.dp),
                            text = "=",
                            buttonColors = JetRoundedButtonDefaults.operationButtonColors(),
                            onClick = {
                                onCalculateExpression.invoke()
                            })
                    }
                }
            }
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ShowPreview() {
    MyTechCalculatorTheme {
        HomeViewInit(viewState = HomeViewState(), {}, {}, {}, {}, {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShowPreview2() {
    MyTechCalculatorTheme {
        HomeViewInit(viewState = HomeViewState(), {}, {}, {}, {}, {})
    }
}