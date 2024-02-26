package com.dailies.ui.daily

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.dailies.R
import com.dailies.ui.components.MainScaffold


@Composable
fun DailyScreenTopLevel(
    navController: NavHostController
){

    DailyScreen(
        navController = navController
    )
}

@Composable
fun DailyScreen(modifier: Modifier = Modifier,
               navController: NavHostController){

    val coroutineScope = rememberCoroutineScope()

    MainScaffold (
        navController = navController,
        coroutineScope = coroutineScope,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Text(
                text = "placeholder for dailies",
                fontSize = 50.sp,
                modifier = Modifier.padding(start = 8.dp)
            )


        }
    }
}
