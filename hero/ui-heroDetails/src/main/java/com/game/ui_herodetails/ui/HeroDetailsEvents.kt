package com.game.ui_herodetails.ui

sealed class HeroDetailsEvents {
    data class GetHeroFromCache(
        val id: Int?
    ): HeroDetailsEvents()
}