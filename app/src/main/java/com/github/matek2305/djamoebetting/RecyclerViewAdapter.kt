package com.github.matek2305.djamoebetting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.time.format.DateTimeFormatter

class RecyclerViewAdapter(private val matches: List<MatchItem>, private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false))
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val details = matches[position].details
        holder?.tvMain?.text = "${details.homeTeamName} - ${details.awayTeamName}"
        holder?.tvBottom?.text = details.startDate
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMain = view.findViewById<TextView>(android.R.id.text1)
        val tvBottom = view.findViewById<TextView>(android.R.id.text2)
    }

}