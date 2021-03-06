package com.example.novaera.ui.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novaera.R
import com.example.novaera.databinding.ItemDetailBinding
import com.example.novaera.local.ClassNovaEraDetail

class AdapterDetail: RecyclerView.Adapter<AdapterDetail.ViewHolderDetail>()  {
    private var listM = listOf<ClassNovaEraDetail>()
    private val selectedM = MutableLiveData<ClassNovaEraDetail>()

    fun selected(): LiveData<ClassNovaEraDetail> = selectedM

    fun update(list: List<ClassNovaEraDetail>){
        listM = list
        notifyDataSetChanged()
    }

    inner class ViewHolderDetail(private val binding: ItemDetailBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(classNovaEraDetail: ClassNovaEraDetail){
            binding.tvName.text = classNovaEraDetail.name
            binding.tvPrice2.text = classNovaEraDetail.price.toString()
            binding.tvLastPrice.text = classNovaEraDetail.lastPrice.toString()
            binding.tvDescription.text = classNovaEraDetail.description
            Glide.with(binding.ivImage).load(classNovaEraDetail.image).into(binding.ivImage)
        }

        override fun onClick(v: View?) {
            selectedM.value = listM[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDetail {
        return ViewHolderDetail(ItemDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolderDetail, position: Int) {
        val list = listM[position]
        holder.bind(list)
    }

    override fun getItemCount(): Int = listM.size
}