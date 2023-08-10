package natanel.android.rickandmortyproject.ui.character_details

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.databinding.FragmentCharacterDetailsBinding

class CharacterDetails : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //getting the character from Parcelable
        val character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable("character", Character::class.java) ?: return
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("character") ?: return
        }

        //inserting character details value

        with(binding){
            nameText.text = character.name
            if (character.species == "Mythological Creature"){
                speciesText.text = "ML Creature"
            }
            else{
                speciesText.text = character.species
            }
            genderText.text = character.gender
            typeText.text = character.type
            if (character.type == ""){
                typeText.text = "No Type"
            }
            episodecountText.text = character.episode.size.toString()
            originText.text = character.origin
            locationText.text = character.location
            Picasso.get().load(character.image).into(characterImage)

            if (character.status == "Alive"){
                dotStatus.setTextColor(Color.GREEN)
                statusText.text = character.status
            }
            else if(character.status == "Dead"){
                dotStatus.setTextColor(Color.RED)
                statusText.text = character.status
            }
            else{
                dotStatus.setTextColor(Color.rgb(255, 165, 0))
                statusText.text = character.status
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}