package com.game.modularization.di

import android.app.Application
import android.content.Context
import com.game.hero_interactors.HeroInteractors
import com.game.modularization.di.qualifiers.HeroAndroidSqlDriver
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroInteractorsModule {

    @Provides
    @Singleton
    @HeroAndroidSqlDriver
    fun provideAndroidDriver(applicationContext: Application): SqlDriver =
        AndroidSqliteDriver(
            schema = HeroInteractors.cacheSchema ,
            context = applicationContext,
            name = HeroInteractors.cacheName
        )


    @Provides
    @Singleton
    fun providesHeroInteractors(@HeroAndroidSqlDriver sqlDriver: SqlDriver): HeroInteractors =
        HeroInteractors.build(sqlDriver)


}