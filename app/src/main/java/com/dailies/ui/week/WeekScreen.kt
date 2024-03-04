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

    NavBarScaffold(navController = navController,
        coroutineScope = coroutineScope) {

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

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.Monday)
                    )
                }
                Button(
                    onClick = {},
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
                    onClick = {},
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Wednesday)
                    )
                }
                Button(
                    onClick = {},
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
                    onClick = {},
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Friday)
                    )
                }
                Button(
                    onClick = {},
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
                    onClick = {},
                    modifier = modifier.padding()
                        .size(width = 140.dp,height= 60.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.Sunday)
                    )
                }

                Button(
                    onClick = {},
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
