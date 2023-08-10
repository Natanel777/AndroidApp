package natanel.android.rickandmortyproject.service.model.episode


import com.google.gson.annotations.SerializedName

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)