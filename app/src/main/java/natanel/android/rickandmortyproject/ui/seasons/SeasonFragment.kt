package natanel.android.rickandmortyproject.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import natanel.android.rickandmortyproject.R
import natanel.android.rickandmortyproject.databinding.FragmentSeasonBinding
import natanel.android.rickandmortyproject.ui.adapters.SeasonAdapter

class SeasonFragment : Fragment() {

    private var _binding: FragmentSeasonBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSeasonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val seasonViewModel =
            ViewModelProvider(this)[SeasonViewModel::class.java]

        //getting all the character from seasons
        seasonViewModel.season.observe(viewLifecycleOwner){
            println(it)
            with(binding.recyclerSeasons){
                adapter = SeasonAdapter(it){season ->
                    Bundle().apply {
                        putParcelable("Season", season)
                        findNavController().navigate(
                            R.id.action_navigation_main_to_seasonDetailFragment,this
                        )
                    }

                }
                layoutManager = LinearLayoutManager(context)
            }
        }

        //checks if button NOT clicked in the main fragment
        //if NOT then make it visible
        seasonViewModel.isClicked.observe(viewLifecycleOwner) {
            if (!it) {
                binding.btnUpdate.visibility = View.VISIBLE
            }
        }

        //if button clicked then make it gone
        //and remove database
        with(binding.btnUpdate) {
            this.setOnClickListener {
                this.visibility = View.GONE
                seasonViewModel.isClicked.value = true // disappearing the button
                Toast.makeText(context, "You Updated The App!", Toast.LENGTH_SHORT).show()
                seasonViewModel.clearDatabase()
            }
        }
        //error text for catching exceptions
        seasonViewModel.error.observe(viewLifecycleOwner) { e ->
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