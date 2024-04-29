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
    object Monday:Screen("monday")
    object Tuesday:Screen("tuesday")
    object Wednesday:Screen("wednesday")
    object Thursday:Screen("thursday")
    object Friday:Screen("friday")
    object Saturday:Screen("saturday")
    object Sunday:Screen("sunday")
    object Notify:Screen("notify")






}

val screens = listOf(
    Screen.Home,
    Screen.Week,
)