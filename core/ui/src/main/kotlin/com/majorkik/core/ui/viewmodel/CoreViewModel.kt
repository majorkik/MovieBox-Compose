package com.majorkik.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class CoreViewModel<State, Event, Effect>(initialState: State) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _sideEffect = Channel<Effect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    abstract fun reduce(viewEvent: Event)

    fun updateState(newState: State) {
        _state.value = newState
    }

    fun updateState(block: State.() -> State) {
        _state.value = state.value.block()
    }

    suspend fun sendEffect(effect: Effect) {
        _sideEffect.send(effect)
    }


}
