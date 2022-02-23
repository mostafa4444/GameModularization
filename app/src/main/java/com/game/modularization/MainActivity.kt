package com.game.modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.game.modularization.navigation.Screen
import com.game.modularization.ui.theme.GameModularizationTheme
import com.game.ui_herodetails.components.HeroDetail
import com.game.ui_herodetails.ui.HeroDetailsViewModel
import com.game.ui_herolist.ui.HeroList
import com.game.ui_herolist.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameModularizationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HeroList.route,
                    builder = {
                        addHeroList(navController , imageLoader)
                        addHeroDetails(imageLoader = imageLoader)
                    }
                )

            }
        }
    }
}

@ExperimentalComposeUiApi
fun NavGraphBuilder.addHeroList(
    navController: NavController,
    imageLoader: ImageLoader
){
    composable(
        route = Screen.HeroList.route
    ){
        val viewModel: HeroListViewModel = hiltViewModel()
        HeroList(
            state = viewModel.state.value,
            events= viewModel::onTriggerEvent,
            imageLoader = imageLoader,
            navigateToDetailsScreen = {id->
                navController.navigate("${Screen.HeroDetails.route}/$id")
            }
        )
    }
}

fun NavGraphBuilder.addHeroDetails(
    imageLoader: ImageLoader
){
    composable(
        route = Screen.HeroDetails.route+"/{heroId}",
        arguments = Screen.HeroDetails.arguments
    ){
        val viewModel: HeroDetailsViewModel = hiltViewModel()
        HeroDetail(
            state = viewModel.state.value,
            imageLoader = imageLoader
        )
    }
}
