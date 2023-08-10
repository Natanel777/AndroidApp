package natanel.android.rickandmortyproject.data.covertor

import androidx.room.TypeConverter

class IntCovertor {

    @TypeConverter
    fun listToString(list: List<Int>): String {
        return list.toShortString()
    }

    @TypeConverter
    fun stringToList(string: String): List<Int> {
        return string.split(",").map { it.toIntOrNull() ?: 0 }
    }
}