package com.game.ui_herodetails.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class HeroDetailsLogger(
    val value: String = "heroDetailsLogger"
)
