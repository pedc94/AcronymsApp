package com.example.acronymsapp.presentation.feature.acronymmeaninglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymsapp.BR
import com.example.acronymsapp.R
import com.example.acronymsapp.databinding.ItemMeaningBinding
import com.example.acronymsapp.domain.model.AcronymMeaningModel

class AcronymMeaningListAdapter : ListAdapter<AcronymMeaningModel, AcronymMeaningListAdapter.AcronymMeaningViewHolder>(AcronymMeaningDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymMeaningViewHolder {
        val itemBinding =
            DataBindingUtil.inflate<ItemMeaningBinding>(LayoutInflater.from(parent.context), R.layout.item_meaning, parent, false)
        return AcronymMeaningViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AcronymMeaningViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class AcronymMeaningViewHolder(private val itemBinding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(meaning: AcronymMeaningModel) {
            itemBinding.meaning = meaning
            itemBinding.executePendingBindings()
        }
    }

    class AcronymMeaningDiff : DiffUtil.ItemCallback<AcronymMeaningModel>() {
        override fun areItemsTheSame(oldItem: AcronymMeaningModel, newItem: AcronymMeaningModel): Boolean {
            return oldItem.lf == newItem.lf
        }

        override fun areContentsTheSame(oldItem: AcronymMeaningModel, newItem: AcronymMeaningModel): Boolean {
            return oldItem == newItem
        }
    }
}