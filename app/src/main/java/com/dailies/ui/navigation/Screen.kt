package com.dailies.ui.navigation

sealed class Screen (
    val route: String
){
    object Home: Screen("home")
    object Week: Screen("week")
    object Setting: Screen("setting")
    object Daily: Screen("daily")
    object Edit: Screen("edit")
    object Add: Screen("add")

}