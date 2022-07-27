package com.example.restapiidemo.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiidemo.R
import com.example.restapiidemo.home.data.CommentModel
import com.example.restapiidemo.home.data.PostModel
import com.example.restapiidemo.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_post2.*
import java.util.Locale.filter

class PostActivity : AppCompatActivity() {

    private lateinit var vm:HomeViewModel
    private lateinit var adapter2: CommentAdapter
    private lateinit var post:PostModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post2)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Post Details"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        val intent=intent
        val id=intent.getIntExtra("id", 0)
        val title=intent.getStringExtra("title")


        itemPost.text=id.toString()
        itemTitle.text=title
        itemBody.text= ""


        init()
        vm = ViewModelProvider(this)[HomeViewModel::class.java]
        vm.fetchAllComments(id)
        vm.commentModelListLiveData?.observe(this, Observer {
            if (it!=null){
                recycleComment.visibility = View.VISIBLE
                adapter2.setData(it as ArrayList<CommentModel>)
            }else{
                showToast("Something went wrong")
            }
        })
    }

    private fun init() {
        adapter2 = CommentAdapter()
        recycleComment.layoutManager = LinearLayoutManager(this)
        recycleComment.adapter = adapter2
    }

    private fun showToast(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}