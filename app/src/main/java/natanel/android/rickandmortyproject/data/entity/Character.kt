package natanel.android.rickandmortyproject.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "characters")
@Parcelize
data class Character(
    @PrimaryKey
    val id: Int,
        val season:List<Int>,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val gender: String,
    val location: String,
    val image: String,
    val episode: List<String>,
    val isClicked: Boolean = false
):Parcelable
