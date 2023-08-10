package natanel.android.rickandmortyproject.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import natanel.android.rickandmortyproject.R
import natanel.android.rickandmortyproject.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_information_to_navigation_main3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}