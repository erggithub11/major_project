package com.dailies.datasource
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dailies.datasource.util.DayConverter
import com.dailies.datasource.util.LocalDateTimeConverter
import com.dailies.model.Dailies
import com.dailies.model.DailiesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek


@Database(entities = [Dailies::class],version = 1)
@TypeConverters(DayConverter::class, LocalDateTimeConverter::class)
abstract class DailiesDatabase: RoomDatabase() {

    abstract fun dailiesDao(): DailiesDao

    companion object {

        private var instance: DailiesDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)


        /**
         * Getting the database value but it contains an if statement which produces a preset if no database is to be found.
         */
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

        /**
         *
         */
        private fun roomDatabaseCallback(context: Context): Callback {
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
             *  Preset data produced for testing.
             *
             */
            val daily1 = Dailies(
                0,
                "ThursdayTest",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tempor orci vel ",
                DayOfWeek.THURSDAY,
                20,
                20,
                false
            )
            val daily4 = Dailies(
                0,
                "Saturday Test",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tempor orci vel ",
                DayOfWeek.SATURDAY,
                19,
                20,
                false

            )
            val daily2 = Dailies(
                0,
                "Tuesday Test",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tempor orci vel ",
                DayOfWeek.TUESDAY,
                12,20,
                false

            )
            val daily3 = Dailies(
                0,
                "Long description Test",
                " fringilla rutrum sem. Sed convallis volutpat purus ac scelerisque. Nam in blandit odio. ",
                DayOfWeek.MONDAY,
                11,11,
                false

            )

            val daily5 = Dailies(
                0,
                "Notify on Daily Wednesday",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.  Proin nunc ipsum, suscipit sed rhoncus finibus, sagittis interdum lorem. Vestibulum nec purus ipsum. Pellentesque dapibus varius felis, at convallis diam. Nulla lacinia sapien ac iaculis porta. Donec commodo consectetur sem non interdum. Vivamus sollicitudin lorem id ex sodales consectetur. Phasellus mauris nisl, pellentesque at neque quis, fringilla rutrum sem. Sed convallis volutpat purus ac scelerisque. Nam in blandit odio. ",
                DayOfWeek.WEDNESDAY,
                9,30,
                true

            )
            /**
             *Preset dailies where put into a mutable list
             */
            val dailiesList = mutableListOf(
                daily1,
                daily2,
                daily3,
                daily4,
                daily5
            )

            /**
             * The mutableList previously produced is inserted into the database via dao.
             */

            val dao = instance.dailiesDao()
            dao.insertMultipleDaily(dailiesList)


        }
    }


}