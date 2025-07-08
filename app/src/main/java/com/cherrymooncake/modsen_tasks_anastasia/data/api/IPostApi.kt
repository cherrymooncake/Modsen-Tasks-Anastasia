package com.cherrymooncake.modsen_tasks_anastasia.data.api

import com.cherrymooncake.modsen_tasks_anastasia.data.model.CommentApiModel
import com.cherrymooncake.modsen_tasks_anastasia.data.model.PostApiModel
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface IPostApi {
    @GET("posts")
    suspend fun getPosts(): List<PostApiModel>
    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): List<CommentApiModel>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): IPostApi {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IPostApi::class.java)
        }
    }
}