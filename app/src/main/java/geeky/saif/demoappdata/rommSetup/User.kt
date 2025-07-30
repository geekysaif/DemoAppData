package geeky.saif.demoappdata.rommSetup

@Entity(tableName="users")
data class User(
    @PrimaryKey val id:Int,
    val name:String,
    val email:String,
    val phone:String,
    val website:String,
    val address:String,
    val companyName:String
)