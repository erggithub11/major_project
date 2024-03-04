package com.dailies.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.*

import androidx.compose.ui.res.stringResource
import com.dailies.R
import com.dailies.ui.navigation.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold (
    navController: NavHostController,
    floatingActionButton: @Composable () -> Unit = { },
    snackbarContent: @Composable (SnackbarData) -> Unit = {},
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState? = null,
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
                })
        },
        content = {
                innerPadding -> pageContent (innerPadding)
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScaffold (
    navController: NavHostController,
    floatingActionButton: @Composable () -> Unit = { },
    snackbarContent: @Composable (SnackbarData) -> Unit = {},
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState? = null,
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
                    IconButton(onClick = {navController.navigate(route = Screen.Edit.route)}) {
                        Icon (
                            imageVector = Icons.Filled.Edit,
                            contentDescription = stringResource(R.string.Back)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScaffold (
    navController: NavHostController,
    floatingActionButton: @Composable () -> Unit = { },
    snackbarContent: @Composable (SnackbarData) -> Unit = {},
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState? = null,
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