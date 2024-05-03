package com.dailies.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.dailies.model.DailiesSearch

/**
 * This class is used to search certain values when used with the card layouts
 * It is unfinished and would've been expanded but as of right now it only contains a button spinner
 */
@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    dayList:List<String>,
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