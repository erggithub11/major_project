package com.dailies

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dailies.model.DailiesViewModel
import com.dailies.ui.add.AddScreenTopLevel
import com.dailies.ui.daily.*
import com.dailies.ui.edit.EditScreenTopLevel
import com.dailies.ui.home.HomeScreenTopLevel
import com.dailies.ui.navigation.Screen
import com.dailies.ui.settings.SettingScreenTopLevel
import com.dailies.ui.theme.DailiesTheme
import com.dailies.ui.week.WeekScreenTopLevel


/**
 * Main activity is where the program starts
 */
class MainActivity : ComponentActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             * DAILIES theme is used here so that the entire program follows the colour scheme
             */
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

/**
 * The navigation graph is important to the rest of the program as it builds a connection to different screens
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun BuildNavigationGraph(

    dailiesViewModel: DailiesViewModel = viewModel()

) {
    val navController = rememberNavController()

    /**
     * start destination is used so the program know which screen to output first when opening the app
     */
    val startDestination = remember { Screen.Home.route}

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        /**
         * all the screen that are used have to be listed in the navGraph builder below
         */
        composable(Screen.Home.route){
            HomeScreenTopLevel(navController)
        }
        composable(Screen.Week.route) { WeekScreenTopLevel(navController) }
        composable(Screen.Daily.route) { DailyScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Monday.route) { MondayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Tuesday.route) { TuesdayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Wednesday.route) { WednesdayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Thursday.route) { ThursdayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Friday.route) { MondayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Saturday.route) { SaturdayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Sunday.route) { SundayScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Notify.route) { NotifyScreenTopLevel(navController,dailiesViewModel) }
        composable(Screen.Setting.route) { SettingScreenTopLevel(navController) }
        composable(Screen.Add.route) { AddScreenTopLevel(navController) }
        composable(Screen.Edit.route) { EditScreenTopLevel(navController) } }

}


@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailiesTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}