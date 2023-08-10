package natanel.android.rickandmortyproject.service.model.season

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import natanel.android.rickandmortyproject.service.model.episode.Episode

@Parcelize
data class SeasonAPI(
     val seasonNum: Int,
     val episodes: List<@RawValue Episode>
):Parcelable