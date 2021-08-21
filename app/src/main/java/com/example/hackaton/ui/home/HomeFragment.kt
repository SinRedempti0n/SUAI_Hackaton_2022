package com.example.hackaton.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hackaton.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackaton.Place
import com.google.firebase.database.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: ArrayAdapter<Place>
    val listData: ArrayList<Place> = ArrayList<Place>()


    val USER_KEY = "Test1"
    val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(context)
        readFromDB(recyclerView)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillList(): List<Place> {
        val data = mutableListOf<Place>()
        for(i in 1..5){
            data.add(Place("id", "Name", 10.0 , 20.0, "Blank"))
        }
        return data
    }

    private fun readFromDB(recyclerView: RecyclerView): ArrayList<Place> {
        var list: ArrayList<Place> = ArrayList<Place>()
        mDatabase.get().addOnSuccessListener {
            for(data in it.children){
                Log.i("firebase", "Got value ${data.getValue().toString()}")
                var id = "0"
                var name = data.child("name").getValue().toString()
                var lon = data.child("longitude").getValue().toString().toDouble()
                var lat = data.child("latitude").getValue().toString().toDouble()
                var history = data.child("history").getValue().toString()
                listData.add(Place(id, name, lon, lat, history))
                list.add(Place(id, name, lon, lat, history))
            }
            recyclerView.adapter = CustomRecyclerAdapter(list)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        return list
    }

}

