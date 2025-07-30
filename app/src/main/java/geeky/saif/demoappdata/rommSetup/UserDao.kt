package geeky.saif.demoappdata.rommSetup

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    suspend fun getAll():List<User>
}