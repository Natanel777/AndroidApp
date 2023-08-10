package natanel.android.rickandmortyproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import natanel.android.rickandmortyproject.R
import natanel.android.rickandmortyproject.data.entity.Season
import natanel.android.rickandmortyproject.databinding.SeasonItemBinding

class SeasonAdapter(private val seasons: List<Season>, val onClick:(Season) -> Unit) :
    RecyclerView.Adapter<SeasonAdapter.SeasonItem>() {
    class SeasonItem(val binding: SeasonItemBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonItem {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val binding = SeasonItemBinding.inflate(inflater, parent, false)
            return SeasonItem(binding)
        }

        override fun getItemCount() = seasons.size


        override fun onBindViewHolder(holder: SeasonItem, position: Int) {
            val season = seasons[position]

            val seasonImageResource = when (position) {
            0 -> R.drawable.season1
            1 -> R.drawable.season2
            2 -> R.drawable.season3
            3 -> R.drawable.season4
            4 -> R.drawable.season5
            else -> R.drawable.season6
        }
            holder.binding.seasonImagebtn.setImageResource(seasonImageResource)
            holder.binding.root.setOnClickListener {
                onClick(season)
            }
        }
    }


