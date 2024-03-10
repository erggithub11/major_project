package com.dailies.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.DayOfWeek

@Entity(tableName ="dailies")
data class Dailies(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var description: String,
    var day: DayOfWeek,
    var time: LocalDateTime = LocalDateTime.now()
){

}
