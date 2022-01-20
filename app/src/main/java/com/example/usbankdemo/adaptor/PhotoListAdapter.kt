package com.example.usbankdemo.adaptor

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usbankdemo.R
import com.example.usbankdemo.databinding.AlbumItemBinding
import com.example.usbankdemo.databinding.PhotoItemBinding
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.model.Photo
import com.example.usbankdemo.ui.photo.PhotoActivity


class PhotoListAdapter(var context: Context) : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    private  var list = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.txtTitle.setText(list.get(position).title)
        holder.binding.txtDescrption.setText(list.get(position).title)
        Glide.with(context).load(list.get(position).thumbnailUrl+".png").into(holder.binding.imageView)
    }

    fun setAdapterList(list: ArrayList<Photo> ){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val bind: PhotoItemBinding) : RecyclerView.ViewHolder(bind.root) {
        var binding: PhotoItemBinding=bind;

    }
}
