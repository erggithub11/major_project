package com.dailies.ui.settings

import android.Manifest
import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailies.NotificationService
import com.dailies.ui.components.MainScaffold
import com.dailies.ui.daily.DailyScreenTopLevel
import com.dailies.ui.theme.DailiesTheme


@Composable
fun SettingScreenTopLevel(
    navController: NavHostController
){

    SettingScreen(
        navController = navController
    )
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier,
                navController: NavHostController
){

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
                text = "Testing",
                fontSize = 50.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            val context = LocalContext.current
            val state = rememberLazyGridState()
            val notificationService= NotificationService(context)

            Button(onClick = { notificationService.showBasicNotification() }) {
                Text(text = "Basic notification")
            }

            Button(onClick = { notificationService.showExpandableNotification() }) {
                Text(text = "expandable notification")
            }

            Button(onClick = { notificationService.showDailiesNotification("name","description",3,3) }) {
                Text(text = "dailies notification")
            }

            Button(onClick = {
                Handler().postDelayed({
                    notificationService.showBasicNotification()
                }, 5000)
            }) {
                Text(text = "late notification")
            }


        }
    }
}

@Preview
@Composable
fun SettingScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        SettingScreenTopLevel(navController)
    }
}
