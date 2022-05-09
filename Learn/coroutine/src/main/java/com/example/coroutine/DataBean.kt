package com.example.coroutine

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*

class DataBean {
    private var desc: String? = null
    private var id = 0
    private var imagePath: String? = null
    private var isVisible = 0
    private var order = 0
    private var title: String? = null
    private var type = 0
    private var url: String? = null
    fun setDesc(desc: String?) {
        this.desc = desc
    }

    fun getDesc(): String? {
        return desc
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setImagePath(imagePath: String?) {
        this.imagePath = imagePath
    }

    fun getImagePath(): String? {
        return imagePath
    }

    fun setIsVisible(isVisible: Int) {
        this.isVisible = isVisible
    }

    fun getIsVisible(): Int {
        return isVisible
    }

    fun setOrder(order: Int) {
        this.order = order
    }

    fun getOrder(): Int {
        return order
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getTitle(): String? {
        return title
    }

    fun setType(type: Int) {
        this.type = type
    }

    fun getType(): Int {
        return type
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getUrl(): String? {
        return url
    }

    companion object {
        //带有参数及返回值的方法
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setSrc(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
        }
    }

}