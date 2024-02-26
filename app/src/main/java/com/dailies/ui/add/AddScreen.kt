package com.dailies.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AddScreenTopLevel(
    navController: NavHostController
){

    AddScreen(
        navController = navController
    )
}

@Composable
fun AddScreen(modifier: Modifier = Modifier,
                  navController: NavHostController
){

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        Text(text = "placeholder for Adding dailies",
            fontSize = 50.sp,
            modifier = Modifier.padding(start = 8.dp))


    }
}
