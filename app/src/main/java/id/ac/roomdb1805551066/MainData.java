package id.ac.roomdb1805551066;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Define table name
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    //create id collum
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //create collum
    @ColumnInfo(name = "text")
    private String text;

    //generate getter & setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
