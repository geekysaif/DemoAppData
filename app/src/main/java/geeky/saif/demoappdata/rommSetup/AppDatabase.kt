package geeky.saif.demoappdata.rommSetup

import geeky.saif.demoappdata.rommSetup.User
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    
    companion object{
        private var INSTANCE: AppDatabase?=null

        fun getInstance(context: Context): AppDatabase{

            if(INSTANCE==null){

                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,"user_db").build()
            }
            return INSTANCE!!
        }
        
    }
    
}