package natanel.android.rickandmortyproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import natanel.android.rickandmortyproject.data.entity.Season

@Dao
interface SeasonDao {

    @Insert
    suspend fun addAll(season: List<Season>)

    @Query("SELECT * FROM season")
    fun getSeasons(): List<Season>?

    @Query("DELETE FROM season")
    fun deleteAll()
}