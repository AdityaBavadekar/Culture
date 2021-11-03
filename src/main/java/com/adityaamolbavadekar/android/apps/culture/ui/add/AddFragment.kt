package com.adityaamolbavadekar.android.apps.culture.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.adityaamolbavadekar.android.apps.culture.R
import com.adityaamolbavadekar.android.apps.culture.databinding.FragmentAddBinding
import com.adityaamolbavadekar.android.apps.culture.room.note.CultureColours
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesDataClass
import com.adityaamolbavadekar.android.apps.culture.ui.culture_viewmodel.CultureViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var checkedCultureColours: CultureColours
    private val viewModel: CultureViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        setCheckedColorTo(CultureColours.Blue)

        binding.colourGrey.setOnClickListener {
            setCheckedColorTo(CultureColours.Cycan)
        }
        binding.colourBlue.setOnClickListener {
            setCheckedColorTo(CultureColours.Blue)
        }
        binding.colourRed.setOnClickListener {
            setCheckedColorTo(CultureColours.Red)
        }
        binding.colourOrange.setOnClickListener {
            setCheckedColorTo(CultureColours.Yellow)
        }

        binding.fabSave.setOnClickListener {
            addNewNote()
        }

        return binding.root
    }


    private fun addNewNote() {
        val title = binding.editTextTitle.text.toString()
        val body = binding.editTextBody.text.toString()
        val timestamp = SimpleDateFormat("dd mmmm yyyy", Locale.ENGLISH).format(Date()).toString()
        val tags = binding.editTextTags.text.toString()

        val note = NotesDataClass(0, title, body, tags, checkedCultureColours, timestamp, null, 0)

        viewModel.createNote(note)
        Navigation.findNavController(binding.fabSave).navigateUp()
    }

    private fun setCheckedColorTo(colour: CultureColours) {

        when (colour) {
            CultureColours.Cycan -> {
                checkedCultureColours = CultureColours.Cycan
                binding.colourGrey.setImageResource(R.drawable.ic_baseline_check_circle_24)
                binding.colourBlue.setImageResource(R.drawable.choose_color_blue)
                binding.colourOrange.setImageResource(R.drawable.choose_color_yellow)
                binding.colourRed.setImageResource(R.drawable.choose_color_red)
            }
            CultureColours.Blue -> {
                checkedCultureColours = CultureColours.Blue
                binding.colourGrey.setImageResource(R.drawable.choose_color_cycan)
                binding.colourBlue.setImageResource(R.drawable.ic_baseline_check_circle_24)
                binding.colourOrange.setImageResource(R.drawable.choose_color_yellow)
                binding.colourRed.setImageResource(R.drawable.choose_color_red)
            }
            CultureColours.Yellow -> {
                checkedCultureColours = CultureColours.Yellow
                binding.colourGrey.setImageResource(R.drawable.choose_color_cycan)
                binding.colourBlue.setImageResource(R.drawable.choose_color_blue)
                binding.colourOrange.setImageResource(R.drawable.ic_baseline_check_circle_24)
                binding.colourRed.setImageResource(R.drawable.choose_color_red)
            }
            CultureColours.Red -> {
                checkedCultureColours = CultureColours.Red
                binding.colourGrey.setImageResource(R.drawable.choose_color_cycan)
                binding.colourBlue.setImageResource(R.drawable.choose_color_blue)
                binding.colourOrange.setImageResource(R.drawable.choose_color_yellow)
                binding.colourRed.setImageResource(R.drawable.ic_baseline_check_circle_24)
            }
        }

    }

}