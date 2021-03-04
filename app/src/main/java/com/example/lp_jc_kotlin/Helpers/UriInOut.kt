package com.example.lp_jc_kotlin.Helpers

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type
import kotlin.jvm.Throws

class UriInOut : JsonSerializer<Uri>, JsonDeserializer<Uri> {
    override fun serialize(src: Uri, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(src: JsonElement, srcType: Type,
                             context: JsonDeserializationContext): Uri {
        return Uri.parse(src.asString)
    }
}