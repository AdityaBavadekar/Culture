package com.adityaamolbavadekar.android.apps.culture.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adityaamolbavadekar.android.apps.culture.R
import com.adityaamolbavadekar.android.apps.culture.SettingsActivity
import com.adityaamolbavadekar.android.apps.culture.databinding.FragmentMainBinding
import com.adityaamolbavadekar.android.apps.culture.room.note.CultureColours
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesDataClass
import com.adityaamolbavadekar.android.apps.culture.ui.culture_viewmodel.CultureViewModel

class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var notesList: MutableList<NotesDataClass>
    private val viewModel: CultureViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
                ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        notesList = mutableListOf()
        viewModel.getNotes().observe(viewLifecycleOwner,{listOfNotes ->
            listOfNotes.forEach { note ->
                notesList.add(note)
            }
        })

        if (notesList.isNullOrEmpty()) {
            notesList.add(NotesDataClass(0,"Welcome to Culture","This s the way you can create and view your notes",null,CultureColours.Yellow,"",null,0))
        }
        setUpRecyclerView()

        binding.fabAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_addFragment)
        }

        return binding.root

    }

    private fun setUpRecyclerView() {

        binding.mainRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.mainRecyclerView.adapter = RegularNoteAdapter(notesList,requireActivity(),viewModel)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main,menu)
        menu.findItem(R.id.action_settings).setOnMenuItemClickListener {
            startActivity(Intent(requireActivity(),SettingsActivity::class.java))
            true
        }
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "Search Notes"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                FilterNotes(newText)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun FilterNotes(newText: String?) {

    }

}