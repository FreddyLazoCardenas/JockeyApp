package com.example.data.repositorty.restApi.util
import com.example.data.exception.GenericExceptionModel
import com.example.data.exception.NonException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class YoyResponse<T>(responseNetwork : Response<T>) {

    var response: T? = null
    var exception: Exception = NonException()

    init {
        when (responseNetwork.code()) {
            HttpsURLConnection.HTTP_OK,
            HttpsURLConnection.HTTP_CREATED,
            HttpsURLConnection.HTTP_ACCEPTED -> response = responseNetwork.body()
            else -> exception = GenericExceptionModel()
        }
    }

}