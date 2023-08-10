package natanel.android.rickandmortyproject.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import natanel.android.rickandmortyproject.data.dao.CharacterDao
import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.data.mapper.toCharacterDB
import natanel.android.rickandmortyproject.service.model.character.CharacterAPI

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun getCharactersBySeason(seasonSearch: String) =
        withContext(Dispatchers.IO) { characterDao.getCharactersBySeason(seasonSearch) }


    //updating seasonNum if the character exist
    //if not exist adds him
    suspend fun addOrUpdateCharacters(apiCharacters: List<CharacterAPI>, seasonNum: Int) {
        val existingCharacters = characterDao.getCharacter()
        withContext(Dispatchers.IO) {
            apiCharacters.map { apiCharacter ->
                val existingCharacter =
                    existingCharacters?.find { it.id == apiCharacter.id } // checks if it already exist

                if (existingCharacter == null) {// if not found then add it
                    val character = apiCharacter.toCharacterDB(listOf(seasonNum))
                    add(character)
                } else {//if exist update the seasonNum(add another season num to the list)
                    val updatedSeasons = existingCharacter.season.toMutableList().apply {
                        add(seasonNum)
                    }
                    val updatedCharacter = existingCharacter.copy(season = updatedSeasons)
                    update(updatedCharacter)
                }
            }
        }
    }


    private suspend fun add(character: Character) =
        withContext(Dispatchers.IO) { characterDao.add(character) }

    private suspend fun update(character: Character) =
        withContext(Dispatchers.IO) { characterDao.update(character) }

    suspend fun delete() = withContext(Dispatchers.IO) { characterDao.deleteAll() }
}

