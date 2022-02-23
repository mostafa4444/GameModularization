package com.game.hero_interactors

import com.game.hero_datasource.cache.HeroCache
import com.game.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeros: GetHeros ,
    val getHeroFromCache: GetHeroFromCache
    ) {
    companion object Factory{
        fun build(sqlDriver: SqlDriver): HeroInteractors{
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeros = GetHeros(service= service , cache = cache),
                getHeroFromCache = GetHeroFromCache(cache)
            )
        }
        val cacheSchema: SqlDriver.Schema = HeroCache.schema
        val cacheName = HeroCache.dbName
    }


}