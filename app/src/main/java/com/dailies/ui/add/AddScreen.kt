package com.dailies.ui.add

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailies.R
import com.dailies.model.Dailies
import com.dailies.model.DailiesViewModel
import com.dailies.ui.components.ButtonSpinner
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
        navController = navController

    ) { newDailies ->
        dailiesViewModel.insertDailies(newDailies)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavHostController,
    insertDailies: (Dailies) -> Unit = {}

){

    /**
     * These variables below are created to be mutable and are purposed to be inserted into the database.
     */
    var time by rememberSaveable{mutableStateOf("")}
    var hoursVar by rememberSaveable{mutableStateOf(0)}
    var minutesVar by rememberSaveable{mutableStateOf(0)}


    /**
     * The clock dialog allows the clock Ui to be available and it saves te selected value chosen by the clock into the variable previously created.
     */
    val clockState = rememberUseCaseState()
    ClockDialog(state = clockState, selection = ClockSelection.HoursMinutes{ hours, minutes ->
        Log.d("SelectedTime","%$hours:$minutes")
        hoursVar = hours
        minutesVar = minutes
        time = ("$hours:$minutes")
    })

    rememberCoroutineScope()

    val values = stringArrayResource(R.array.day_array)
    val dayValue = values.copyOfRange(0,values.size)


    var day by rememberSaveable { mutableStateOf( dayValue[0] ) }
    var dailiesName by rememberSaveable{mutableStateOf("")}
    var description by rememberSaveable{mutableStateOf("")}


    Scaffold(
        /**
         * floating action button are used to confirm the production of a data.
         */
        floatingActionButton = {
            FloatingActionButton(onClick = {
                insertDailies(
                    name = dailiesName,
                    desc = description,
                    hours = hoursVar,
                    minutes = minutesVar,
                    day = day
                )
                /**
                 * The mutable values was directly inserted into the database
                 */
                { newDailies ->
                    insertDailies(newDailies)
                }

                /**
                 * NavigateUp allows the user to return to the previous screen after the insertion
                 */
                navController.navigateUp()
            },
            ) {
                /**
                 * Icon are chosen from material 3 and it changes the visual of the floating action button.
                 */
                Icon (
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.add_dailies)
                )
            }
        },
        /**
         * The top bar is created to hold the title and to hold an icon button
         */
        topBar ={
            SmallTopAppBar(
                title = {
                    Text(stringResource(R.string.add_dailies))
                },
                /**
                 * icon button ar used to here to allow the user to go back so they're not forced in this screen
                 */
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

            /**
             * These values sets the locations of buttons in the center to set the M3 standard
             */
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

            /**
             * Clockstate.show allows the clock state that was previously set to appear
             */
            Button(onClick = {clockState.show()}) {
                Text("Time picker")
            }

            /**
             * The text field here output the value from the clock state so the user know what they've inserted
             */
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

/**
 * This function inputs a button spinner/ drop down bar and it changes the value of the button spinner
 * To the value that user selected using it
 */
@Composable
fun DayInput(
    values: Array<String>,
    modifier: Modifier,
    updateDay: (String) -> Unit)
{
    ButtonSpinner(
        items = values.asList(),
        modifier = modifier,
        itemClick = {
            updateDay(it)
        }
    )
}


/**
 * This function below is an text-field that updates it value to the one that user inserted.
 */
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

/**
 * Identical to descriptionInput except for a different variable
 */
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

/**
 * The function below inserts the chosen value into the database
 */

fun insertDailies(name: String,
                  desc: String,
                  hours: Int,
                  minutes: Int,
                  day: String,
                  doInsert: (Dailies) -> Unit = {}) {
    /**
     * The if statement checks whether the the name is empty or not
     */
    if (name.isNotEmpty()){
        val dailies = Dailies (
            id = 0,
            name = name,
            description = desc,
            day = DayOfWeek.valueOf(day.uppercase()),
            hour = hours,
            minute = minutes,
            notify = false


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