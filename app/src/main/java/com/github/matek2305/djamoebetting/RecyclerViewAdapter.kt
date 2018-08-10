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
        val bet = matches[position].bet
        val result = matches[position].result

        holder?.matchTeamNamesTextView?.text = "${details.homeTeamName} - ${details.awayTeamName}"
        holder?.matchResultTextView?.text = if (result != null) "(${result.homeTeam}-${result.awayTeam})" else ""
        holder?.matchDateTextView?.text = details.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        holder?.userBetTextView?.text = if (bet != null) "${bet.homeTeam} - ${bet.awayTeam}" else "NO BET"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val matchTeamNamesTextView = view.findViewById<TextView>(R.id.txt_match_teams)
        val matchResultTextView = view.findViewById<TextView>(R.id.txt_match_result)
        val matchDateTextView = view.findViewById<TextView>(R.id.txt_match_date)
        val userBetTextView = view.findViewById<TextView>(R.id.txt_user_bet)
    }

}