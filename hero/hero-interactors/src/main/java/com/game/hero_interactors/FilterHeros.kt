package com.game.hero_interactors

import com.game.core.FilterOrder
import com.game.hero_domain.Hero
import com.game.hero_domain.HeroAttribute
import com.game.hero_domain.HeroFilter
import kotlin.math.round

class FilterHeros {

    fun execute(
        current: List<Hero>,
        heroName: String,
        heroFilter: HeroFilter,
        attributeFilter: HeroAttribute
    ):List<Hero>{
        var filteredList: MutableList<Hero> = current.filter {
            it.localizedName.lowercase().contains(heroName.lowercase())
        }.toMutableList()

        when(heroFilter){
            is HeroFilter.Hero ->{
                when(heroFilter.order){
                    is FilterOrder.Descending->{
                        filteredList.sortByDescending { it.localizedName }
                    }
                    is FilterOrder.Ascending ->{
                        filteredList.sortBy { it.localizedName }
                    }
                }
            }
            is HeroFilter.ProWins ->{
                when(heroFilter.order){
                    is FilterOrder.Descending->{
                        filteredList.sortByDescending {
                            getWinRate(it)
                        }
                    }
                    is FilterOrder.Ascending ->{
                        filteredList.sortBy {
                            getWinRate(it)
                        }
                    }
                }
            }
        }

        when(attributeFilter){
            is HeroAttribute.Strength ->{
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Strength }.toMutableList()
            }
            is HeroAttribute.Unknown ->{
            }
            is HeroAttribute.Intelligence ->{
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Intelligence }.toMutableList()
            }
            is HeroAttribute.Agility ->{
                filteredList = filteredList.filter { it.primaryAttribute is HeroAttribute.Agility }.toMutableList()
            }
        }

        return filteredList
    }

    private fun getWinRate(hero: Hero): Int{
        return if (hero.proPick <= 0) {
            0
        }else {
            round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()
        }
    }

}