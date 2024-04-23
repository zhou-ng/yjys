package com.example.yjys.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yjys.R
import com.example.yjys.viewmodel.PlaceViewModel
import com.example.yjys.WeatherActivity
import com.example.yjys.WeatherMainActivity
import com.example.yjys.adapter.PlaceAdapter
import com.example.yjys.databinding.FragmentPlaceBinding
import com.example.yjys.model.Place


class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private var placeList = mutableListOf<Place>()
    private lateinit var adapter: PlaceAdapter
    private lateinit var binding: FragmentPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPlaceBinding.inflate(layoutInflater,container,false)

        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        if (activity is WeatherMainActivity && viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra(getString(R.string.location_lng), place.location.lng)
                putExtra(getString(R.string.location_lat), place.location.lat)
                putExtra(getString(R.string.place_name), place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        initListener()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer{ result ->
//            val placeList = mutableListOf<Place>()
            val places = result.getOrNull()
            if (places != null) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.bgImageView.visibility = View.GONE
//                viewModel.placeList.clear()
//                viewModel.placeList.addAll(places)
                adapter.setData(places.toMutableList())
            } else {
                Toast.makeText(activity, getString(R.string.no_query_place), Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    private fun initListener() {
        binding.searchPlaceEdit.doAfterTextChanged {
            val content = it.toString().trim()  //去除首尾空格
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.bgImageView.visibility = View.VISIBLE
                placeList.clear()
            }
        }
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this)
        binding.recyclerView.adapter = adapter
    }
}