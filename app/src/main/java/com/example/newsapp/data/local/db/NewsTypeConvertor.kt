package com.example.newsapp.data.local.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapp.data.model.dto.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}