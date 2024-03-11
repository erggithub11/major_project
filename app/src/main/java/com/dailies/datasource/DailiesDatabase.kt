package com.dailies.datasource
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dailies.datasource.util.DayConverter
import com.dailies.datasource.util.LocalTimeConverter
import com.dailies.model.Dailies
import com.dailies.model.DailiesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDateTime

@Database(entities = [Dailies::class],version = 1)
@TypeConverters(DayConverter::class, LocalTimeConverter::class,)
abstract class DailiesDatabase: RoomDatabase() {

    abstract fun dailiesDao(): DailiesDao

    companion object {
        private var instance: DailiesDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)
    }

    @Synchronized
    fun getDatabase(context: Context): DailiesDatabase? {
        if (instance == null) {
            instance =
                Room.databaseBuilder(
                    context.applicationContext,
                    DailiesDatabase::class.java,
                    "dailies_database"
                )
                    .allowMainThreadQueries()
                    .addCallback(roomDatabaseCallback(context))
                    .build()
        }
        return instance
    }

    private fun roomDatabaseCallback(context: Context): RoomDatabase.Callback {
        return object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                coroutineScope.launch {
                    populateDatabase(getDatabase(context)!!)
                }
            }
        }
    }

    private fun populateDatabase(
        instance: DailiesDatabase
    ){
        /**
         *  An Example for testing
         *
         */
        val daily1 = Dailies(
            0,
            "test1",
            "description",
            DayOfWeek.THURSDAY,
            LocalDateTime.now()
        )
        val daily4 = Dailies(
            0,
            "test4",
            "description",
            DayOfWeek.SATURDAY,
            LocalDateTime.now()

        )
        val daily2 = Dailies(
            0,
            "test2",
            "description",
            DayOfWeek.TUESDAY,
            LocalDateTime.now()

        )
        val daily3 = Dailies(
            0,
            "test3",
            "description",
            DayOfWeek.MONDAY,
            LocalDateTime.now()

        )
        /**
         *All the premade exercises will be put within a list and the list will be used in the dao to be put into the database
         */
        val dailiesList = mutableListOf(
            daily1,
            daily2,
            daily3,
            daily4
        )

        val dao = instance.dailiesDao()
        dao.insertMultipleDaily(dailiesList)


    }

}