package my.tech.calculator.ui.screens.home.models

import nmstu.youth.base.architecture.UiAction

sealed class HomeAction: UiAction {
    data class ShowAlertDialog(val description: String) : HomeAction()
}