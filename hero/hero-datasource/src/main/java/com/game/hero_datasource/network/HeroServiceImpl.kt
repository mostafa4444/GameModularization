package com.game.hero_datasource.network

import com.game.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.request.*

class HeroServiceImpl(
    private val httpClient: HttpClient
): HeroService {

    override suspend fun getHeroStats(): List<Hero> {
        return httpClient.get<List<HeroDto>>{
            url(EndPoint.HERO_STATS)
        }.map {
            it.toHero()
        }
    }
}