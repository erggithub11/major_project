package com.dailies.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
data class dailies(
    var id: Int = 0,
    var name: String,
    var description: String,
    var day: String,
    var time: Int = 0
){

}
