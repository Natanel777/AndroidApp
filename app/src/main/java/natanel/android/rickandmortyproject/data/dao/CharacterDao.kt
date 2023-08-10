package natanel.android.rickandmortyproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import natanel.android.rickandmortyproject.data.entity.Character

@Dao
interface CharacterDao {

    @Insert
    suspend fun add(character: Character)

    @Insert
    suspend fun addAll(characters: List<Character>)

    @Update
    suspend fun update(character: Character)

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): Character?

    @Query("SELECT * FROM characters WHERE season LIKE '%' || :seasonSearch || '%'")
    suspend fun getCharactersBySeason(seasonSearch: String): List<Character>?

    @Query("DELETE FROM characters")
    fun deleteAll()

    @Query("SELECT * FROM characters")
    fun getCharacter(): List<Character>?

}