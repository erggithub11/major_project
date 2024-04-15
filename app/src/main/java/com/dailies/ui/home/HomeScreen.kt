package com.dailies.ui.home

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailies.R
import com.dailies.ui.components.NavBarScaffold
import com.dailies.ui.navigation.Screen
import com.dailies.ui.theme.DailiesTheme
import java.text.DateFormat


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


    val coroutineScope = rememberCoroutineScope()


    val context = LocalContext.current

    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else mutableStateOf(true)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

     fun showNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("content title")
            .setContentText("content text")
            .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }



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
//            Button(onClick = {clockState.show()}) {
//                Text("Time picker")
//            }

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

            Button(onClick = {

            },
                modifier = modifier.padding()) {
                Text(
                    text = stringResource(id = R.string.Request_permission)
                )
            }

            Button(onClick = {
                             showNotificaiton()
            },
                modifier = modifier.padding()) {
                Text(
                    text = stringResource(id = R.string.Show_notification)
                )
            }

        }

    }


}



@Composable
fun GetTime(){
    val calendar = Calendar.getInstance().time
    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)
    Text(text = time)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun clock(closeSelection: () -> Unit){


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
