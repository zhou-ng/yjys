package com.example.yjys.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yjys.viewmodel.PlaceViewModel
import com.example.yjys.WeatherActivity
import com.example.yjys.WeatherMainActivity
import com.example.yjys.adapter.PlaceAdapter
import com.example.yjys.databinding.FragmentPlaceBinding


class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter: PlaceAdapter

    private lateinit var binding: FragmentPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPlaceBinding.inflate(layoutInflater,container,false)

        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        binding.recyclerView.adapter = adapter

        if (activity is WeatherMainActivity && viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

//        //进入后焦点在搜索栏上并打开系统软键盘
//        binding.searchPlaceEdit.postDelayed({
//            binding.searchPlaceEdit.requestFocus()
//            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(binding.searchPlaceEdit, InputMethodManager.SHOW_IMPLICIT)
//        }, 200)

        binding.searchPlaceEdit.doAfterTextChanged {
            val content = it.toString().trim()  //去除首位空格
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer{ result ->
            val places = result.getOrNull()
            if (places != null) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}