package com.example.hackaton.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import androidx.lifecycle.ViewModelProvider
import com.example.hackaton.Place
import com.example.hackaton.R
import com.example.hackaton.databinding.FragmentDashboardBinding
import com.example.hackaton.ui.home.CustomRecyclerAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DashboardFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    val USER_KEY = "Test1"
    val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val v: View = binding.root

        val mapFragment = childFragmentManager.findFragmentById(R.id.fallasMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return v
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        // Add a marker in Sydney and move the camera
        val points: ArrayList<LatLng> = ArrayList()
        mDatabase.get().addOnSuccessListener {
            for(data in it.children){
                Log.i("firebase", "Got value ${data.getValue().toString()}")
                var id = "0"
                val cord = LatLng(data.child("latitude").getValue().toString().toDouble(), data.child("longitude").getValue().toString().toDouble())
                points.add(cord)
                mMap.addMarker(MarkerOptions().position(cord).title(
                    data.child("name").getValue().toString())
                )

                var history = data.child("history").getValue().toString()
                val sydney = LatLng(58.422, 32.3836)
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(58.52517593166956, 31.273686997420548), 10f))
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

}


