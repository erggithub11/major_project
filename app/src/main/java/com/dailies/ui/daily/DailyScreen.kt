package com.dailies.ui.daily

import com.dailies.NotificationService
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailies.R
import com.dailies.model.Dailies
import com.dailies.model.DailiesSearch
import com.dailies.model.DailiesViewModel
import com.dailies.ui.components.AddScaffold
import com.dailies.ui.components.DailyCard
import com.dailies.ui.components.MainScaffold
import com.dailies.ui.components.SearchComponent
import com.dailies.ui.navigation.Screen
import com.dailies.ui.theme.DailiesTheme


@Composable
fun DailyScreenTopLevel(
    navController: NavHostController,
    dailiesViewModel: DailiesViewModel = viewModel()
){

    val dailyList by dailiesViewModel.dailyList.observeAsState(listOf())

    DailyScreen(
        navController = navController,
        dailiesList = dailyList,
        updateSearchCriteria = { dailiesSearch ->
            dailiesViewModel.updateDailiesSearch(dailiesSearch)
        },
        removeDailies = {newDailies ->
            dailiesViewModel.removeDailies(newDailies)
        },
    ) { newDailies ->
        dailiesViewModel.updateDaily(newDailies)
    }
}


/**
 * This screen contains the card layout,search components and many more. It is used as the mainframe for the
 * Database visual and its variant are used else where.
 */
@Composable
fun DailyScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dailiesList: List<Dailies> = listOf(),
    updateSearchCriteria: (DailiesSearch) -> Unit = {},
    removeDailies: (Dailies) -> Unit = {},
    dailiesUpdate: (Dailies) -> Unit = {},
    ){

    rememberCoroutineScope()

    AddScaffold (
        navController = navController
    )


    {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            /**
             * Day list value are needed to be produced here so it can be used within the search component
             */
            /**
             * Day list value are needed to be produced here so it can be used within the search component
             */
            val dayList = stringArrayResource(id = R.array.day_array).toList()
            val context = LocalContext.current
            val state = rememberLazyGridState()
            val notificationService= NotificationService(context)

            SearchComponent(dayList = dayList){
                updateSearchCriteria(it)
            }

            LazyVerticalGrid(state = state,
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, bottom = 4.dp)
                )
            {
                items(dailiesList){
                    DailyCard(
                        /**
                         * The dailies data allows the data to be outputted into strings
                         */
                        /**
                         * The dailies data allows the data to be outputted into strings
                         */
                        dailies = it,
                        /**
                         * The functions below are activated where the buttons are pressed
                         */
                        /**
                         * The functions below are activated where the buttons are pressed
                         */
                        thirtyMinAction = { dailies ->
                            /**
                             * Toast are pop up window that comes up when activated
                             */
                            /**
                             * Toast are pop up window that comes up when activated
                             */
                            Toast.makeText(context,"Notification for ${dailies.name} will activate in 30 minute",Toast.LENGTH_LONG ).show()
                            dailies.notify = true
                            /**
                             * Notify value is changed to true prior update
                             * Notify value will not be changed to true outside of this program if it didn't get updated via dao
                             */
                            /**
                             * Notify value is changed to true prior update
                             * Notify value will not be changed to true outside of this program if it didn't get updated via dao
                             */
                            dailiesUpdate(dailies)
                            /**
                             * notification function requires specific value from the dailies
                             */
                            /**
                             * notification function requires specific value from the dailies
                             */
                            notificationService.show30MinDailiesNotification(dailies.name,dailies.description,dailies.hour,dailies.minute)

                        },
                        editAction = {dailies ->
                            Toast.makeText(context,"Edit ${dailies.name}",Toast.LENGTH_LONG ).show()
                            /**
                             * The variable below are saved into the dailies viewmodel so it can be reused in the edit screen
                             */
                            /**
                             * The variable below are saved into the dailies viewmodel so it can be reused in the edit screen
                             */
                            DailiesViewModel.currentDaily = dailies
                            navController.navigate(route = Screen.Edit.route)
                        },
                        deleteAction = {dailies ->
                            /**
                             * Dailies are removed via the use of dao
                             */
                            /**
                             * Dailies are removed via the use of dao
                             */
                            removeDailies(dailies)
                            Toast.makeText(context,"Delete ${dailies.name}",Toast.LENGTH_LONG ).show()
                        }
                        ,
                        unNotifyAction = {dailies ->
                            Toast.makeText(context,"${dailies.name} has been turned off",Toast.LENGTH_LONG ).show()
                            dailies.notify = false
                            dailiesUpdate(dailies)
                        }
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun DailyScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        DailyScreenTopLevel(navController)
    }
}
