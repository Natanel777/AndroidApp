package natanel.android.rickandmortyproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import natanel.android.rickandmortyproject.data.covertor.IntCovertor
import natanel.android.rickandmortyproject.data.covertor.StringConverter
import natanel.android.rickandmortyproject.data.dao.CharacterDao
import natanel.android.rickandmortyproject.data.dao.SeasonDao
import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.data.entity.Season

const val DB_VERSION = 1
const val DB_NAME = "AppDatabase"

@TypeConverters(StringConverter::class, IntCovertor::class)
@Database(entities = [Character::class, Season::class], version = DB_VERSION)
abstract class AppDatabase:RoomDatabase() {

    abstract fun characterDao():CharacterDao
    abstract fun seasonDao(): SeasonDao

    companion object{
        fun create(context: Context) =
            Room.databaseBuilder(context,AppDatabase::class.java, DB_NAME)
                .build()
    }
}