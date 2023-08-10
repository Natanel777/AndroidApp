package natanel.android.rickandmortyproject.service

import natanel.android.rickandmortyproject.service.model.character.CharacterAPI
import natanel.android.rickandmortyproject.service.model.episode.EpisodeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page : Int): EpisodeResponse

    @GET("character/{id}")
    suspend fun getCharacterPerSeason(@Path("id") id: String): List<CharacterAPI>


    companion object {
        fun create(): ApiService =
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}