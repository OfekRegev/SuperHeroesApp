package com.ofek.superheroes.searchscreen.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofek.superheroes.NavigationActivity
import com.ofek.superheroes.databinding.FragmentSearchScreenLayoutBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchScreenFragment : Fragment() {

    lateinit var layoutBinding: FragmentSearchScreenLayoutBinding
    lateinit var viewModel: SearchScreenViewModel

    companion object {
        fun newInstance(): SearchScreenFragment {
            return SearchScreenFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutBinding = FragmentSearchScreenLayoutBinding.inflate(inflater, container, false)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        layoutBinding.searchView.addTextChangedListener {
            val query = it?.toString()
            viewModel.onQueryChanged(query.orEmpty())
        }
        val adapter = HeroesAdapter(
            viewModel.picasso,
        ) {
            activity?.let {
                if (it is NavigationActivity) {
                    // navigate to hero details
                }
            }
        }
        layoutBinding.heroesRecyclerView.adapter = adapter
        layoutBinding.heroesRecyclerView.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.VERTICAL, false
        )
        viewModel.getSearchItems().observe(viewLifecycleOwner, {
            adapter.setSearchItems(it)
        })
        viewModel.getSuggestedItems().observe(viewLifecycleOwner) {
            adapter.setSuggestedItems(it)
        }

    }


}