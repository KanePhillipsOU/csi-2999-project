package com.example.csi2999
import io.github.jan-tennert.supabase.createSupabaseClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

// import io.supabase.SupabaseClient
// import io.supabase.SupabaseClientOptions

@Service
class SupabaseConnection(
    @Value("\${dbUrl}") private val url: String?,
    @Value("\${dbApiKey}") private val key: String?
) {
    // private val url = @Value("${dbUrl}")
    // private val key = @Value("${dbApiKey}")
    // private val connection = SupabaseClient( url, key, SupabaseClientOptions(autoConnect = true)
    // )
    val supabase = createSupabaseClient(url, key) {
        defaultSerializer = JacksonSerializer()
    }


    fun getConnection(): String {
        return "test"
    }
}
