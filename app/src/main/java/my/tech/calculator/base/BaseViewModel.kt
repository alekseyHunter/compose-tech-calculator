package my.tech.calculator.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import nmstu.youth.base.architecture.UiAction
import nmstu.youth.base.architecture.UiEvent
import nmstu.youth.base.architecture.UiState

abstract class BaseViewModel <Event: UiEvent, State: UiState, Action: UiAction> (initialState: State): ViewModel() {

    private val _viewStates : MutableStateFlow<State> = MutableStateFlow(initialState)
    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    private val _viewActions = MutableSharedFlow<Action?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    protected var viewAction: Action?
        get() = _viewActions.replayCache.last()
        set(value) {
            _viewActions.tryEmit(value)
        }

    fun viewActions(): SharedFlow<Action?> =_viewActions.asSharedFlow()
    fun viewStates(): StateFlow<State> = _viewStates.asStateFlow()

    /**
     * Handle each event
     */
    abstract fun obtainEvent(event : Event)
}