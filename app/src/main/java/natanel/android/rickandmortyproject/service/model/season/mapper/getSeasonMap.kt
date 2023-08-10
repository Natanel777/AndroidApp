package natanel.android.rickandmortyproject.service.model.season.mapper

import natanel.android.rickandmortyproject.service.model.episode.Episode
import natanel.android.rickandmortyproject.service.model.episode.EpisodeResponse
import natanel.android.rickandmortyproject.service.model.season.SeasonAPI

//getAllSeasons function will return a list of Season objects,
//where each Season object contains the episodes belonging to that season

fun EpisodeResponse.getAllSeasons(): List<SeasonAPI> {
    val seasons = mutableListOf<SeasonAPI>()
    var addSeason = mutableListOf<Episode>()
    var count = 1
    episodes.forEach {item ->
        // Check if the 'episode' parameter is not null
        if (item.episode != null) {
            // Parse the season number from the 'episode' parameter (Assuming the format is "S01E01")
            val seasonNumber = item.episode.substring(2, 3).toIntOrNull()

            if (seasonNumber != null) {
                if (seasonNumber.toInt() == count) {
                    // Add the episode to the existing season
                    addSeason.add(item)
                } else {
                    // add the episodes for the current season
                    seasons.add(SeasonAPI(count,addSeason))

                    //איפוס
                    count++
                    addSeason = mutableListOf()
                    addSeason.add(item)
                }
            }
        }
    }
    // adding the last season
    if (addSeason.isNotEmpty()) {
        seasons.add(SeasonAPI(count,addSeason))
    }

    // return list of seasons
    return seasons
}