package id.ac.roomdb1805551066;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MaindDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);
    //delete query
    @Delete
    void delete(MainData mainData);
    //delete all query
    @Delete
    void reset(List<MainData> mainData);
    //update
    @Query("UPDATE table_name SET text = :sText WHERE ID = :sID")
    void update(int sID,String sText);
    //get all data query
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();
}
