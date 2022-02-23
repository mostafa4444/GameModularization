package com.game.modularization.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class HeroAndroidSqlDriver(
    /** The name.  */
    val value: String = "heroAndroidSqlDriver"
)
