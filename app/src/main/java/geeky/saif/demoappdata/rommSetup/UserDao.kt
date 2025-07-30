package geeky.saif.demoappdata.rommSetup


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getAll():List<User>
}