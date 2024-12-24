package com.example.gson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter(
    private val onImageClickListener: OnImageClickListener
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private val photoUrls = mutableListOf<String>()

    interface OnImageClickListener {
        fun onClickImage(url: String)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rview_item, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = photoUrls.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoUrl = photoUrls[position]
        Glide.with(holder.imageView)
            .load(photoUrl)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            onImageClickListener.onClickImage(photoUrl)
        }
    }

    fun setItems(items: List<String>) {
        this.photoUrls.clear()
        this.photoUrls.addAll(items)
    }
}