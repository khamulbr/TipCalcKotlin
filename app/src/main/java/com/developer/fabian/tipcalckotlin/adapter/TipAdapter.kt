package com.developer.fabian.tipcalckotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.fabian.tipcalckotlin.R
import com.developer.fabian.tipcalckotlin.model.TipRecord
import kotlinx.android.synthetic.main.item_row.view.*

class TipAdapter(val context: Context, val dataset: MutableList<TipRecord>, val listener: IOnItemClickListener)
    : RecyclerView.Adapter<TipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position], listener)

    override fun getItemCount(): Int = dataset.size

    fun add (tipRecord: TipRecord) {
        dataset.add(0, tipRecord)
        notifyDataSetChanged()
    }

    fun clear() {
        dataset.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tipRecord: TipRecord, onItemClickListener: IOnItemClickListener) = with(itemView) {
            txtContent.text = String().format(context.getString(R.string.global_message_tip), tipRecord.getTip())
            txtDateContent.text = tipRecord.getDateFormatted()

            btnView.setOnClickListener({
                onItemClickListener.onItemClick(tipRecord)
            })
        }
    }
}
