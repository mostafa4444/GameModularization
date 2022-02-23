package com.game.ui_herolist.ui

import com.game.core.ProgressBarState
import com.game.hero_domain.Hero
import com.game.hero_domain.HeroAttribute
import com.game.hero_domain.HeroFilter

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle ,
    val heros: List<Hero> = listOf(),
    val filteredHero: List<Hero> = listOf(),
    val heroName: String = "",
    val heroFilter: HeroFilter = HeroFilter.Hero(),
    val primaryAttribute: HeroAttribute = HeroAttribute.Unknown
)
