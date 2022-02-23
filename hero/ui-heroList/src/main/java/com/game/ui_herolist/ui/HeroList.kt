package com.game.ui_herolist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.game.core.ProgressBarState
import com.game.ui_herolist.components.HeroListItem
import com.game.ui_herolist.components.HeroListToolbar

@ExperimentalComposeUiApi
@Composable
fun HeroList(
    state: HeroListState,
    events: (HeroListEvents) -> Unit,
    imageLoader: ImageLoader,
    navigateToDetailsScreen: (Int) -> Unit
){
    Box{
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                HeroListToolbar(
                    heroName = state.heroName,
                    onHeroNameChanged = { newName->
                        events(HeroListEvents.UpdateHeroName(heroName = newName))
                    },
                    onExecuteSearch = {
                        events(HeroListEvents.FilterHero)
                    },
                    onShowFilterDialog ={

                    }
                )
                LazyColumn{
                    items(state.filteredHero){hero ->
                        HeroListItem(
                            hero = hero,
                            onSelectHero = {targetId->
                                navigateToDetailsScreen(targetId)
                            },
                            imageLoader = imageLoader
                        )
                    }
                }
            }

            if (state.progressBarState is ProgressBarState.Loading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
