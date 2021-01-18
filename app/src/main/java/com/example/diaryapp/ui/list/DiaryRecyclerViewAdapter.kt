package com.example.diaryapp.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val dateText: TextView = view.findViewById(R.id.date_view)
        val locationText: TextView = view.findViewById(R.id.location_view)
        val weatherIcon: ImageView = view.findViewById(R.id.weather_icon)
        var id: Int? = null

        init {
            // Set the click listener to the detailed view of post
            view.setOnClickListener {
                val id = id ?: throw IllegalStateException()
                activity.showDetail(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.view_diary_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = posts.value?.get(position) ?: throw IllegalStateException()

        // Set data values
        holder.id = diary.id
        holder.titleText.text = diary.title
        holder.dateText.text = diary.date
        holder.locationText.text = diary.city

        // Get the weather icon for post when present (not -1)
        val weatherIcon = diary.getWeatherIcon()
        if(weatherIcon != -1) {
            holder.weatherIcon.setImageResource(diary.getWeatherIcon())
        }else{
            holder.weatherIcon.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int = posts.value?.size ?: 0

}