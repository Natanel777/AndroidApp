package natanel.android.rickandmortyproject.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Season (
    @PrimaryKey
    val seasonNum: Int,
    val episodes: List<String>
):Parcelable