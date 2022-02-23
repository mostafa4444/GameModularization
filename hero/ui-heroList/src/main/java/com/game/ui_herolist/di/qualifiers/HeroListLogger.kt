package com.game.ui_herolist.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class HeroListLogger(
    /** The name.  */
    val value: String = "heroListLogger"
)