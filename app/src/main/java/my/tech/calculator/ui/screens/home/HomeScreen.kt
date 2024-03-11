package my.tech.calculator.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import my.tech.calculator.ui.screens.home.models.HomeEvent
import my.tech.calculator.ui.screens.home.models.HomeViewState
import my.tech.calculator.ui.screens.home.views.HomeViewInit

@Composable
fun HomeScreen() {

    HomeViewInit(
        viewState = HomeViewState(),
        onChangeTheme = {

        },
        onChangeExpression = {

        },
        onCalculateExpression = {

        },
        onClearExpression = {

        },
        onRemoveLastSymbol = {

        }
    )
}