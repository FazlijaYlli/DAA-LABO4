package com.example.daa_labo4.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.daa_labo4.DbApp
import com.example.daa_labo4.NotesViewModel
import com.example.daa_labo4.NotesViewModelFactory
import com.example.daa_labo4.R

class FragmentControles : Fragment() {
    lateinit var button_generate: Button
    lateinit var button_delete: Button
    lateinit var button_counter: TextView

    private val noteViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as DbApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_generate = view.findViewById(R.id.button_generate)
        button_delete = view.findViewById(R.id.button_delete)
        button_counter = view.findViewById(R.id.text_count)

        button_generate.setOnClickListener{
            noteViewModel.generateANote()
        }

        button_delete.setOnClickListener{
            noteViewModel.deleteAllNote()
        }

        button_counter.text = noteViewModel.countNotes.value.toString()

        noteViewModel.countNotes.observe(viewLifecycleOwner){
            button_counter.text = noteViewModel.countNotes.value.toString()
        }
    }
}