package natanel.android.rickandmortyproject.service.model.episode

import com.google.gson.annotations.SerializedName


data class EpisodeResponse(
    val info: Info,
    @SerializedName("results")
    val episodes: List<Episode>
)