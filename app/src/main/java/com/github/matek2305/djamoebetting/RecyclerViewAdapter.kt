package com.github.matek2305.djamoebetting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.time.format.DateTimeFormatter

class RecyclerViewAdapter(private val matches: List<MatchDomain.MatchListItem>, private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val details = matches[position].details
        holder?.MatchTeamNamesTextView?.text = "${details.homeTeamName} - ${details.awayTeamName}"
        holder?.matchDateTextView?.text = details.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val MatchTeamNamesTextView = view.findViewById<TextView>(R.id.txt_match_teams)
        val matchDateTextView = view.findViewById<TextView>(R.id.txt_match_date)
    }

}