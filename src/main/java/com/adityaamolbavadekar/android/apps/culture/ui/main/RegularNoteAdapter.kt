package com.adityaamolbavadekar.android.apps.culture.ui.main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.android.apps.culture.databinding.ItemNoteBinding
import com.adityaamolbavadekar.android.apps.culture.databinding.WantToDeleteNoteDialogBinding
import com.adityaamolbavadekar.android.apps.culture.room.note.CultureColours
import com.adityaamolbavadekar.android.apps.culture.room.note.NotesDataClass
import com.adityaamolbavadekar.android.apps.culture.ui.culture_viewmodel.CultureViewModel
import com.google.android.material.snackbar.Snackbar

class RegularNoteAdapter(
    private val notesList: MutableList<NotesDataClass>,
    private val activity: Context,
    private val viewModel: CultureViewModel
) : RecyclerView.Adapter<RegularNoteAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.apply {
            binding.textTitle.text = currentNote.noteTitle
            binding.textBody.text = currentNote.noteBody
            binding.textTimestamp.text = currentNote.createdOn
            binding.parentCard.setCardBackgroundColor(getColor(currentNote.noteColor, binding))
            binding.clickListener.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToEditFragment(currentNote)
                Navigation.findNavController(it).navigate(action)
            }
            binding.clickListener.setOnLongClickListener {
                showDeleteAlertDialog(currentNote, it)
                true
            }
        }
    }

    private fun getColor(color: CultureColours, binding: ItemNoteBinding): Int {
        return when (color) {
            CultureColours.Cycan -> {
                binding.textTimestamp.setTextColor(Color.BLACK)
                binding.textBody.setTextColor(Color.BLACK)
                binding.textTitle.setTextColor(Color.BLACK)
                Color.CYAN
            }
            CultureColours.Blue -> {
                binding.textTimestamp.setTextColor(Color.WHITE)
                binding.textBody.setTextColor(Color.WHITE)
                binding.textTitle.setTextColor(Color.WHITE)
                Color.BLUE
            }
            CultureColours.Yellow -> {
                binding.textTimestamp.setTextColor(Color.BLACK)
                binding.textBody.setTextColor(Color.BLACK)
                binding.textTitle.setTextColor(Color.BLACK)
                Color.YELLOW
            }
            CultureColours.Red -> {
                binding.textTimestamp.setTextColor(Color.BLACK)
                binding.textBody.setTextColor(Color.BLACK)
                binding.textTitle.setTextColor(Color.BLACK)
                Color.RED
            }
        }
    }

    private fun showDeleteAlertDialog(notesDataClass: NotesDataClass, noteView: View) {
        val d = Dialog(activity)
        d.requestWindowFeature(Window.FEATURE_NO_TITLE)
        d.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(d.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        val binding = WantToDeleteNoteDialogBinding()
        d.setContentView(binding.root)
        binding.cancelButton.setOnClickListener {
            d.dismiss()
        }
        binding.deletedButton.setOnClickListener {
            d.dismiss()
            viewModel.deleteNote(notesDataClass.indexId)
            val snack = Snackbar.make(noteView, "Note was deleted", Snackbar.LENGTH_LONG)
            snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snack.show()
        }
        d.show()
        d.window!!.attributes = lp
    }

    override fun getItemCount(): Int = notesList.size

}
