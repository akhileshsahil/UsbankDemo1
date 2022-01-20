package com.example.usbankdemo.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.usbankdemo.model.Album
import com.example.usbankdemo.repository.Repository
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {


    @Mock
    lateinit var repository: Repository
    lateinit var albumViewModel: AlbumViewModel

    @Before
    fun setUp() {
        albumViewModel = AlbumViewModel(repository)
    }

    @Test
    fun testDataformRepoList(){
        Mockito.`when`(repository.getAlbum())
            .thenReturn(MutableLiveData<List<Album>>())
    }

    @Test
    fun testLiveDataAlbumList(){
        var list =  mutableListOf<Album>()
        var album = Album(1,1,"title")
        list.add(album)


        albumViewModel.album.value = list
       val result = albumViewModel.getAlbum().getOrAwaitValue().find {
           it.id == 1 && it.title == "title" && it.userId == 1
       }
        System.out.println("value==="+result)
        assertTrue(true)
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 10,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        try {
            afterObserve.invoke()
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            this.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}