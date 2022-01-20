package com.example.usbankdemo.ui.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usbankdemo.R
import com.example.usbankdemo.adaptor.AlbumListAdapter
import com.example.usbankdemo.adaptor.PhotoListAdapter
import com.example.usbankdemo.databinding.ActivityMainBinding
import com.example.usbankdemo.databinding.ActivityPhotoBinding
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.model.Photo
import com.example.usbankdemo.ui.album.AlbumFragment
import com.example.usbankdemo.viewmodels.AlbumViewModel
import com.example.usbankdemo.viewmodels.PhotoViewModel
import com.example.usbankdemo.viewmodels.ViewModelFactory
import online.mshoap.application.MyApplication
import javax.inject.Inject

class PhotoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhotoBinding
    lateinit var adapter: PhotoListAdapter
    var list = ArrayList<Photo>()
    private lateinit var viewModel: PhotoViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Photos")

        initializeRecyclerView()
        MyApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PhotoViewModel::class.java)
        viewModel.getAllPhoto().observe(this, Observer {
            Log.i("Cart observed", "$it")
            list.clear()
            list.addAll(it)
            adapter?.notifyDataSetChanged()
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    fun initializeRecyclerView(){

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = PhotoListAdapter(this)
        this.adapter.setAdapterList(list)
        binding.recyclerView?.layoutManager = manager
        binding.recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()

    }
}