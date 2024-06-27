package com.sally.flowergarden.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flower(
    @ColumnInfo(name="title")
    var title:String?,
    @ColumnInfo(name="author")
    var author:String?,
    @ColumnInfo(name="description")
    var description:String?,
    @ColumnInfo(name="images")
    var images:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
