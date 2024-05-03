package com.dailies.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.dailies.R
import com.dailies.ui.navigation.Screen

/**
 * The main scaffold that will be used in many screens, It holds a back button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold (
    navController: NavHostController,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {},
    ){
    Scaffold(
        topBar = {
            /**
             * Top app bar ensures the scaffold appear at the top of the screen
             */
            TopAppBar(title = {
            },
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon (
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.Back)
                        )
                    }
                })
        },
        content = {
                innerPadding -> pageContent (innerPadding)
        }
    )
}

/**
 * This scaffold is used when going into a daily screen. It contains an extra button which allows navigation into the add screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScaffold (
    navController: NavHostController,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {},
){
    Scaffold(
        topBar = {
            TopAppBar(title = {
            },
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon (
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.Back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {navController.navigate(route = Screen.Add.route)}) {
                        Icon (
                            imageVector = Icons.Filled.AddAlarm,
                            contentDescription = stringResource(R.string.Add)
                        )
                    }
                }
                )
        },

        content = {
                innerPadding -> pageContent (innerPadding)
        }
    )
}

/**
 * This scaffold holds a bottom bar that contains the navigaiton bar
 */
@Composable
fun NavBarScaffold (
    navController: NavHostController,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {},


    ){
    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        },
        content = {
                innerPadding -> pageContent (innerPadding)
        }
    )

}