package com.dailies.ui.week

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


@Composable
fun WeekScreenTopLevel(
    navController: NavHostController
){

    WeekScreen(
        navController = navController
    )
}

@Composable
fun WeekScreen(modifier: Modifier = Modifier,
               navController: NavHostController){

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        Text(text = "Weekly",
            fontSize = 50.sp,
            modifier = Modifier.padding(start = 8.dp))

        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Monday)
            )
        }
        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Tuesday)
            )
        }

        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Wednesday)
            )
        }
        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Thursday)
            )
        }
        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Friday)
            )
        }
        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Saturday)
            )
        }
        Button(onClick = {},
            modifier = modifier.padding()) {
            Text(
                text = stringResource(id = R.string.Sunday)
            )
        }

    }
}
