package natanel.android.rickandmortyproject.ui.seasons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import natanel.android.rickandmortyproject.data.AppDatabase
import natanel.android.rickandmortyproject.data.entity.Season
import natanel.android.rickandmortyproject.data.repository.CharacterRepository
import natanel.android.rickandmortyproject.data.repository.SeasonRepository
import natanel.android.rickandmortyproject.service.ApiService
import natanel.android.rickandmortyproject.service.model.episode.Episode
import natanel.android.rickandmortyproject.service.model.episode.EpisodeResponse
import natanel.android.rickandmortyproject.service.model.season.mapper.getAllSeasons

class SeasonViewModel(context: Application) : AndroidViewModel(context) {

    //Crating access to repository that access the database
    private val db = AppDatabase.create(context)
    private val repo = SeasonRepository(db.seasonDao()) // season repo

    // for clearing Database
    val isClicked = MutableLiveData(false)
    private val repoCharacter =
        CharacterRepository(db.characterDao()) // character repo (using here only for clearing database)

    //the main properties used by adapter in the fragment
    private val _season = MutableLiveData<List<Season>>()
    val season: LiveData<List<Season>> = _season

    init {
            viewModelScope.launch(Dispatchers.Main) {
                _season.value = repo.getSeasons()

                //if database(SeasonDao) is empty then fill it
                if (_season.value.isNullOrEmpty()) {
                    viewModelScope.launch(Dispatchers.IO) {

                        //getting all the episodes (meantime there are only 3 pages of episodes)
                        val allEpisodePages = getAllEpisodePages()

                        //combine the episodes
                        val combinedEpisodes = combineEpisodeResponses(allEpisodePages)

                        //put all the episodes to the seasons they belong to
                        val seasonResponse = combinedEpisodes.getAllSeasons()

                        repo.updateDao(seasonResponse) //updating the database

                        viewModelScope.launch(Dispatchers.Main) {
                            _season.value = repo.getSeasons() //getting the updated seasons
                        }
                    }
                }
            }
        }


    //the func checks how much pages of EpisodeResponse there are
    //and puts it in a list
    private suspend fun getAllEpisodePages(): List<EpisodeResponse> {
        val combinedResponses = mutableListOf<EpisodeResponse>()
        var currentPage = 1

        while (true) {
            val episodeResponse =
                ApiService.create().getEpisodes(currentPage) //creating every index a NEW PAGE
            if (episodeResponse.info.next != null) { // checks if we have next page (ignore the warning)
                combinedResponses.add(episodeResponse)
                currentPage++
            } else {
                combinedResponses.add(episodeResponse) // adding the last element
                break
            }
        }
        return combinedResponses
    }

    //the func gets a list, combined them and returns a long EpisodeResponse one
    private fun combineEpisodeResponses(responses: List<EpisodeResponse>): EpisodeResponse {
        val combinedEpisodes = mutableListOf<Episode>()

        for (response in responses) {
            combinedEpisodes.addAll(response.episodes)
        }

        return EpisodeResponse(responses[0].info, combinedEpisodes)
    }

    //func that helps us to understand if the button has clicked
    //in order to make it gone while using the app and clearing all database
    fun clearDatabase() {
        isClicked.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete() //clearing season database
            repoCharacter.delete() // clearing character database
        }
    }

}