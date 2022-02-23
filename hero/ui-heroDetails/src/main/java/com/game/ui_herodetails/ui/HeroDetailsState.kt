package com.game.ui_herodetails.ui

import com.game.core.ProgressBarState
import com.game.hero_domain.Hero


data class HeroDetailsState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val hero: Hero ?= null
)