package com.dailies

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dailies.model.DailiesViewModel
import com.dailies.ui.add.AddScreenTopLevel
import com.dailies.ui.daily.DailyScreenTopLevel
import com.dailies.ui.edit.EditScreenTopLevel
import com.dailies.ui.home.HomeScreenTopLevel
import com.dailies.ui.navigation.Screen
import com.dailies.ui.settings.SettingScreenTopLevel
import com.dailies.ui.theme.DailiesTheme
import com.dailies.ui.week.WeekScreenTopLevel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            val context = LocalContext.current
//            var hasNotificationPermission by remember {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    mutableStateOf(
//                        ContextCompat.checkSelfPermission(
//                            context,
//                            Manifest.permission.POST_NOTIFICATIONS
//                        ) == PackageManager.PERMISSION_GRANTED
//                    )
//                } else mutableStateOf(true)
//            }
//
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.RequestPermission(),
//                onResult = { isGranted ->
//                    hasNotificationPermission = isGranted
//                }
//            )

            DailiesTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    BuildNavigationGraph()
                }
            }
        }
    }

    fun showNotification(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext,"channel_id")
            .setContentText("content text")
            .setContentTitle("content title")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(1,notification)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun BuildNavigationGraph(

    dailiesViewModel: DailiesViewModel = viewModel()

) {
    val navController = rememberNavController()
    val startDestination = remember { Screen.Home.route}

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route){
            HomeScreenTopLevel(navController)
        }

        composable(Screen.Week.route) { WeekScreenTopLevel(navController) }
        composable(Screen.Daily.route) { DailyScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Setting.route) { SettingScreenTopLevel(navController) }
        composable(Screen.Add.route) { AddScreenTopLevel(navController) }
        composable(Screen.Edit.route) { EditScreenTopLevel(navController) }


    }

}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailiesTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}