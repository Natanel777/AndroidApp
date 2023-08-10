package natanel.android.rickandmortyproject.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import natanel.android.rickandmortyproject.data.entity.Character
import natanel.android.rickandmortyproject.databinding.CharacterItemBinding

class CharacterAdapter(private val characters: List<Character>, val onClick:(Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterItem>() {
    class CharacterItem(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItem {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CharacterItemBinding.inflate(inflater, parent, false)
        return CharacterItem(binding)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CharacterItem, position: Int) {
        val character = characters[position]
        with(holder.binding){
            nameText.text = character.name
            speciesText.text = character.species
            Picasso.get().load(character.image).into(characterImage)
            if (character.status == "Alive")
                dot.setTextColor(Color.GREEN)

            else if(character.status == "Dead")
                dot.setTextColor(Color.RED)

            else
                dot.setTextColor(Color.rgb(255, 165, 0)) // orange - UNKNOWN

            root.setOnClickListener {
                onClick(character)
            }
            val name = character.name
            //there are character with the same name so i want define their origin
            if(name == "Rick Sanchez" || name == "Morty Smith"  || name == "Summer Smith" ||
               name == "Beth Smith" || name == "Jerry Smith"){
                Log.d("Name: ", "$name: ")
               Log.d("INFO: ", "$character")
                originText.text = character.origin
            }
            else originText.text = "" //reset the recycleView
        }







//            if (character.status == "Alive"){
//                characterStatus.text = character.status
//                dotStatus.setBackgroundColor(Color.parseColor("#8BC34A"))
//            }
//            else{
//                characterStatus.text = character.status
//                dotStatus.setBackgroundColor(Color.parseColor("#FF0000"))
//            }

    }
}
