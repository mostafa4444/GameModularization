package com.game.modularization.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument


sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
){

    object HeroList: Screen("heroList" , emptyList())

    object HeroDetails: Screen("heroDetails" ,
        listOf(
            navArgument("heroId"){
                type = NavType.IntType
            }
        )
        )

}
