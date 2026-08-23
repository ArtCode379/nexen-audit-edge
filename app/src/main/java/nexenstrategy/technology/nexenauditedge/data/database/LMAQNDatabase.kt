package nexenstrategy.technology.nexenauditedge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nexenstrategy.technology.nexenauditedge.data.dao.BookingDao
import nexenstrategy.technology.nexenauditedge.data.database.converter.Converters
import nexenstrategy.technology.nexenauditedge.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LMAQNDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

