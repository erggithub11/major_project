package com.dailies.ui.week

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.dailies.R
import com.dailies.ui.components.NavBarScaffold
import com.dailies.ui.navigation.Screen

/**
 * The week screen is accessible from the home screen and is used to contain many other navigation options
 */

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

    rememberCoroutineScope()


    /**
     * The week screen uses the navbar scaffold and a large amount of buttons
     */
    NavBarScaffold(
        navController = navController
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Text(
                text = "Weekly",
                fontSize = 50.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            /**
             * The rows are used for superior formatting and to evenly spread the button out
             */
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Monday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.Monday)
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Tuesday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Tuesday)
                    )
                }
            }

            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Wednesday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Wednesday)
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Thursday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Thursday)
                    )
                }
            }


            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Friday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Friday)
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Saturday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Saturday)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )  {
                Button(
                    onClick = {
                        navController.navigate(route = Screen.Sunday.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Sunday)
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(route = Screen.Daily.route)
                    },
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.All)
                    )
                }
            }
        }
    }
}
