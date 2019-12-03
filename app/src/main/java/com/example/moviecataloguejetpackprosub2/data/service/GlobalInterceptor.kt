package com.example.moviecataloguejetpackprosub2.data.service

import okhttp3.Interceptor
import okhttp3.Response

class GlobalInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
//            .addHeader("token_key", BuildConfig.TOKEN_KEY)
            .build()
        return chain.proceed(request)
    }
}