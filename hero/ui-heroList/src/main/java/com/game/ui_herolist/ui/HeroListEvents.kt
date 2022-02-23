package com.game.ui_herolist.ui

sealed class HeroListEvents{

    object GetHeros: HeroListEvents()

    object FilterHero: HeroListEvents()

    data class UpdateHeroName(
        val heroName: String
    ): HeroListEvents()

}
