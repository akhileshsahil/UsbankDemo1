package com.example.usbankdemo.ui.album

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usbankdemo.adaptor.AlbumListAdapter
import com.example.usbankdemo.databinding.FragmentAlbumBinding
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.viewmodels.AlbumViewModel
import com.example.usbankdemo.viewmodels.ViewModelFactory
import online.mshoap.application.MyApplication
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding
     lateinit var adapter: AlbumListAdapter
    var list = ArrayList<Album>()
    private lateinit var viewModel: AlbumViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance() = AlbumFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlbumBinding.inflate(inflater,container,false);
        initializeRecyclerView()
        MyApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)
        viewModel.getAlbum().observe(viewLifecycleOwner, Observer {
            Log.i("Cart observed", "$it")
            list.clear()
            list.addAll(it)
            adapter?.notifyDataSetChanged()
        })
        return binding.root
    }

    fun initializeRecyclerView(){

        val manager = LinearLayoutManager(this@AlbumFragment.requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = AlbumListAdapter(this@AlbumFragment.requireContext())
        this.adapter.setAdapterList(list)
        binding.recyclerView?.layoutManager = manager
        binding.recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()

    }


}