package natanel.android.rickandmortyproject.ui.seasons.detail

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import natanel.android.rickandmortyproject.R
import natanel.android.rickandmortyproject.data.entity.Season
import natanel.android.rickandmortyproject.databinding.FragmentSeasonDetailBinding
import natanel.android.rickandmortyproject.ui.adapters.CharacterAdapter


class SeasonDetailFragment : Fragment() {

    private var _binding: FragmentSeasonDetailBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSeasonDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //getting the current season from Parcelable
        val season = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("Season", Season::class.java) ?: return
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("Season") ?: return
        }

        val seasonDetailViewModel =
            ViewModelProvider(this).get(SeasonDetailViewModel::class.java)

        // sending the current season and getting characters
        lifecycleScope.launch {
            seasonDetailViewModel.getCharacters(season)
        }

        //func that clears the textSearch text
        fun clearSearchViewFocus() {
            binding.searchCharacters.clearFocus()
            binding.searchCharacters.setQuery("", false)
        }

        //updating the SearchView
        binding.searchCharacters.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(name: String?): Boolean {
                seasonDetailViewModel.searchCharacter(name)
                binding.searchCharacters.clearFocus()
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                seasonDetailViewModel.searchCharacter(text)
                return false
            }
        })


        //using the characters and show them in
        seasonDetailViewModel.character.observe(viewLifecycleOwner) {
            println(it)
            with(binding.recycleCharacters) {
                adapter = CharacterAdapter(it) { character ->
                    Bundle().apply {
                        putParcelable("character", character)
                        findNavController().navigate(
                            R.id.action_seasonDetailFragment_to_characterDetails, this
                        )
                        clearSearchViewFocus() //Clearing the textSearch text
                    }
                }
                layoutManager = GridLayoutManager(context, 2)
            }
        }

        //Loading view while getting up the character from the api
        seasonDetailViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Show loading UI (e.g., progress bar)
                binding.progressLoading.visibility = View.VISIBLE
            } else {
                // Hide loading UI and show character list
                binding.progressLoading.visibility = View.GONE
            }
        }

        //error text for catching exceptions
        seasonDetailViewModel.error.observe(viewLifecycleOwner) { e ->
            if (e != null) {
                binding.textError.visibility = View.VISIBLE
            } else {
                binding.textError.visibility = View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}