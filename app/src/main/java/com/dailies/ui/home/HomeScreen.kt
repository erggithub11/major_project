package com.dailies.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.dailies.R
import com.dailies.ui.components.NavBarScaffold
import com.dailies.ui.navigation.Screen


@Composable
fun HomeScreenTopLevel(
    navController: NavHostController
){

    HomeScreen(
        navController = navController
    )
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
    navController: NavHostController){

    val coroutineScope = rememberCoroutineScope()

    NavBarScaffold(navController = navController,
        coroutineScope = coroutineScope) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Image (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 0.dp, top = 0.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                contentScale = ContentScale.Crop
            )

            Button(onClick = {navController.navigate(route = Screen.Daily.route)},
                modifier = modifier.padding()) {
                Text(
                    text = stringResource(id = R.string.Today_button)
                )
            }
            Button(onClick = {navController.navigate(route = Screen.Add.route)},
                modifier = modifier.padding()) {
                Text(
                    text = stringResource(id = R.string.Add_new_daily_button)
                )
            }

            Button(onClick = {navController.navigate(route = Screen.Setting.route)},
                modifier = modifier.padding()) {
                Text(
                    text = stringResource(id = R.string.Settings)
                )
            }

        }

    }

}
