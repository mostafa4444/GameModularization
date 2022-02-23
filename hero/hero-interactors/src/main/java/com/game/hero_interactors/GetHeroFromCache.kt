package com.game.hero_interactors

import com.game.core.DataState
import com.game.core.ProgressBarState
import com.game.core.UIComponent
import com.game.hero_datasource.cache.HeroCache
import com.game.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroFromCache(
    private val cache: HeroCache
) {
    fun execute(id: Int): Flow<DataState<Hero>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val cachedHero = cache.getHero(id) ?: throw Exception("This Hero does not exist in the database")
            emit(DataState.Data(cachedHero))
        }catch (e: Exception){
            e.printStackTrace()
            emit(
                DataState.Response<Hero>(
                uiComponent = UIComponent.Dialog(
                    title = "Error",
                    description = e.message?: "Unknown error"
                )
            ))
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

}