package com.majorkik.ui.details.ui.tv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.majorkik.ui.details.ui.destinations.MovieDetailsScreenDestination
import com.majorkik.ui.details.ui.destinations.TVDetailsScreenDestination
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused")
internal class TVDetailsViewModel(
    handle: SavedStateHandle,
) : ViewModel(), ContainerHost<TVDetailsViewState, TVDetailsSideEffect> {
    // Arguments
    private val args = TVDetailsScreenDestination.argsFrom(handle)

    // Initialization container
    override val container: Container<TVDetailsViewState, TVDetailsSideEffect> = container(TVDetailsViewState())
}

sealed class State {
    data object LoadingState : State()
}

data class TVDetailsViewState(
    val screen: State = State.LoadingState,
)

sealed class TVDetailsSideEffect
