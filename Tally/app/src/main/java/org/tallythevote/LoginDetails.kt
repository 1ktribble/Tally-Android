package org.tallythevote

/**
 * Kotlin Login Activity.
 */

data class LoginDetails(val email: String? = null, val username:String? = null){
    companion object {
        val empty = LoginDetails()
    }

    val isAvailable get() = email != null && username != null
}