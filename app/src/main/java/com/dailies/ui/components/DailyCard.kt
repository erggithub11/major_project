package com.dailies.ui.components

import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dailies.model.Dailies
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import com.dailies.R
import java.text.DateFormat


@Composable
fun DailyCard(
    modifier: Modifier = Modifier,
    dailies: Dailies,
    selectAction: (Dailies) -> Unit = {},
    deleteAction: (Dailies) -> Unit = {}
    ){
    Card(
        modifier = Modifier.fillMaxSize()
    ){
        ConstraintLayout {
            val(nameRef,timeRef,deleteRef) = createRefs()

            Text(
                text = dailies.name,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding()
                    .constrainAs(nameRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.bottom)
                        bottom.linkTo(deleteRef.top)
                    }
            )

            //val calendar = dailies.time.toString()
            //val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)
            //val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(dailies.time)

            Text(
                text = dailies.time.toString(),
                //text = time,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding()
                    .constrainAs(timeRef) {
                        start.linkTo(parent.start)
                        top.linkTo(nameRef.bottom)
                        bottom.linkTo(deleteRef.top)
                    }
            )

            IconButton(onClick = {
                deleteAction(dailies)},
                modifier = Modifier
                    .constrainAs(deleteRef) {
                        end.linkTo(parent.end)
                        top.linkTo(timeRef.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = stringResource(R.string.remove_daily)
                )

            }
        }



    }
}