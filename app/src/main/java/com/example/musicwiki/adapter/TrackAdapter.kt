package com.example.musicwiki.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.musicwiki.databinding.TrackListLayoutBinding
import com.example.musicwiki.model.TrackModel

class TrackAdapter(
    private val listener: TrackOnClick,
    private val list: ArrayList<TrackModel.Tracks.Track>,
    private val context: Context
) : BaseAdapter() {

    private lateinit var holder: TrackViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            val binding = TrackListLayoutBinding.inflate(
                mInflater, parent, false
            )
            holder = TrackViewHolder(binding)
            holder.view = binding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as TrackViewHolder
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

    private class TrackViewHolder(trackBinding:TrackListLayoutBinding) {
        var binding: TrackListLayoutBinding = trackBinding
        var view = binding.root
    }

    open class TrackOnClick(val clickListener: (tracks:TrackModel.Tracks.Track) -> Unit) {
        fun onClick(tracks:TrackModel.Tracks.Track) = clickListener(tracks)
    }
}