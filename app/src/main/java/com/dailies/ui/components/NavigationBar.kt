package com.dailies.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dailies.R
import com.dailies.ui.navigation.Screen
import com.dailies.ui.navigation.screens
import com.dailies.ui.theme.DailiesTheme

@Composable
fun NavigationBar(
    navController: NavController
) {
    val icons = mapOf (
        Screen.Home to IconGroup (
            filledIcon = Icons.Filled.Home,
            outlineIcon = Icons.Outlined.Home,
            label = stringResource (id = R.string.Home)
        )
    ,
    Screen.Week to IconGroup (
        filledIcon = Icons.Filled.Event,
        outlineIcon = Icons.Outlined.Event,
        label = stringResource (id = R.string.Week)
    )
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any {it.route == screen.route} == true
            val labelText = icons[screen]!!.label

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlineIcon),
                        contentDescription = labelText
                    )

                },
                label = {Text(labelText)},
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
private fun NavigationBarPreview() {
    val navController = rememberNavController()
    DailiesTheme(dynamicColor = false) {
        NavigationBar(navController)
    }
}