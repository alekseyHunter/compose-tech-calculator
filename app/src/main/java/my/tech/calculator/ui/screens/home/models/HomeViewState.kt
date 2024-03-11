package my.tech.calculator.ui.screens.home.models

data class HomeViewState(
    val displayExpression: StringBuilder = StringBuilder(),
    val privateExpression: StringBuilder = StringBuilder(),
    val currentExpressionItem: ExpressionItem = ExpressionItem.None,
    val expressionResult: String = "",
    val isDarkTheme: Boolean = false
)

