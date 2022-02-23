package com.game.ui_herodetails.di

import com.game.core.Logger
import com.game.hero_interactors.HeroInteractors
import com.game.ui_herodetails.di.qualifiers.HeroDetailsLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroDetailsModule {

    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        interactors: HeroInteractors
    ) =
        interactors.getHeroFromCache


    @Provides
    @Singleton
    @HeroDetailsLogger
    fun provideLogger(): Logger =
        Logger("HeroDetails" , true)

}