package natanel.android.rickandmortyproject.service.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterAPI(
    val id: Int,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val location: Location,
    val url: String,
    val created: String
):Parcelable