package natanel.android.rickandmortyproject.data.covertor

import androidx.room.TypeConverter

fun <T> List<T>.toShortString() = joinToString(",") { "$it" }

class StringConverter {
    @TypeConverter
    fun listToString(genres: List<String>): String {
        return genres.toShortString()
    }

    @TypeConverter
    fun stringToList(string: String): List<String> {
        return string.split(",")
    }

}