package com.majorkik.ui.details.ui.tv

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.ui.details.ui.destinations.TVDetailsScreenDestination
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused")
internal class TVDetailsViewModel(
    handle: SavedStateHandle,
    private val getTVDetailsUseCase: GetTVDetailsUseCase,
) : ViewModel(), ContainerHost<TVDetailsViewState, TVDetailsSideEffect> {
    // Arguments
    private val args = TVDetailsScreenDestination.argsFrom(handle)

    // Initialization container
    override val container: Container<TVDetailsViewState, TVDetailsSideEffect> =
        container(TVDetailsViewState()) { state ->
            if (state.screen !is State.TVDetailsState) {
                fetchTVDetails()
            }
        }

    private fun fetchTVDetails() = viewModelScope.launch {
        val details = getTVDetailsUseCase(args.tvId)

        intent {
            reduce { state.copy(screen = State.TVDetailsState(data = details)) }
        }
    }
}

@Immutable
internal sealed class State {
    data object LoadingState : State()
    data object ErrorState : State()
    data class TVDetailsState(val data: TVDetails) : State()
}

@Immutable
internal data class TVDetailsViewState(
    val screen: State = State.LoadingState,
)

internal sealed class TVDetailsSideEffect
