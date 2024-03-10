package com.dailies.model
import java.time.LocalDateTime
import java.time.DayOfWeek

data class Dailies(
    var id: Int = 0,
    var name: String,
    var description: String,
    var day: DayOfWeek,
    var time: LocalDateTime = LocalDateTime.now()
){

}
