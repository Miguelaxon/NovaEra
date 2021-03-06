package com.example.novaera.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novaera.databinding.ItemListBinding
import com.example.novaera.local.ClassNovaEra
import java.text.NumberFormat

class AdapterList: RecyclerView.Adapter<AdapterList.ViewHolderList>() {
    private var listM = listOf<ClassNovaEra>()
    private val selectedM = MutableLiveData<ClassNovaEra>()

    fun selected(): LiveData<ClassNovaEra> = selectedM

    fun update(list: List<ClassNovaEra>){
        listM = list
        notifyDataSetChanged()
    }

    inner class ViewHolderList(private val binding: ItemListBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(classNovaEra: ClassNovaEra){
            binding.tvTitle.text = classNovaEra.name
            binding.tvPrice.text = classNovaEra.price.toString()
            Glide.with(binding.imageView).load(classNovaEra.image).into(binding.imageView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedM.value = listM[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        return ViewHolderList(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        val list = listM[position]
        holder.bind(list)
    }

    override fun getItemCount(): Int = listM.size
}