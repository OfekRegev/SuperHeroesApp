package com.ofek.superheroes.herodetailsscreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ofek.superheroes.databinding.HeroExtraDetailsItemLayoutBinding
import com.ofek.superheroes.herodetailsscreen.ui.models.HeroExtraDetailsModel

class HeroExtraDetailsAdapter : RecyclerView.Adapter<ExtraDetailViewHolder>() {

    private var items = emptyList<HeroExtraDetailsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraDetailViewHolder {
        return ExtraDetailViewHolder(
            HeroExtraDetailsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExtraDetailViewHolder, position: Int) {
        holder.setExtraDetailsItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<HeroExtraDetailsModel>) {
        this.items = items
        notifyDataSetChanged()
    }

}

class ExtraDetailViewHolder(
    private val binding: HeroExtraDetailsItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun setExtraDetailsItem(item: HeroExtraDetailsModel) {
        binding.title.text = item.title
        binding.content.text = item.content
    }
}