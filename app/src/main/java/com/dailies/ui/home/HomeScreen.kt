package com.dailies.ui.home

import android.icu.util.Calendar
import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.dailies.R
import com.dailies.ui.components.NavBarScaffold
import com.dailies.ui.navigation.Screen
import java.text.DateFormat
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dailies.ui.edit.EditScreenTopLevel
import com.dailies.ui.theme.DailiesTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime


@Composable
fun HomeScreenTopLevel(
    navController: NavHostController
){

    HomeScreen(
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
    navController: NavHostController){


    val clockState = rememberUseCaseState()
    ClockDialog(state = clockState, selection = ClockSelection.HoursMinutes{ hours,minutes ->
        Log.d("SelectedTime","%$hours:$minutes")
    })


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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp, top = 0.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                contentScale = ContentScale.Crop
            )

            GetTime()
            Button(onClick = {clockState.show()}) {
                Text("Time picker")
            }

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

@Composable
fun GetTime(){
    val calendar = Calendar.getInstance().time
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)
    Text(text = "$time")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun clock(closeSelection: () -> Unit){


}


@Preview
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        HomeScreenTopLevel(navController)
    }
}
