package com.dailies.ui.navigation

/**
 * This class contains all the screens that the navigation required to access
 */
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

/**
 * The val below is used in navigation bar
 */
val screens = listOf(
    Screen.Home,
    Screen.Week,
)