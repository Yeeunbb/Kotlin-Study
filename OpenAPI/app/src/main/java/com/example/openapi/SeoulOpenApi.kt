package com.example.openapi

import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "4966415566796b6f343255556b7278"
    }
}

interface SeoulOpenService {
    
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/{limit}")
    fun getLibraries(@Path("api_key") key:String, @Path("limit") limit:Int)
    
}