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
import com.dailies.ui.components.DailyCard
import com.dailies.ui.components.MainScaffold
import com.dailies.ui.navigation.Screen
import com.dailies.ui.theme.DailiesTheme


@Composable
fun WednesdayScreenTopLevel(
    navController: NavHostController,
    dailiesViewModel: DailiesViewModel = viewModel()
){

    val dailyList by dailiesViewModel.tuesdayList.observeAsState(listOf())

    WednesdayScreen(
        navController = navController,
        dailiesList = dailyList,
        dailiesSearch = dailiesViewModel.dailiesSearch,
        updateSearchCriteria = { dailiesSearch ->
            dailiesViewModel.updateDailiesSearch(dailiesSearch)
        },
        removeDailies = {newDailies ->
            dailiesViewModel.removeDailies(newDailies)
        },
        dailiesUpdate = {newDailies ->
            dailiesViewModel.updateDaily(newDailies)
        },
    )
}

@Composable
fun WednesdayScreen(modifier: Modifier = Modifier,
                  navController: NavHostController,
                  dailiesList: List<Dailies> =  listOf(),
                  dailiesSearch: DailiesSearch = DailiesSearch(),
                  updateSearchCriteria: (DailiesSearch) -> Unit = {},
                  removeDailies: (Dailies) -> Unit = {},
                  dailiesUpdate: (Dailies) -> Unit = {},

                  ){

    val coroutineScope = rememberCoroutineScope()

    MainScaffold (
        navController = navController,
        coroutineScope = coroutineScope
    )


    {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val dayList = stringArrayResource(id = R.array.day_array).toList()
            val context = LocalContext.current
            val state = rememberLazyGridState()
            val notificationService= NotificationService(context)
            LazyVerticalGrid(state = state,
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, bottom = 4.dp)
            )
            {
                items(dailiesList){
                    DailyCard(
                        dailies = it,
                        thirtyMinAction = { dailies ->
                            Toast.makeText(context,"Notification for ${dailies.name} will activate in 30 minute",Toast.LENGTH_LONG ).show()
                            dailies.notify = true
                            dailiesUpdate(dailies)
                            notificationService.show30MinDailiesNotification(dailies.name,dailies.description,dailies.hour,dailies.minute)

                        },
                        editAction = {dailies ->
                            Toast.makeText(context,"Edit ${dailies.name}",Toast.LENGTH_LONG ).show()
                            DailiesViewModel.currentDaily = dailies
                            Log.d("editScreen", DailiesViewModel.currentDaily.name)
                            navController.navigate(route = Screen.Edit.route)
                        },
                        deleteAction = {dailies ->
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
fun WednesdayScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        WednesdayScreenTopLevel(navController)
    }
}
