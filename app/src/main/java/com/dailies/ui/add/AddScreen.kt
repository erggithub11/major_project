package com.dailies.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailies.R
import com.dailies.model.Dailies
import com.dailies.model.DailiesViewModel
import com.dailies.ui.components.EditScaffold
import com.dailies.ui.components.MainScaffold
import com.dailies.ui.daily.DailyScreenTopLevel
import com.dailies.ui.theme.DailiesTheme

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

@Composable
fun AddScreen(modifier: Modifier = Modifier,
                  navController: NavHostController,
              insertDailies: (Dailies) -> Unit = {}

){

    val coroutineScope = rememberCoroutineScope()

    var values = stringArrayResource(R.array.day_array)
    val dayValue = values.copyOfRange(0,values.size)

    var day by rememberSaveable { mutableStateOf( dayValue[0] ) }

    var dailiesName by rememberSaveable{mutableStateOf("")}



    EditScaffold (
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
                text = "placeholder for Adding dailies",
                fontSize = 50.sp,
                modifier = Modifier.padding(start = 8.dp)
            )


        }
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