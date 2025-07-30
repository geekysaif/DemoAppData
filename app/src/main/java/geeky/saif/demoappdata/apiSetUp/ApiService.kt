package geeky.saif.demoappdata.apiSetUp

import geeky.saif.demoappdata.rommSetup.User

interface ApiService {
    @GET("users")
    suspend fun  getUsers(): List<User>
}