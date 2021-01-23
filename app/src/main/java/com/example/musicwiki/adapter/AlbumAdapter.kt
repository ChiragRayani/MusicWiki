package com.example.musicwiki.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.musicwiki.databinding.AlbumListLayoutBinding
import com.example.musicwiki.model.AlbumModel

class AlbumAdapter(
    private val listener: AlbumOnClick,
    private val list: ArrayList<AlbumModel.Albums.Album>,
    private val context: Context
) : BaseAdapter() {

    private lateinit var holder: AlbumViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            val binding = AlbumListLayoutBinding.inflate(
                mInflater, parent, false
            )
            holder = AlbumViewHolder(binding)
            holder.view = binding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as AlbumViewHolder
        }

        try {
            val data = list[position]
            Glide.with(context).load(data.image[1].text).into(holder.binding.image)
            holder.binding.name.text = data.name

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return holder.view
    }


    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    private class AlbumViewHolder(albumBinding: AlbumListLayoutBinding) {
        var binding: AlbumListLayoutBinding = albumBinding
        var view = binding.root
    }

    open class AlbumOnClick(val clickListener: (album: AlbumModel.Albums.Album) -> Unit) {
        fun onClick(album: AlbumModel.Albums.Album) = clickListener(album)
    }
}