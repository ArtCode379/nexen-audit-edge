package nexenstrategy.technology.nexenauditedge.di

import androidx.room.Room
import nexenstrategy.technology.nexenauditedge.data.database.LMAQNDatabase
import org.koin.dsl.module

private const val DB_NAME = "lmaqn_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = LMAQNDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<LMAQNDatabase>().bookingDao()}

}