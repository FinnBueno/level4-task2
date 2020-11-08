package com.peakfinn.task4level2_final.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peakfinn.task4level2_final.R
import com.peakfinn.task4level2_final.data.Match
import kotlinx.android.synthetic.main.item_history.view.*
import java.text.DateFormat.getDateTimeInstance

/**
 * Created by Finn Bon on 27/03/2020.
 */
class HistoryAdapter(
    private val matches: List<Match>
): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        fun bindViewToObject(match: Match) {
            itemView.winnerHeader.text = itemView.resources.getString(match.result.textResource)
            itemView.timestamp.text = getDateTimeInstance().format(match.played_at)
            itemView.computerActionImage.setImageResource(match.computerAction.image)
            itemView.playerActionImage.setImageResource(match.playerAction.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
    )

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindViewToObject(matches[position])
}