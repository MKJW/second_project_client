package com.mksoft.mkjw_second_project.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.mksoft.mkjw_second_project.App

/**
 * Class that handles image requests using Volley.
 */
object ImageRequester {//싱글톤으로 적용하기 위하여 class 말고 object로 한듯
    private val requestQueue: RequestQueue
    private val imageLoader: ImageLoader
    private val maxByteSize: Int

    init {
        val context = App.applicationContext()
        this.requestQueue = Volley.newRequestQueue(context)
        this.requestQueue.start()
        this.maxByteSize = calculateMaxByteSize(context)
        this.imageLoader = ImageLoader(//이미지 로더(volley에서 지원)에는 request queue, image cache가 들어감
                requestQueue,
                object : ImageLoader.ImageCache {
                    private val lruCache = object : LruCache<String, Bitmap>(maxByteSize) {
                        override fun sizeOf(url: String, bitmap: Bitmap): Int {
                            return bitmap.byteCount
                        }
                    }//여기서 maxSize는 sizeOf 오버라이드에 따라서 정할 수 있다.... lru크기가 될 수도.. bitmap 크기가 될 수 도

                    @Synchronized
                    override fun getBitmap(url: String): Bitmap? {
                        return lruCache.get(url)
                    }

                    @Synchronized
                    override fun putBitmap(url: String, bitmap: Bitmap) {
                        lruCache.put(url, bitmap)
                    }
                })
    }

    /**
     * Sets the image on the given [NetworkImageView] to the image at the given URL
     *
     * @param networkImageView [NetworkImageView] to set image on
     * @param url              URL of the image
     */
    fun setImageFromUrl(networkImageView: NetworkImageView, url: String) {
        networkImageView.setImageUrl(url, imageLoader)
    }

    private fun calculateMaxByteSize(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics//디스플레이에 대한 일반적인 정보를 설명하는 구조
        val screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4
        //스크린 크기 * 4(비트맵의 1픽셀의 크기)
        return screenBytes * 3
    }
}