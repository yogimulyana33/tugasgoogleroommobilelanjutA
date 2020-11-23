package id.ac.roomdb1805551066;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//add database entities
@Database(entities = {MainData.class},version = 1,exportSchema = false)
abstract
class RoomDB extends RoomDatabase {
    //create db instance
    private static RoomDB database;
    //define db name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        //check sondition
        if (database == null){
            //db null = initialize db
            database = Room.databaseBuilder(context.getApplicationContext()
                    ,RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        //return db
        return database;
    }
    //create dao
    public abstract MaindDao maindDao();

}
