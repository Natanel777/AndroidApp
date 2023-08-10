package natanel.android.rickandmortyproject.service.model.character


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    val name: String,
    val url: String
):Parcelable