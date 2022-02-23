package com.game.core

sealed class ProgressBarState{
    object Loading: ProgressBarState()
    object Idle: ProgressBarState()
}
