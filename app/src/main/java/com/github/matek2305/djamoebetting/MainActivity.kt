package com.github.matek2305.djamoebetting

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Response
import com.android.volley.request.GsonRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        loadMatches()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun loadMatches() {
        Log.i(MainActivity::class.java.simpleName, "Loading matches ...")
        val matchesRequest = GsonRequest<MatchesResponse>("http://5b59a29cf294400014c9b82a.mockapi.io/matches", MatchesResponse::class.java, emptyMap(),
                Response.Listener<MatchesResponse> {
                    Log.i(MainActivity::class.java.simpleName, "Matches loaded, recycler view init ...")
                    initRecyclerView(it)
                },
                Response.ErrorListener {
                    Log.e(MainActivity::class.java.simpleName, "Matches loading failed: ${it.localizedMessage}")
                }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(matchesRequest)
    }

    private fun initRecyclerView(response: MatchesResponse) {
        recyclerView.adapter = RecyclerViewAdapter(response.matches, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        Log.i(MainActivity::class.java.simpleName, "Recycler view loaded, matches should be visible")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
