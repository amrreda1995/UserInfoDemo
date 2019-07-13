package com.user.info.demo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
class User(
        @PrimaryKey(autoGenerate = true) var _ID: Long? = null,
        var name: String = "",
        var age: Int = 0,
        @ColumnInfo(name = "job_title") var jobTitle: String = "",
        var gender: String = "Male"
)