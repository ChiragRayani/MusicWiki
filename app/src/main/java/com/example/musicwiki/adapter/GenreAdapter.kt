package com.example.musicwiki.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.musicwiki.databinding.GenreAdapterLayoutBinding
import com.example.musicwiki.model.NetworkGenreModel

class GenreAdapter(
    private val listener: GenreOnClick,
    private val list: ArrayList<NetworkGenreModel.Tags.Tag>,
    private val context: Context
) : BaseAdapter() {
    private lateinit var holder: GenreViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            val binding = GenreAdapterLayoutBinding.inflate(
                mInflater, parent, false
            )
            holder = GenreViewHolder(binding)
            holder.view = binding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as GenreViewHolder
        }

        try {
            val data = list[position]
            holder.binding.genre.text = data.name

            holder.binding.genre.setOnClickListener {
                listener.onClick(data)
            }

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

    private class GenreViewHolder(genreBinding: GenreAdapterLayoutBinding) {
        var binding: GenreAdapterLayoutBinding = genreBinding
        var view = binding.root
    }

    open class GenreOnClick(val clickListener: (tag: NetworkGenreModel.Tags.Tag) -> Unit) {
        fun onClick(tag: NetworkGenreModel.Tags.Tag) = clickListener(tag)
    }
}