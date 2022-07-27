package com.example.restapiidemo.home.ui

import android.accounts.AccountManager.get
import android.content.Intent
import android.media.CamcorderProfile.get
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration.get
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.ActionBarPolicy.get
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiidemo.R
import com.example.restapiidemo.home.data.PostModel
import kotlinx.android.synthetic.main.activity_post2.view.*
import kotlinx.android.synthetic.main.home_rv_item_view.view.*
import kotlinx.android.synthetic.main.home_rv_item_view.view.itemBody
import kotlinx.android.synthetic.main.home_rv_item_view.view.itemPost
import kotlinx.android.synthetic.main.home_rv_item_view.view.itemTitle

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private var data : ArrayList<PostModel>?=null



    fun setData(list: ArrayList<PostModel>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_rv_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)

        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, PostActivity::class.java)
            intent.putExtra("id",item?.id)
            intent.putExtra("title",item?.title)
            v.context.startActivity(intent)
        }


    }

    class HomeViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){

        fun bindView(item: PostModel?) {
            itemView.itemPost.text = item?.id.toString()
            itemView.itemTitle.text = item?.title
            itemView.itemBody.text = item?.body

        }
    }



}