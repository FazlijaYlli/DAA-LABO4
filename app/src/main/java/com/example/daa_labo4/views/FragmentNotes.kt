package com.example.daa_labo4.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daa_labo4.CustomRecyclerViewAdapter
import com.example.daa_labo4.DbApp
import com.example.daa_labo4.NotesViewModel
import com.example.daa_labo4.NotesViewModelFactory
import com.example.daa_labo4.R

class FragmentNotes : Fragment() {
    lateinit var customAdapter: CustomRecyclerViewAdapter

    private val noteViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as DbApp).repository)
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customAdapter = CustomRecyclerViewAdapter()

        val recyclerView: RecyclerView = view.findViewById(R.id.notes_recyclerview)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        noteViewModel.allNotes.observe(viewLifecycleOwner) {
            customAdapter.noteList = noteViewModel.allNotes.value!!
            customAdapter.notifyDataSetChanged()
        }
    }
}