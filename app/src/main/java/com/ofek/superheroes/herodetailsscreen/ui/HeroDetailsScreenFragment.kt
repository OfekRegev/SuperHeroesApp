package com.ofek.superheroes.herodetailsscreen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofek.superheroes.databinding.FragmentHeroDetailsScreenLayoutBinding
import com.ofek.superheroes.herodetailsscreen.ui.models.DetailsScreenSuperheroModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailsScreenFragment : Fragment() {

    private lateinit var viewModel: HeroDetailsScreenViewModel
    private lateinit var binding: FragmentHeroDetailsScreenLayoutBinding

    companion object {
        private const val hero_key = "hero"

        fun newInstance(hero: DetailsScreenSuperheroModel): HeroDetailsScreenFragment {
            return HeroDetailsScreenFragment()
                .apply {
                    arguments = Bundle().apply {
                        putParcelable(hero_key, hero)
                    }
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeroDetailsScreenViewModel::class.java)
        arguments?.getParcelable<DetailsScreenSuperheroModel>(hero_key)?.let {
            viewModel.setHero(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsScreenLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSuperHeroLiveData().observe(viewLifecycleOwner) {
            it?.let {
                binding.nameTextView.text = "name: ${it.name}"
                binding.biographyTextView.text = it.biography
                if (it.image.orEmpty().isNotBlank()) {
                    viewModel.picasso.load(it.image).into(binding.heroImage)
                }
            }
        }
        binding.extraDetailsRecycler.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val adapter = HeroExtraDetailsAdapter()
        viewModel.getHeroExtraDetailsItemsLiveData().observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        binding.extraDetailsRecycler.adapter = adapter
    }
}