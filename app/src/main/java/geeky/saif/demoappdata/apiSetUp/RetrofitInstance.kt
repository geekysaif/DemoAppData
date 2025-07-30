package geeky.saif.demoappdata.apiSetUp

class RetrofitInstance {

    object RetrofitInstance {

        val api: ApiService by lazy {

            Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    }
}