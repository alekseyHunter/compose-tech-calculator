package my.tech.calculator.ui.screens.home.models

import nmstu.youth.base.architecture.UiEvent

sealed class HomeEvent : UiEvent {
    data class ChangeTheme(val newValue: Boolean) : HomeEvent()
    data class ChangeExpression(val newValue: ExpressionItem) : HomeEvent()
    data object CalculateExpression : HomeEvent()
    data object RemoveLastSymbol : HomeEvent()
    data object ClearExpression : HomeEvent()
}