package se.stylianosgakis.marsrealestate.util

const val BASE_URL = "https://mars.udacity.com/"

enum class ApiFilter(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all");
}