package com.example.postapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.rvDetails)
        recyclerView.layoutManager=LinearLayoutManager(this)

        fetchPosts()

    }


    fun fetchPosts() {
        val apiInterface = ApiClient.buildApiInterface(PostApiInterface::class.java)
        val request = apiInterface.fetchPosts()
        request.enqueue(object: retrofit2.Callback<List<Post>>{
            override fun onResponse(p0: Call<List<Post>>, p1: retrofit2.Response<List<Post>>) {
              if (p1.isSuccessful){
                  val post = p1.body()
                  post?.let {
                      postAdapter=PostAdapter(it)
                      recyclerView.adapter=postAdapter
                  }
                  Toast.makeText(baseContext,"fetch ${post!!.size}post",Toast.LENGTH_LONG)
                      .show()
              }else{
                  Toast.makeText(baseContext,p1.errorBody()?.string(),Toast.LENGTH_LONG).show()
              }
            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
             Toast.makeText(baseContext,p1.message,Toast.LENGTH_SHORT)
            }

        })
    }
}