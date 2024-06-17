package my.tech.calculator.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import my.tech.calculator.ui.screens.home.models.HomeEvent
import my.tech.calculator.ui.screens.home.views.HomeViewInit

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val viewState = viewModel.viewStates().collectAsState()

    with(viewState.value) {
        HomeViewInit(
            viewState = this,
            onChangeTheme = {
                viewModel.obtainEvent(HomeEvent.ChangeTheme(it))
            },
            onChangeExpression = {
                viewModel.obtainEvent(HomeEvent.ChangeExpression(it))
            },
            onCalculateExpression = {
                viewModel.obtainEvent(HomeEvent.CalculateExpression)
            },
            onClearExpression = {
                viewModel.obtainEvent(HomeEvent.ClearExpression)
            },
            onRemoveLastSymbol = {
                viewModel.obtainEvent(HomeEvent.RemoveLastSymbol)
            }
        )
    }
}