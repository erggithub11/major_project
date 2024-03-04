package com.dailies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dailies.ui.add.AddScreenTopLevel
import com.dailies.ui.daily.DailyScreenTopLevel
import com.dailies.ui.edit.EditScreenTopLevel
import com.dailies.ui.home.HomeScreenTopLevel
import com.dailies.ui.navigation.Screen
import com.dailies.ui.settings.SettingScreenTopLevel
import com.dailies.ui.theme.DailiesTheme
import com.dailies.ui.week.WeekScreenTopLevel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
}

@Composable
private fun BuildNavigationGraph(

) {
    val navController = rememberNavController()
    var startDestination = remember { Screen.Home.route}

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route){
            HomeScreenTopLevel(navController)
        }
        composable(Screen.Week.route) { WeekScreenTopLevel(navController) }
        composable(Screen.Daily.route) { DailyScreenTopLevel(navController) }
        composable(Screen.Setting.route) { SettingScreenTopLevel(navController) }
        composable(Screen.Add.route) { AddScreenTopLevel(navController) }
        composable(Screen.Edit.route) { EditScreenTopLevel(navController) }





    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailiesTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}