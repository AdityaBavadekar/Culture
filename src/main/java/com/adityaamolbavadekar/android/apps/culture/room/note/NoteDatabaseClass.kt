/*******************************************************************************
 * Copyright (c) 2021. Aditya Bavadekar
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 ******************************************************************************/

package com.adityaamolbavadekar.android.apps.culture.room.note

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesDataClass::class], version = 1, exportSchema = false)
abstract class NoteDatabaseClass : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabaseClass? = null

        fun getDatabaseInstance(context: Context): NoteDatabaseClass {

            val subInstance = INSTANCE
            if (subInstance != null){
                return subInstance
            }else{
                synchronized(this){
                    val roomDatabaseINSTANCE = Room.databaseBuilder(
                        context,NoteDatabaseClass::class.java,"notes_database"
                    ).build()
                    INSTANCE = roomDatabaseINSTANCE
                    return roomDatabaseINSTANCE
                }
            }
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    NoteDatabaseClass::class.java,
//                    "notes_database"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                return instance
//            }
        }

    }

}