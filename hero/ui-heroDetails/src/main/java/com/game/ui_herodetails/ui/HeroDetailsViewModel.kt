package com.game.ui_herodetails.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.core.DataState
import com.game.core.Logger
import com.game.core.UIComponent
import com.game.hero_domain.Hero
import com.game.hero_interactors.GetHeroFromCache
import com.game.ui_herodetails.di.qualifiers.HeroDetailsLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val getHeroFromCache: GetHeroFromCache,
    @HeroDetailsLogger private val logger: Logger,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){


    val state: MutableState<HeroDetailsState> = mutableStateOf(HeroDetailsState())

    init {
        savedStateHandle.get<Int>("heroId")?.let {heroId ->
            onTriggerEvent(HeroDetailsEvents.GetHeroFromCache(heroId))
        }
    }

    private fun onTriggerEvent(events: HeroDetailsEvents){
        when(events){
            is HeroDetailsEvents.GetHeroFromCache ->{
                getHeroById(events.id)
            }
        }
    }

    private fun getHeroById(id: Int?) {

        getHeroFromCache.execute(id!!).onEach {dataState ->
            when(dataState){
                is DataState.Data ->{
                    state.value = state.value.copy(hero = dataState.data)
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
//                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}