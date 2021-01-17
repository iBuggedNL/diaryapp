package com.example.diaryapp.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.R
import com.example.diaryapp.domain.Diary
import com.example.diaryapp.ui.MainActivity


class DiaryRecyclerViewAdapter(
    private val activity: MainActivity,
    private val posts: LiveData<List<Diary>>
) : RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder>() {

    init {
        val dataObserver = Observer<List<Diary>> {
            this.notifyDataSetChanged()
        }

        posts.observe(activity, dataObserver)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        var id: Int? = null

        init {
            view.setOnClickListener {
                val id = id ?: throw IllegalStateException()
                activity.showDetail(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.view_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("", "onBindViewHolder: $position")
        val diary = posts.value?.get(position) ?: throw IllegalStateException()

        holder.id = diary.id
        holder.titleText.text = diary.title
    }

    override fun getItemCount(): Int = posts.value?.size ?: 0

}