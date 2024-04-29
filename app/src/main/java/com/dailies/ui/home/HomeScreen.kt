package com.dailies.ui.home

import com.dailies.NotificationService
import android.Manifest
import android.icu.util.Calendar
import android.os.Build
import android.os.Handler
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dailies.R
import com.dailies.ui.components.NavBarScaffold
import com.dailies.ui.navigation.Screen
import java.text.DateFormat
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.dailies.ui.theme.DailiesTheme


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreenTopLevel(
    navController: NavHostController
){

    HomeScreen(
        navController = navController
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
    navController: NavHostController){


    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        Toast.makeText(context,"isGranted = $isGranted",Toast.LENGTH_SHORT).show()

    }



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


            Button(onClick = {navController.navigate(route = Screen.Notify.route)},
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
                    text = stringResource(id = R.string.Testing)
                )
            }
        }

    }

}

@Composable
fun GetTime(){
    val calendar = Calendar.getInstance().time
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)
    Text(modifier = Modifier,
        fontSize = 40.sp,
            text = time
        )
}



@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        HomeScreenTopLevel(navController)
    }
}
