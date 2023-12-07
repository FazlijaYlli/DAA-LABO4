package com.example.daa_labo4

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.example.daa_labo4.db.Repository
import com.example.daa_labo4.models.NoteAndSchedule
import com.example.daa_labo4.models.State
import com.example.daa_labo4.models.Type
import java.text.SimpleDateFormat
import java.util.Calendar

class CustomRecyclerViewAdapter(var noteList: List<NoteAndSchedule> = emptyList()) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val desc: TextView
        val icon: ImageView
        val status: ImageView
        val time: TextView
        val scheduleGroup: Group

        init {
            // Define click listener for the ViewHolder's View
            title = view.findViewById(R.id.noteTitle)
            desc = view.findViewById(R.id.noteDescription)
            icon = view.findViewById(R.id.noteType)
            status = view.findViewById(R.id.noteStatus)
            time = view.findViewById(R.id.noteTime)
            scheduleGroup = view.findViewById(R.id.noteSchedule)
        }

        fun bind(note : NoteAndSchedule) {
            title.text = note.note.title
            desc.text = note.note.text
            icon.setImageResource(when(note.note.type) {
                Type.NONE -> R.drawable.note
                Type.FAMILY -> R.drawable.family
                Type.SHOPPING -> R.drawable.shopping
                Type.TODO -> R.drawable.todo
                Type.WORK -> R.drawable.work
            })

            val color = when(note.note.state) {
                State.DONE -> Color.GREEN
                State.IN_PROGRESS -> Color.GRAY
            }
            icon.setColorFilter(color)

            if (note.schedule != null) {
                scheduleGroup.visibility = View.VISIBLE
                val color = if(note.schedule.date > Calendar.getInstance()) Color.RED else Color.GRAY
                status.setColorFilter(color)
                time.text = SimpleDateFormat.getInstance().format(note.schedule.date.time)
            }  else {
                scheduleGroup.visibility = View.GONE
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        // TODO: Use 2 different layouts
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(noteList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return noteList.size;
    }

    fun sortNoteListByCreationDate() {
        noteList = noteList.sortedByDescending {
            it.note.creationDate
        }
        for (elem in noteList){
            println(elem.note)
        }
    }

    fun sortNoteListByNearestDueDate() {
        noteList = noteList.sortedByDescending {
            it.schedule?.date
        }
        for (elem in noteList){
            println(elem.schedule)
        }
    }

}
