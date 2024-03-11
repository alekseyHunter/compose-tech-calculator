package my.tech.calculator.ui.screens.home.models

sealed class HomeEvent {
    data class ChangeTheme(val newValue: Boolean) : HomeEvent()
    data class ChangeExpression(val newValue: ExpressionItem) : HomeEvent()
    data object CalculateExpression : HomeEvent()
    data object RemoveLastSymbol : HomeEvent()
    data object ClearExpression : HomeEvent()
}