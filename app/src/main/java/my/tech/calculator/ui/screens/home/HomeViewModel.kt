package my.tech.calculator.ui.screens.home

import my.tech.calculator.base.BaseViewModel
import my.tech.calculator.ui.screens.home.models.ExpressionItem
import my.tech.calculator.ui.screens.home.models.ExpressionItemType
import my.tech.calculator.ui.screens.home.models.HomeAction
import my.tech.calculator.ui.screens.home.models.HomeEvent
import my.tech.calculator.ui.screens.home.models.HomeViewState
import my.tech.calculator.utils.MathModule
import java.text.ParseException
import kotlin.math.roundToLong

class HomeViewModel : BaseViewModel<HomeEvent, HomeViewState, HomeAction>(
    HomeViewState()
) {
    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeTheme -> {

            }

            is HomeEvent.ChangeExpression -> {
                when (event.newValue.type) {
                    is ExpressionItemType.Value -> addValue(viewState, event.newValue)
                    is ExpressionItemType.Bracket -> addBracket(viewState, event.newValue)
                    is ExpressionItemType.Operation -> addOperation(viewState, event.newValue)
                    is ExpressionItemType.Empty -> clearExpression()
                }
            }

            is HomeEvent.CalculateExpression -> equality(viewState)
            is HomeEvent.ClearExpression -> clearExpression()
            is HomeEvent.RemoveLastSymbol -> removeSymbol(viewState)
        }
    }

    private fun addValue(state: HomeViewState, item: ExpressionItem) {
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
        expression.append(item.value)
        privateExpression.append(item.value)
        val result = calculateResult(privateExpression.toString())

        viewState = state.copy(
            displayExpression = expression,
            privateExpression = privateExpression,
            currentExpressionItem = item,
            expressionResult = result
        )
    }

    private fun addBracket(state: HomeViewState, item: ExpressionItem) {
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
        expression.append(item.value)
        privateExpression.append(item.value)

        val result = calculateResult(privateExpression.toString())

        viewState = state.copy(
            expression, privateExpression, item, result
        )
    }

    private fun addOperation(state: HomeViewState, item: ExpressionItem) {
        val expression = state.displayExpression
        val privateExpression = state.privateExpression

        if (state.currentExpressionItem.type == ExpressionItemType.Operation) {
            expression.deleteCharAt(expression.lastIndex)
            privateExpression.deleteCharAt(privateExpression.lastIndex)
        }

        expression.append(item.value)
        privateExpression.append(item.value)
        viewState = state.copy(
            expression, privateExpression, item
        )
    }

    private fun clearExpression() {
        viewState = HomeViewState(
            StringBuilder(), StringBuilder(), ExpressionItem.None, ""
        )
    }

    private fun removeSymbol(state: HomeViewState) {
        if (state.displayExpression.isNotEmpty()) {
            val expression = state.displayExpression
            val privateExpression = state.privateExpression
            expression.deleteCharAt(expression.lastIndex)
            privateExpression.deleteCharAt(privateExpression.lastIndex)
            val expressionItem =
                if (expression.isEmpty()) ExpressionItem.None else ExpressionItem.convertToExpression(
                    expression.last().toString()
                )

            val result = calculateResult(privateExpression.toString())

            viewState = state.copy(
                expression, privateExpression, expressionItem, result
            )
        }
    }

    private fun equality(state: HomeViewState) {
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
        val result = calculateResult(privateExpression.toString())
        expression.clear()
        privateExpression.clear()
        privateExpression.append(result)
        expression.append(result)

        viewState = state.copy(
            expression, privateExpression, ExpressionItem.None, result
        )
    }

    private fun calculateResult(expression: String): String {
        return try {
            val mathModule = MathModule(expression)
            val result = mathModule.calculate()
            convertResultToString(result)
        } catch (exception: ParseException) {
            ""
        } catch (exception: Exception) {
            exception.localizedMessage ?: "Unknown error"
        }
    }

    private fun convertResultToString(result: Double): String {
        return if ((result - result.toLong()) != 0.0) {
            "${String.format("%.2f", result)}"
        } else {
            "${result.roundToLong()}"
        }
    }

}