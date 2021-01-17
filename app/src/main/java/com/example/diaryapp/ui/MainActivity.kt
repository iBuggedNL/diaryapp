package com.example.diaryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.diaryapp.R
import com.example.diaryapp.databinding.ActivityMainBinding
import com.example.diaryapp.ui.add.AddFragment
import com.example.diaryapp.ui.detail.DetailFragment
import com.example.diaryapp.ui.list.ListFragment
import com.example.diaryapp.utils.GPSUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gpsUtils: GPSUtils;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        gpsUtils = GPSUtils.getInstance()
        gpsUtils.initPermissions(this)
        gpsUtils.findDeviceLocation(this)

        val listFragment = ListFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.activeFragment, listFragment)
            .commit()
    }

    fun showDetail(movieId: Int) {

        val detailFragment = DetailFragment.newInstance(movieId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activeFragment, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    fun showAdd() {
        val addFragment = AddFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activeFragment, addFragment)
            .addToBackStack(null)
            .commit()
    }
}