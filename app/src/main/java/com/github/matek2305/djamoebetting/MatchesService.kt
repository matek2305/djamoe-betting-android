package com.github.matek2305.djamoebetting

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface MatchesService {

    @GET("/matches")
    fun getMatches(): Call<DomainModel.MatchesResponse>

    companion object {
        fun create(): MatchesService {
            val gson = GsonBuilder()
                    .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer<LocalDateTime> { json, _, _ ->
                        LocalDateTime.parse(json.asString, DateTimeFormatter.ISO_DATE_TIME)
                    })
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://5b59a29cf294400014c9b82a.mockapi.io")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(MatchesService::class.java)
        }
    }
}