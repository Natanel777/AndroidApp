package natanel.android.rickandmortyproject.ui.seasons.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import natanel.android.rickandmortyproject.data.AppDatabase
import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.data.entity.Season
import natanel.android.rickandmortyproject.data.repository.CharacterRepository
import natanel.android.rickandmortyproject.service.ApiService
import java.lang.StringBuilder
import java.net.SocketTimeoutException
import kotlin.random.Random


class SeasonDetailViewModel(context: Application) : AndroidViewModel(context) {

    private val allCharacter = MutableLiveData<List<Character>>() // for searchView

    private val _characters = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>> = _characters

    //loading properties:
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    //database properties:
    private val db = AppDatabase.create(context)
    private val repo = CharacterRepository(db.characterDao())


    //val regex = Regex("\\d+$") EXPLAIN:

    // (Regex) - help to find expressions based on specific patterns
    // (\\d) - it means search for a number(decimal)
    // (+) - one or more digits
    // ($) - at the end of the string

    private val stringBuilder: StringBuilder =
        StringBuilder() //saving all the ID's of the character

    //getting all the ID's of the character
    private fun getCharacterIds(season: Season): String {
        val episodes = season.episodes
            for (character in episodes) {
                val regex = Regex("\\d+$") //explanation above
                val lastNumbers = regex.find(character)?.value
                stringBuilder.append(lastNumbers)
                stringBuilder.append(",")
            }
            // remove the last added coma:
            stringBuilder.setLength(stringBuilder.length - 1)
        return stringBuilder.toString()
    }

    //getting all the Characters from the current season
    suspend fun getCharacters(season: Season) {

        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val seasonNum = season.seasonNum.toString()
                val seasonCharacter = repo.getCharactersBySeason(seasonNum)
                if (seasonCharacter.isNullOrEmpty()) { // if season character empty:
                    //create and update if we have similar characters:
                    val characterIds = getCharacterIds(season)
                    val characterApiResponse =
                        ApiService.create().getCharacterPerSeason(characterIds)

                    //the func that creates/updates
                    repo.addOrUpdateCharacters(characterApiResponse, season.seasonNum)
                }
                //getting existed character or updated character:
                viewModelScope.launch(Dispatchers.Main) {
                    _characters.value = repo.getCharactersBySeason(seasonNum)?.shuffled(Random(42))
                    allCharacter.value = _characters.value
                }

            } catch (e: SocketTimeoutException) {
                viewModelScope.launch(Dispatchers.Main) {
                    _error.value = "Please check you internet connection and try again"
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _error.value = "there is a problem try again later"
                }
            } finally {
                viewModelScope.launch(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    //searching character
    fun searchCharacter(name: String?) {
        name?.let {
            _characters.value = allCharacter.value?.filter {
                it.name.contains(
                    name,
                    ignoreCase = true
                )
            } ?: return
        }
    }
}


