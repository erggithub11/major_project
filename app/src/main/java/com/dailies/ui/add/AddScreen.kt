package com.dailies.ui.add

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Insert
import com.dailies.R
import com.dailies.model.Dailies
import com.dailies.model.DailiesViewModel
import com.dailies.ui.components.ButtonSpinner
import com.dailies.ui.components.EditScaffold
import com.dailies.ui.components.MainScaffold
import com.dailies.ui.daily.DailyScreenTopLevel
import com.dailies.ui.theme.DailiesTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.DayOfWeek

@Composable
fun AddScreenTopLevel(
    navController: NavHostController,
    dailiesViewModel: DailiesViewModel = viewModel()

){
    AddScreen(
        navController = navController,
        insertDailies = { newDailies ->
            dailiesViewModel.insertDailies(newDailies)

        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(modifier: Modifier = Modifier,
                  navController: NavHostController,
              insertDailies: (Dailies) -> Unit = {}

){

    var time by rememberSaveable{mutableStateOf("")}


    val clockState = rememberUseCaseState()
    ClockDialog(state = clockState, selection = ClockSelection.HoursMinutes{ hours, minutes ->
        Log.d("SelectedTime","%$hours:$minutes")
        time = ("$hours:$minutes")
    })

    val coroutineScope = rememberCoroutineScope()

    var values = stringArrayResource(R.array.day_array)
    val dayValue = values.copyOfRange(0,values.size)

    var day by rememberSaveable { mutableStateOf( dayValue[0] ) }
    var dailiesName by rememberSaveable{mutableStateOf("")}
    var description by rememberSaveable{mutableStateOf("")}


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                insertDailies(
                    name = dailiesName,
                    desc = description,
                    //time = time,
                    day = day
                ) { newDailies ->
                    insertDailies(newDailies)
                }
                navController.navigateUp()
            },
            ) {
                Icon (
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.add_dailies)
                )
            }
        },
        topBar ={
            SmallTopAppBar(
                title = {
                    Text(stringResource(R.string.add_dailies))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ){

                        Icon (
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.Back)
                        )
                    }
                }
            )
        }
    ){innerPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            DailyNameInput(
                dailiesName = dailiesName,
                modifier = Modifier
                    .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth(),
                updateName = {
                    dailiesName = it
                }
            )

            DescriptionInput(
                desc = description,
                modifier = Modifier
                    .padding(top = 8.dp),
                updateDesc = {
                    description = it
                }
            )

            Button(onClick = {clockState.show()}) {
                Text("Time picker")
            }

            TextField(value = ("Time: $time"), onValueChange = {
                time = it
            })

            DayInput (
                values = dayValue,
                modifier = Modifier
                    .padding(top = 8.dp),
                updateDay = {
                    day = it
                }
            )
        }

    }

}

@Composable
fun DayInput(values: Array<String>, modifier: Modifier, updateDay: (String) -> Unit)
{
    ButtonSpinner(
        items = values.asList(),
        modifier = modifier,
        itemClick = {
            updateDay(it)
        }
    )
}

@Composable
fun DescriptionInput(
    desc: String, modifier: Modifier, updateDesc: (String) -> Unit)
{
    OutlinedTextField(
        value = desc,
        label = {
            Text(text = "Description")
        },
        onValueChange = { updateDesc(it) },
        modifier = modifier
    )
}

@Composable
fun DailyNameInput(
    dailiesName: String, modifier: Modifier, updateName: (String) -> Unit)
{
OutlinedTextField(value = dailiesName,
    label = {
            Text(text = "Name")
    },
    onValueChange ={ updateName(it) },
    modifier = modifier )
}


//    EditScaffold (
//        navController = navController,
//        coroutineScope = coroutineScope,
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = modifier
//                .padding(10.dp)
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState()),
//        ) {
//
//            Text(
//                text = "placeholder for Adding dailies",
//                fontSize = 50.sp,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//
//
//        }
//    }
// }

fun insertDailies(name: String, desc: String, day: String,
                  doInsert: (Dailies) -> Unit = {}) {
    if (name.isNotEmpty()){
        val dailies = Dailies (
            id = 0,
            name = name,
            description = desc,
            day = DayOfWeek.valueOf(day.uppercase())

        )
        doInsert(dailies)
    }
}

@Preview
@Composable
fun AddScreenPreview(){
    val navController = rememberNavController()
    DailiesTheme{
        AddScreenTopLevel(navController)
    }
}