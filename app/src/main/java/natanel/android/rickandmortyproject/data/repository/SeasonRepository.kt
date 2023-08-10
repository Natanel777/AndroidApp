package natanel.android.rickandmortyproject.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import natanel.android.rickandmortyproject.data.dao.SeasonDao
import natanel.android.rickandmortyproject.data.mapper.toSeasonDB
import natanel.android.rickandmortyproject.service.model.season.SeasonAPI

class SeasonRepository(private val seasonDao: SeasonDao){

    //getting seasons from the existing database
     suspend fun getSeasons() = withContext(Dispatchers.IO) { seasonDao.getSeasons()}

    //database is empty so updating him with a list and index that
    //represent the season number
    suspend fun updateDao(apiSeasons: List<SeasonAPI>){
        withContext(Dispatchers.IO) {
            seasonDao.addAll(apiSeasons.mapIndexed { index, seasonAPI ->
                seasonAPI.toSeasonDB(index + 1) })
        }
    }

    suspend fun delete() = withContext(Dispatchers.IO){ seasonDao.deleteAll()}

}