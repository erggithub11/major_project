package com.dailies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmOff
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dailies.R
import com.dailies.model.Dailies
import com.dailies.ui.theme.md_theme_light_onPrimary

/**
 * The card class stores a funciton that reads a list into a class and outputs data on the individual data.
 *
 */
@Composable
fun DailyCard(
    dailies: Dailies,
    thirtyMinAction: (Dailies) -> Unit = {},
    editAction:(Dailies) -> Unit = {},
    deleteAction: (Dailies) -> Unit = {},
    unNotifyAction: (Dailies) -> Unit = {}

){
    Card(
        modifier = Modifier.fillMaxSize()
    ){

        /**
         * The constraint layout allows the data to be formatted in a specific way that matches my ideals
         */
        ConstraintLayout {
            val(nameRef,timeRef
                    ,deleteRef,selectRef,descRef,editRef,unnotifyRef
            ) = createRefs()
            /**
             * Hour and minutes are int values and are needed to be translated into string prior use
             */
            val hourText = dailies.hour.toString()
            val minuteText = dailies.minute.toString()

            Text(
                /**
                 * The name attribute is already in text and so it can be used directly in the text()
                 */
                text = dailies.name,
                fontSize = 30.sp,
                modifier = Modifier

                    .padding()
                    /**
                     * I gave the title a specific background colour to show its a new value in the array
                     */
                    .background(color = md_theme_light_onPrimary )
                    .constrainAs(nameRef) {
                        top.linkTo(parent.top)
                    }

            )

            Text(
                text = ("$hourText : $minuteText"),
                fontSize = 40.sp,
                modifier = Modifier
                    .padding()
                    .constrainAs(timeRef) {
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(descRef.top)
                    }
            )

            Text(
                text = (dailies.description),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding()
                    .constrainAs(descRef) {
                        top.linkTo(timeRef.bottom)
                    }
            )


            /**
             * These icon buttons are linked to an action but these actions are not set up here, rather it's set up
             * when it is used in the screens.
             */
            IconButton(onClick = {
                deleteAction(dailies)},
                modifier = Modifier
                    .constrainAs(deleteRef) {
                        start.linkTo(timeRef.end)
                        end.linkTo(selectRef.start)
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(descRef.top)

                    }
            ) {
                /**
                 * The icons are taken from M3 and it has different colour scheme to make its use more intuitive
                 */
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = stringResource(R.string.remove_daily)
                )

            }

            IconButton(onClick = {
                thirtyMinAction(dailies)},
                modifier = Modifier
                    .constrainAs(selectRef) {
                        start.linkTo(deleteRef.end)
                        end.linkTo(editRef.start)
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(descRef.top)

                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.AlarmOn,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.turn_on_daily)
                )

            }

            IconButton(onClick = {
                editAction(dailies)},
                modifier = Modifier
                    .constrainAs(editRef) {
                        start.linkTo(selectRef.end)
                        end.linkTo(unnotifyRef.end)
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(descRef.top)

                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.edit_daily)
                )

            }

            IconButton(onClick = {
                unNotifyAction(dailies)},
                modifier = Modifier
                    .constrainAs(unnotifyRef) {
                        start.linkTo(editRef.end)
                        end.linkTo(parent.end)
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(descRef.top)

                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.AlarmOff,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.turn_off_daily)
                )

            }
        }

    }
}
