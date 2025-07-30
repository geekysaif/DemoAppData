package geeky.saif.demoappdata.apiSetUp

import geeky.saif.demoappdata.rommSetup.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun  getUsers(): List<User>
}