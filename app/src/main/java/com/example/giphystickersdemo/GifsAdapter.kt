package com.example.giphystickersdemo

import BaseResponseModel
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.giphystickersdemo.databinding.ListItemGifBinding


class GifsAdapter(val context : Context, private val dataList : BaseResponseModel) : RecyclerView.Adapter<GifsAdapter.GifsViewHolder>() {
    class GifsViewHolder(private val binding: ListItemGifBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var binding: ListItemGifBinding
    private var TAG = "GifsAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        binding = ListItemGifBinding.inflate(LayoutInflater.from(parent.context))
        return GifsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        with(holder){

            binding.ivGif.loadUrl(dataList.data[position].images.original.url)

//            Log.d(TAG, "onBindViewHolder: ${dataList.data[position].url}")
//            Log.d(TAG, "onBindViewHolder: ${dataList.data}")
/*            Glide.with(context).load(dataList.data[position].images.original.url)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (e != null) {
                            Log.e(TAG, "onLoadFailed: ${e.message}", )
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e(TAG, "onResourceReady:", )

                        return false
                    }
                })
                .into(binding.ivGif)*/
        }
    }

    override fun getItemCount(): Int {
        return dataList.data.size
    }
}