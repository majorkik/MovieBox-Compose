package com.majorkik.ui.details.ui.movie

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.usecase.GetMovieDetailsByIdUseCase
import com.majorkik.ui.details.ui.destinations.MovieDetailsScreenDestination
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class MovieDetailsViewModel(
    handle: SavedStateHandle,
    private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase,
) : ViewModel(), ContainerHost<MovieDetailsViewState, MovieDetailsSideEffect> {
    // Arguments
    private val args = MovieDetailsScreenDestination.argsFrom(handle)

    // Initialization container
    override val container: Container<MovieDetailsViewState, MovieDetailsSideEffect> =
        container(MovieDetailsViewState()) { state ->
            if (state.screen !is State.MovieDetailsState) {
                actionFetchMovieDetails(id = args.movieId)
            }
        }

    private fun actionFetchMovieDetails(id: Int) = intent {
        when (val result = getMovieDetailsByIdUseCase(id)) {
            is Either.Left -> updateState(State.ErrorState)
            is Either.Right -> {
                updateState(State.MovieDetailsState(data = result.value))
            }
        }
    }

    private fun updateState(state: State) = intent {
        reduce { this.state.copy(screen = state) }
    }
}

@Immutable
internal sealed class State {
    data object LoadingState : State()
    data object ErrorState : State()
    data class MovieDetailsState(val data: MovieDetails) : State()
}

@Immutable
internal data class MovieDetailsViewState(
    val screen: State = State.LoadingState,
)

internal sealed class MovieDetailsSideEffect

