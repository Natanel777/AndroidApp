package natanel.android.rickandmortyproject.data.mapper

import natanel.android.rickandmortyproject.data.entity.Season
import natanel.android.rickandmortyproject.service.model.episode.Episode
import natanel.android.rickandmortyproject.service.model.season.SeasonAPI

fun SeasonAPI.toSeasonDB(seasonNumber: Int) = Season(
    seasonNum = seasonNumber,
    episodes = getCharacterEpisode(episodes)
)

//takes all the characters URLs and adds them to a list of strings
fun getCharacterEpisode(listOfEpisodes: List<Episode>):List<String>{
    val list = mutableListOf<String>()
    for (item in listOfEpisodes) {
        for (characters in item.characters) {
            list.add(characters)
        }
    }
    return list
}