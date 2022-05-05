package com.example.giphystickersdemo

import BaseResponseModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphystickersdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding
    private val API_KEY = "DPGN7B59yQZ9orqoMJ0CoHyBSii6g3Ls"
    private val LIMIT = "20"
    private val RATING = "g"
    private val TAG = "MainActivity"
    private lateinit var layoutManager : LinearLayoutManager
//    private lateinit var adapter: GifsAdapt/er

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MyRepository())).get(
            MyViewModel::class.java
        )

        viewModel.initData(API_KEY, LIMIT, RATING)

        var responseLiveData = viewModel.getGifLiveData()
        Log.d(TAG, "onCreate: $responseLiveData")
        layoutManager = LinearLayoutManager(this)
        binding.rvGifs.layoutManager = layoutManager
        responseLiveData.observe(this, {

            Log.d(TAG, "onCreate:")

            setAdapter(it)
            addPagination(it)
        })



    }

    private fun addPagination(baseResponseModel: BaseResponseModel) {
        binding.rvGifs.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return layoutManager.findLastCompletelyVisibleItemPosition() == baseResponseModel.pagination.total_count
            }

            override fun isLoading(): Boolean {
                TODO("Not yet implemented")
            }

            override fun loadMoreItems() {
//                viewModel.
            }

        })
    }

    private fun setAdapter(baseResponseModel: BaseResponseModel) {
        binding.rvGifs.adapter = GifsAdapter(this, baseResponseModel)
    }


    abstract class PaginationScrollListener
        (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

        abstract fun isLastPage(): Boolean

        abstract fun isLoading(): Boolean

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }//                    && totalItemCount >= ClothesFragment.itemsCount
            }
        }

        abstract fun loadMoreItems()
    }
}