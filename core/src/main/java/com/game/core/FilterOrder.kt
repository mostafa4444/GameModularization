package com.game.core

sealed class FilterOrder {

    object Ascending: FilterOrder()

    object Descending: FilterOrder()

}