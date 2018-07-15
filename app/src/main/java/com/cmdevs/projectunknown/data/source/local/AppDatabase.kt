package com.cmdevs.projectunknown.data.source.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.cmdevs.projectunknown.data.Friend
import com.cmdevs.projectunknown.ioThread

@Database(entities = arrayOf(Friend::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        fillFakeDataDb(context.applicationContext)
                    }
                }).build()
        }

        fun fillFakeDataDb(context: Context) {
            ioThread {
                val fakeItems = mutableListOf<Friend>()
                fakeItems.add(0, Friend("cylee", "이치영", 28, "코딩은 어려웡", "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_01.png?raw=true"))
                fakeItems.add(1, Friend("mhham", "함명호", 29, "여자친구 구함", "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_02.png?raw=true"))
                fakeItems.add(2, Friend("hkseong", "성홍규", 31, "감성터짐", "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_03.png?raw=true"))
                fakeItems.add(3, Friend("cjgo", "고창재", 32, "노장", "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_04.png?raw=true"))
                fakeItems.add(4, Friend("dsshin", "신대성", 30, "계란한판..", "https://github.com/taehwandev/Kotlin-Udemy-Sample/blob/No42-Image-load-library/app/src/main/res/drawable/sample_05.png?raw=true"))
                getInstance(context.applicationContext).friendDao().insertAll(fakeItems)
            }
        }
    }

}