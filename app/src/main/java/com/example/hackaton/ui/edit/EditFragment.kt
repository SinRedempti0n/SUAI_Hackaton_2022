package com.example.hackaton.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hackaton.databinding.FragmentEditBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    private var _binding: FragmentEditBinding? = null
    val USER_KEY = "Test_v1"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editViewModel =
            ViewModelProvider(this).get(EditViewModel::class.java)

        _binding = FragmentEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editName: EditText = binding.editName
        val editLongitude: EditText = binding.editLongitude
        val editLatitude: EditText = binding.editLatitude
        val editHistory: EditText = binding.editHistory
        val buttonAdd: Button = binding.buttonAdd

        val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY)

        buttonAdd.setOnClickListener {
            class Point(name: String, longitude: Double, latitude: Double, history: String){
            }
            mDatabaseReference.push().setValue(Point(editName.getText().toString(),
                editLongitude.getText().toString().toDouble(),
                editLatitude.getText().toString().toDouble(),
                editHistory.getText().toString()))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}