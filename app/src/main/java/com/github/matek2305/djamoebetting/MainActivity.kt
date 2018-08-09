package com.github.matek2305.djamoebetting

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

private val matchesService by lazy {
    MatchesService.create()
}

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

        matchesService
                .getMatches()
                .enqueue(object : Callback<DomainModel.MatchesResponse> {
                    override fun onResponse(call: Call<DomainModel.MatchesResponse>, response: Response<DomainModel.MatchesResponse>) {
                        Log.i(TAG, "Matches loaded, recycler view init ...")
                        initRecyclerView(response.body()!!)
                    }

                    override fun onFailure(call: Call<DomainModel.MatchesResponse>, t: Throwable) {
                        Log.e(TAG, "Matches loading failed: ${t.localizedMessage}")
                    }

                })
    }

    private fun initRecyclerView(response: DomainModel.MatchesResponse) {
        recyclerView.adapter = RecyclerViewAdapter(response.matches, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        Log.i(TAG, "Recycler view loaded, matches should be visible")
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
