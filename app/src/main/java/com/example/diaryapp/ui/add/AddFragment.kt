package com.example.diaryapp.ui.add

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.diaryapp.BuildConfig
import com.example.diaryapp.MyApplication
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentAddBinding
import com.example.diaryapp.domain.Diary
import com.example.diaryapp.utils.GPSUtils
import com.example.diaryapp.utils.WeatherStackApi
import com.example.diaryapp.utils.WeatherStackItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val diaryRepository by lazy { (requireActivity().application as MyApplication).diaryRepository }
    private lateinit var gpsUtils: GPSUtils
    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gpsUtils = GPSUtils.getInstance()
        geocoder = Geocoder(requireContext(), Locale.getDefault())
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        binding.submitButton.setOnClickListener {
            try {
                // Get current date
                val date = SimpleDateFormat("dd-MM-YYYY").format(Date());

                // Set default values for location & weather data
                var currentLat: String = ""
                var currentLong: String = ""
                var currentCity: String = ""
                var currentTemp: Int? = 99
                var weatherCode: Int = 0

                // Check switch state
                if(binding.locationSwitch.isChecked){
                    // Get current location
                    gpsUtils.findDeviceLocation(requireActivity())
                    val locations = geocoder.getFromLocation(gpsUtils.latitude.toDouble(), gpsUtils.longitude.toDouble(), 1)
                    val currentLocation = locations.get(0)
                    currentLat = currentLocation.latitude.toString()
                    currentLong = currentLocation.longitude.toString()
                    currentCity = currentLocation.subAdminArea.toString()

                    // Get weather information
                    val service = WeatherStackApi.weatherStackService
                    val call = service.getResponse(access_key = "7097696b78b919d0ff95d07e1c433a60", query = currentCity)

                    call.enqueue(object: Callback<WeatherStackItem> {
                        override fun onFailure(call: Call<WeatherStackItem>, t: Throwable) {
                            // API request failed, use diary object without location data
                            Toast.makeText(requireContext(), "Failed retrieving weather information!", Toast.LENGTH_SHORT).show()

                            // Diary object
                            val newPost = Diary(
                                    title = binding.addPostTitleInput.text.toString(),
                                    content = binding.addPostContentInput.text.toString(),
                                    date = date,
                                    coordLat = currentLat,
                                    coordLong = currentLong,
                                    city = currentCity,
                                    temperature = currentTemp,
                                    weather = weatherCode

                            )
                            diaryRepository.add(newPost)
                            requireActivity().supportFragmentManager.popBackStack()
                        }

                        override fun onResponse(
                            call: Call<WeatherStackItem>,
                            response: Response<WeatherStackItem>
                        ) {
                            val newPost = Diary(
                                title = binding.addPostTitleInput.text.toString(),
                                content = binding.addPostContentInput.text.toString(),
                                date = date,
                                coordLat = currentLat,
                                coordLong = currentLong,
                                city = currentCity,
                                temperature = response.body()?.current?.temperature,
                                weather = response.body()?.current?.weatherCode

                            )

                            diaryRepository.add(newPost)
                            requireActivity().supportFragmentManager.popBackStack()
                        }

                    })

                }else{
                    // Location switch state is off
                    val newPost = Diary(
                        title = binding.addPostTitleInput.text.toString(),
                        content = binding.addPostContentInput.text.toString(),
                        date = date,
                        coordLat = currentLat,
                        coordLong = currentLong,
                        city = currentCity,
                        temperature = currentTemp,
                        weather = weatherCode

                    )
                    diaryRepository.add(newPost)
                    requireActivity().supportFragmentManager.popBackStack()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddFragment.
         */
        @JvmStatic
        fun newInstance() = AddFragment()
    }
}