package com.artjure.pageflip

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PageAdapter(
    private val context: Context,
    private val fileNames: List<String>
) : RecyclerView.Adapter<PageAdapter.PageViewHolder>() {

    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.pageImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_page, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val fileName = fileNames[position]
        holder.imageView.setImageBitmap(null)
        holder.imageView.tag = fileName
        val bitmap = decodeAssetBitmap(fileName)
        if (holder.imageView.tag == fileName) {
            holder.imageView.setImageBitmap(bitmap)
        }
    }

    override fun onViewRecycled(holder: PageViewHolder) {
        super.onViewRecycled(holder)
        holder.imageView.setImageBitmap(null)
    }

    override fun getItemCount(): Int = fileNames.size

    private fun decodeAssetBitmap(fileName: String): Bitmap? {
        return try {
            context.assets.open("pages/$fileName").use { input ->
                BitmapFactory.decodeStream(input)
            }
        } catch (e: Exception) {
            null
        }
    }
}
