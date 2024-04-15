package com.dailies.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.dailies.model.DailiesSearch

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    dailiesSearch: DailiesSearch,
    dayList:List<String>,
//    nameSearch: String,
    updateSearch: (DailiesSearch) -> Unit = {}
){
    Card(
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
    ) {
        Row{
            ButtonSpinner(
                items = dayList,
                modifier = Modifier,
                itemClick =
            {
                updateSearch(
                    DailiesSearch(
                        day = it,
//                        name = dailiesSearch.name
                    )
                )
            }
            )
        }
    }
}