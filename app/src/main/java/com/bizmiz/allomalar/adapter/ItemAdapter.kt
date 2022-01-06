package com.gita.allomalar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.Bizmiz.allomalar.databinding.AllomalarItemBinding
import com.Bizmiz.allomalar.R
import com.gita.allomalar.utils.imageList
import com.gita.allomalar.utils.list

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    var onclick: (position: Int) -> Unit = {}
    fun setOnclickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class MyViewHolder(private val binding: AllomalarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(position: Int) {
            binding.itemImg.setImageResource(imageList[position])
            binding.itemName.text = list[position]
            binding.itemCard.setOnClickListener {
                onclick.invoke(position)
            }
            if (position==0 || position%2==0){
                val fromRight = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_right)
                fromRight.startOffset = 1000
                binding.itemCard.startAnimation(fromRight)
            }
            else{
                val fromLeft = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_left)
                fromLeft.startOffset = 1000
                binding.itemCard.startAnimation(fromLeft)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            AllomalarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.populateModel(position)
    }

    override fun getItemCount(): Int = list.size
}