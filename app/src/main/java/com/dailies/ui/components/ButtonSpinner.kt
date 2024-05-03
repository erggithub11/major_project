package com.dailies.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailies.R
import com.dailies.ui.theme.DailiesTheme

/**
 * The button spinner is a expanded DropDownMenu that reads in data into options for the menu
 */
@Composable
fun ButtonSpinner(
    items: List<String>,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    itemClick: (String) -> Unit = {}
)
{
    var itemText by rememberSaveable { mutableStateOf(if (items.isNotEmpty()) items[0] else "") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        /**
         * The expanded option allows the drop down menu to open up when selected so it doesn't take up too much space
         */
        onClick = { expanded = !expanded }
    ){
        Text(
            text = itemText,
            fontSize = fontSize,
            modifier = Modifier.padding(end = 8.dp)
        )

        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = stringResource(R.string.DropDown)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){

            /**
             * For each allows the menu to to absorb the given array to produce a specific drop down bar
             */
            items.forEach {
                
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        expanded = false

                        itemText = it
                        itemClick(it)
                    }
                )
                    

            }
        }


    }
}


@Composable
@Preview
private fun SpinnerPreview() {
    DailiesTheme(dynamicColor = false) {
        val items = listOf("one", "two", "three")
        ButtonSpinner(items = items)
    }
}