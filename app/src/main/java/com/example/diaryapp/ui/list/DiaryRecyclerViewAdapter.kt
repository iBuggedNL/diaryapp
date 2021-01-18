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
        val weatherCode: TextView = view.findViewById(R.id.weatherCode)
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
        holder.dateText.text = diary.date
        holder.locationText.text = diary.city
        val currentWeatherCode = diary.weather
        holder.weatherCode.visibility = View.INVISIBLE

        when(currentWeatherCode) {
            113 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_day_sunny)
            116, 119 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_cloudy)
            143, 248, 260 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_fog)
            185, 281, 284, 293, 296, 299, 302, 305, 308, 311 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_day_rain)
            179, 227, 230 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_snow)
            176, 263, 266 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_day_sprinkle)
            200 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_day_lightning)
            182 -> holder.weatherIcon.setImageResource(R.drawable.ic_wi_day_sleet)
            0 ->  holder.weatherIcon.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int = posts.value?.size ?: 0

}