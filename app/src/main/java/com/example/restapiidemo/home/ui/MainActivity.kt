package com.example.restapiidemo.home.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiidemo.R
import com.example.restapiidemo.home.data.PostModel
import com.example.restapiidemo.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    private lateinit var vm:HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[HomeViewModel::class.java]
        initAdapter()


        vm.fetchAllPosts()

        vm.postModelListLiveData?.observe(this, Observer {
            if (it!=null){
                rv_home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<PostModel>)
            }else{
               showToast("Something went wrong")
           }
            progress_home.visibility = View.GONE
        })


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.home_menu, menu)
//        return true
//    }

    private fun initAdapter() {
        adapter = HomeAdapter()
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
    }


    private fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}

