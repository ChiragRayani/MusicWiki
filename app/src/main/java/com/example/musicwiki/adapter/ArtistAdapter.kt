package com.example.musicwiki.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.musicwiki.databinding.ArtistListLayoutBinding
import com.example.musicwiki.model.ArtistModel

class ArtistAdapter(
    private val listener: ArtistOnClick,
    private val list: ArrayList<ArtistModel.Topartists.Artist>,
    private val context: Context
) : BaseAdapter() {

    private lateinit var holder: ArtistViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            val binding = ArtistListLayoutBinding.inflate(
                mInflater, parent, false
            )
            holder = ArtistViewHolder(binding)
            holder.view = binding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as ArtistViewHolder
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

    private class ArtistViewHolder(artistBinding:ArtistListLayoutBinding) {
        var binding: ArtistListLayoutBinding = artistBinding
        var view = binding.root
    }

    open class ArtistOnClick(val clickListener: (artist:ArtistModel.Topartists.Artist) -> Unit) {
        fun onClick(artist:ArtistModel.Topartists.Artist) = clickListener(artist)
    }
}