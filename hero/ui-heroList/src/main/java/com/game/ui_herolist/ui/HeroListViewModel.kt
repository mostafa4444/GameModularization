package com.game.ui_herolist.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.core.DataState
import com.game.core.Logger
import com.game.core.UIComponent
import com.game.hero_interactors.FilterHeros
import com.game.hero_interactors.GetHeros
import com.game.ui_herolist.di.qualifiers.HeroListLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeros: GetHeros,
    @HeroListLogger private val logger: Logger,
    private val filterHeros: FilterHeros
): ViewModel() {

    val state: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        onTriggerEvent(HeroListEvents.GetHeros)
    }

    fun onTriggerEvent(events: HeroListEvents){
        when(events){
            is HeroListEvents.GetHeros ->{
                getHeros()
            }
            is HeroListEvents.FilterHero ->{
                filterHeros()
            }
            is HeroListEvents.UpdateHeroName ->{
                updateHeroName(events.heroName)
            }
        }
    }

    private fun updateHeroName(heroName: String) {
        state.value = state.value.copy(heroName= heroName)
    }

    private fun filterHeros() {
        val filterList = filterHeros.execute(
            current = state.value.heros,
            heroName = state.value.heroName,
            heroFilter = state.value.heroFilter,
            attributeFilter = state.value.primaryAttribute
        )
        state.value = state.value.copy(filteredHero = filterList)
    }

    private fun getHeros(){
        getHeros.execute().onEach {dataState ->
            when(dataState){
                is DataState.Data ->{
                    state.value = state.value.copy(heros = dataState.data ?: listOf())
                    filterHeros()
                }
                is DataState.Response ->{
                    when(dataState.uiComponent){
                        is UIComponent.Dialog->{
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None->{
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Loading ->{
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}