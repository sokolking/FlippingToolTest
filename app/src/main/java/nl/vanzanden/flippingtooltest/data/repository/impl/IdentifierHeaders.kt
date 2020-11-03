package nl.vanzanden.flippingtooltest.data.repository.impl

import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import java.util.*

class IdentifierHeaders(preferences: PreferenceHelper) {
    private val headers: HashMap<String, String> = HashMap()
    fun get(): Set<Map.Entry<String, String>> {
        return headers.entries
    }

    init {
        headers["deviceType"] = "android"
    }
}