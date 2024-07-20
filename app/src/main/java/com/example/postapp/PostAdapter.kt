package com.example.postapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (var post: List<Post>):
        RecyclerView.Adapter<PostAppViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAppViewHolder {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item,parent,false)
        return PostAppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostAppViewHolder, position: Int) {
        val postView=post[position]
        holder.body.text=postView.body
        holder.title.text=postView.title
        holder.id.text="ID: ${postView.id}"
        holder.userId.text="UserID: ${postView.userId}"
    }

    override fun getItemCount(): Int {
        return post.size
    }
}

    class PostAppViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var body=itemView.findViewById<TextView>(R.id.tvBody)
        var title=itemView.findViewById<TextView>(R.id.tvTitle)
        var userId = itemView.findViewById<TextView>(R.id.tvUserId)
        var id= itemView.findViewById<TextView>(R.id.tvId)
}