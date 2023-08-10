package natanel.android.rickandmortyproject.service.model.character


data class CharacterResponse(
    val info: Info,
    val characters: List<CharacterAPI>
)