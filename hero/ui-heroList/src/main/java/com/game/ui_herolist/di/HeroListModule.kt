package com.game.ui_herolist.di

import com.game.core.Logger
import com.game.hero_interactors.FilterHeros
import com.game.hero_interactors.GetHeros
import com.game.hero_interactors.HeroInteractors
import com.game.ui_herolist.di.qualifiers.HeroListLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {

    @Provides
    @Singleton
    fun provideGetHeros(heroInteractors: HeroInteractors): GetHeros=
        heroInteractors.getHeros

    @Provides
    @Singleton
    @HeroListLogger
    fun provideLogger(): Logger =
        Logger("HeroList" , true)


    @Provides
    @Singleton
    fun provideFilterHeros(heroInteractors: HeroInteractors): FilterHeros=
        heroInteractors.filterHeros
}