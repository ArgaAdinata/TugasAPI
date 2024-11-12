package com.example.tugasapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasapi.databinding.ItemCharBinding
import com.example.tugasapi.model.Chars
import com.squareup.picasso.Picasso


typealias OnClickChar = (Chars) -> Unit

class CharAdapter(private val listChar: List<Chars>,
                  private val onClick: OnClickChar) :
    RecyclerView.Adapter<CharAdapter.itemCharsViewHolder>() {

    inner class itemCharsViewHolder(private val binding: ItemCharBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Chars) {
            with(binding) {
                txtName.text = data.artistName
                Picasso.Builder(itemView.context).build().load(data.url).fit().centerCrop().into(imgChar)

                itemView.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemCharsViewHolder {
        val binding = ItemCharBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return itemCharsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listChar.size
    }

    override fun onBindViewHolder(holder: itemCharsViewHolder, position: Int) {
        holder.bind(listChar[position])
    }
}