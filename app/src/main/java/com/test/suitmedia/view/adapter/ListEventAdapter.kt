package com.test.suitmedia.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.suitmedia.databinding.EventItemBinding
import com.test.suitmedia.model.EventModel

class ListEventAdapter(
    private var list: ArrayList<EventModel>,
    private var onItemClickCallback: OnItemClickCallback? = null
): RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {

    fun setOnItemClick(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = list.size

    inner class ListViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventModel) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(event)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(event.image)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgItem)
                tvNameItem.text = event.name
                tvDateItem.text = event.date
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = EventItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: EventModel?)
    }

}