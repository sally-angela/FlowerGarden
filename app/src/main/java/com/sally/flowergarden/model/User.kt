package com.sally.flowergarden.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="email")
    var email:String?,
    @ColumnInfo(name="firstName")
    var firstName:String?,
    @ColumnInfo(name="lastName")
    var lastName:String?,
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="images")
    var images:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
