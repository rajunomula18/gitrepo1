package com.example.gitrepo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepo.R
import com.example.gitrepo.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<Item>()

    fun setListData(data: ArrayList<Item>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.tvTitle
        private val tvDesc = view.tvDesc
        private val imageThumb = view.imageThumb

        fun bind(data: Item) {
            tvTitle.text = data.git_url
            tvDesc.text = data.name

            val url = data.repository.owner.avatar_url
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageThumb);
        }

    }

}