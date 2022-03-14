package com.ofek.superheroes.searchscreen.network

object NetworkConstants {
    // one day in milliseconds
    const val picasso_cache_expiration_milliseconds: Int = 86400000
    // 60MB in bytes
    const val picasso_cache_limit_bytes: Long = 60 * 1024 * 1024
    private const val api_access_token = "10159869502921382"
    const val superheroes_api_base_url = "https://superheroapi.com/api/$api_access_token/"
}