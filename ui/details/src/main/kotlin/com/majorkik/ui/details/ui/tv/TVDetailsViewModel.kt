package com.majorkik.ui.details.ui.tv

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.majorkik.core.ui.viewmodel.CoreViewModel
import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.ui.details.ui.destinations.TVDetailsScreenDestination
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

internal class TVDetailsViewModel(
    handle: SavedStateHandle,
    private val getTVDetailsUseCase: GetTVDetailsUseCase,
) : CoreViewModel<TVDetailsState, TVDetailsEvent, TVDetailsEffect>(TVDetailsState.Loading) {

    private val args = TVDetailsScreenDestination.argsFrom(handle)

    init {
        processTVDetails()
    }

    private fun processTVDetails() {
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, _ -> updateState(TVDetailsState.Error) },
            block = {
                updateState(TVDetailsState.Loading)

                val details = getTVDetailsUseCase(args.tvId)

                updateState(TVDetailsState.Content(details = details))
            }
        )
    }

    override fun reduce(viewEvent: TVDetailsEvent) {
        when (viewEvent) {
            TVDetailsEvent.Refresh -> processTVDetails()
        }
    }
}

@Immutable
internal sealed class TVDetailsState {
    data object Loading : TVDetailsState()
    data object Error : TVDetailsState()
    data class Content(val details: TVDetails) : TVDetailsState()
}

internal sealed class TVDetailsEvent {
    data object Refresh : TVDetailsEvent()
}

internal sealed class TVDetailsEffect
