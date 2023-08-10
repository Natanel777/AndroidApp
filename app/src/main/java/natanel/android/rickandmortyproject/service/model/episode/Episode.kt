package natanel.android.rickandmortyproject.service.model.episode



import com.google.gson.annotations.SerializedName



data class Episode(
    val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
){
    override fun toString(): String {
        return "Episode(id=$id, airDate=$airDate, characters=$characters, created=$created, episode=$episode, name=$name, url=$url)"
    }
}

//val episodesList = listOf(
//    Episode("2023-01-01", listOf("Character A", "Character B"), "2023-07-22", "S01E01", 1, "Episode 1", "https://example.com/ep1"),
//    Episode("2023-01-02", listOf("Character B", "Character C"), "2023-07-22", "S01E02", 2, "Episode 2", "https://example.com/ep2"),
//    Episode("2023-01-08", listOf("Character A", "Character C"), "2023-07-22", "S02E01", 3, "Episode 3", "https://example.com/ep3"),
//    Episode("2023-01-15", listOf("Character D", "Character E"), "2023-07-22", "S02E02", 4, "Episode 4", "https://example.com/ep4"),
//    Episode("2023-01-22", listOf("Character F", "Character G"), "2023-07-22", "S03E01", 5, "Episode 5", "https://example.com/ep5"),
//    Episode("2023-01-29", listOf("Character H", "Character I"), "2023-07-22", "S04E01", 6, "Episode 6", "https://example.com/ep6")
//)

