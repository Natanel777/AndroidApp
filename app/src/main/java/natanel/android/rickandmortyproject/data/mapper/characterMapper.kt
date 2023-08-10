package natanel.android.rickandmortyproject.data.mapper


import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.service.model.character.CharacterAPI

fun CharacterAPI.toCharacterDB(seasonNumbs:List<Int>) = Character(
    id = id,
        season = seasonNumbs,
    name = name,
    origin = origin.name,
    species = species,
    status = status,
    type = type,
    gender = gender,
    episode = episode,
    image = image,
    location = location.name,
    isClicked = false,


)