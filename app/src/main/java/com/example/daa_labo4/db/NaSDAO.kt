import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.daa_labo4.models.NoteAndSchedule;

@Dao
interface NaSDAO {
    @Insert
    fun insert(nas :NoteAndSchedule): Long

    @Update
    fun update(nas: NoteAndSchedule)

    @Delete
    fun delete(nas: NoteAndSchedule)

    @Query("DELETE FROM NoteAndSchedule")
    fun deleteAll()

    @Query("SELECT * FROM NoteAndSchedule")
    fun getAllNaS() : LiveData<List<NoteAndSchedule>>

    @Query("SELECT COUNT(*) FROM NoteAndSchedule")
    fun getCount() : LiveData<Long>
}