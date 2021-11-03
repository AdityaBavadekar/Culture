package com.adityaamolbavadekar.android.apps.culture.ui.edit

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.adityaamolbavadekar.android.apps.culture.R
import com.adityaamolbavadekar.android.apps.culture.databinding.FragmentEditBinding
import com.adityaamolbavadekar.android.apps.culture.room.note.CultureColours
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesDataClass
import com.adityaamolbavadekar.android.apps.culture.ui.culture_viewmodel.CultureViewModel
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class EditFragment : Fragment() {
    private val note by navArgs<EditFragmentArgs>()
    private lateinit var binding: FragmentEditBinding
    private lateinit var checkedCultureColours: CultureColours
    private val viewModel: CultureViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        binding.editTextTitle.setText(note.note.noteTitle)
        binding.editTextBody.setText(note.note.noteBody)
        binding.editTextTags.setText(note.note.noteTags)
        setCheckedColorTo(note.note.noteColor)
        setHasOptionsMenu(true)
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
            updateCurrentNote(note.note.indexId)
        }

        try {
            binding.editTextTags.addTextChangedListener {
                val text = it.toString()
                if (text.contains(",")){
                    val split = text.split(",")
                    split.forEach {
                        val chip = Chip(requireActivity())
                            chip.text = it
                        chip.chipCornerRadius = 30F
                        binding.chipGroup.addView(chip)
                    }
                }
            }
        } catch (e: Exception) {
        }

        return binding.root
    }


    private fun updateCurrentNote(indexId: Long) {
        val title = binding.editTextTitle.text.toString()
        val body = binding.editTextBody.text.toString()
        val timestamp = SimpleDateFormat("dd mmmm yyyy", Locale.ENGLISH).format(Date()).toString()
        val tags = binding.editTextTags.text.toString()

        val note =
            NotesDataClass(indexId, title, body, tags, checkedCultureColours, timestamp, null, 0)

        viewModel.updateTheNote(note)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}