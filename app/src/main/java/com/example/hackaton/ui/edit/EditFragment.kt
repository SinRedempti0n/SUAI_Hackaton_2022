package com.example.hackaton.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hackaton.Place
import com.example.hackaton.R
import com.example.hackaton.databinding.FragmentEditBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    private var _binding: FragmentEditBinding? = null
    val USER_KEY = "Test1"
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

        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference(USER_KEY)

        val editName: EditText = root.findViewById(R.id.editName)
        val editLongitude: EditText = root.findViewById(R.id.editLongitude)
        val editLatitude: EditText = root.findViewById(R.id.editLatitude)
        val editHistory: EditText = root.findViewById(R.id.editHistory)
        val buttonAdd: Button = root.findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {


            val p: Place = Place(mDatabase.getKey().toString(),
                editName.getText().toString(),
                editLongitude.getText().toString().toDouble(),
                editLatitude.getText().toString().toDouble(),
                editHistory.getText().toString())
            mDatabase.push().setValue(p)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}