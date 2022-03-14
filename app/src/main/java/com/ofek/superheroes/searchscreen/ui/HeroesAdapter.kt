package com.ofek.superheroes.searchscreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ofek.superheroes.R
import com.ofek.superheroes.databinding.HeroListViewItemLayoutBinding
import com.ofek.superheroes.databinding.TitleViewItemLayoutBinding
import com.ofek.superheroes.searchscreen.ui.models.SearchScreenSuperHeroModel
import com.squareup.picasso.Picasso

class HeroesAdapter(
    private val picasso: Picasso,
    private val onHeroClicked: (SearchScreenSuperHeroModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var suggestedItems = emptyList<SearchScreenSuperHeroModel>()
    private var searchItems = emptyList<SearchScreenSuperHeroModel>()


    companion object {
        const val hero_view_type = 0
        const val title_view_type = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || position == getSuggestedLastIndex() + 1) {
            title_view_type
        } else {
            hero_view_type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            hero_view_type -> {
                HeroViewHolder(
                    picasso,
                    HeroListViewItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                )
            }
            title_view_type -> {
                TitleViewHolder(
                    TitleViewItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("illegal view holder type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeroViewHolder -> {
                if (isInSuggestionRange(position)) {
                    // -1 because the first item would be the title item, so essentially the suggested items first index would be 1
                    val hero = suggestedItems[position - 1]
                    holder.setHeroData(hero) {
                        onHeroClicked.invoke(hero)
                    }
                } else {
                    // -1 because the first item would be the title item, so essentially the suggested items first index would be 1
                    if (isInSearchRange(position)) {
                        // +1 to include the title item
                        val suggestedSize = suggestedItems.size + 1
                        val hero = searchItems[position - suggestedSize - 1]
                        holder.setHeroData(hero) {
                            onHeroClicked.invoke(hero)
                        }
                    }
                }
            }
            is TitleViewHolder -> {
                if (isInSuggestionRange(position)) {
                    holder.setTitle(holder.itemView.resources.getString(R.string.suggested_title))
                } else {
                    if (isInSearchRange(position)) {
                        holder.setTitle(holder.itemView.resources.getString(R.string.searched_title))
                    }
                }
            }
        }
    }

    private fun isInSearchRange(position: Int): Boolean {
        // +1 to include the suggested title item
        val suggestedLastIndex = getSuggestedLastIndex()
        val searchLastIndex = itemCount - 1
        return position in (suggestedLastIndex + 1)..searchLastIndex
    }

    private fun isInSuggestionRange(position: Int): Boolean {
        return position in 0..getSuggestedLastIndex()
    }

    override fun getItemCount(): Int {
        // +1 for the extra title item
        val suggestedSize = suggestedItems.size + 1
        val searchSize = searchItems.size + 1
        return suggestedSize + searchSize
    }

    fun setSearchItems(searchItems: List<SearchScreenSuperHeroModel>) {
        this.searchItems = searchItems
        notifyDataSetChanged()
    }

    fun setSuggestedItems(suggestedItems: List<SearchScreenSuperHeroModel>) {
        val currentSuggestedItemsSize = suggestedItems.size
        this.suggestedItems = suggestedItems
        notifyItemRangeChanged(0, currentSuggestedItemsSize + 1)
    }

    private fun getSuggestedLastIndex(): Int {
        // +1 to include the suggested title item
        return suggestedItems.lastIndex + 1
    }

}

class HeroViewHolder(
    private val picasso: Picasso,
    private val binding: HeroListViewItemLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    var onHeroClicked: () -> Unit = {}

    init {
        binding.root.setOnClickListener {
            onHeroClicked.invoke()
        }
    }

    fun setHeroData(heroModel: SearchScreenSuperHeroModel, onHeroClicked: () -> Unit) {
        if (heroModel.imageUrl.isNullOrBlank()) {
            // set placeholder image...
        } else {
            picasso.load(heroModel.imageUrl).into(binding.heroThumbnail)
        }
        binding.heroNameTextView.text = heroModel.name
        this.onHeroClicked = onHeroClicked
    }
}

class TitleViewHolder(
    private val binding: TitleViewItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun setTitle(title: String) {
        binding.titleText.text = title
    }
}