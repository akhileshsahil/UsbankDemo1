package online.mshoap.application

import android.app.Application
import android.content.Context
import com.example.usbankdemo.di.ApiModule
import com.example.usbankdemo.di.AppComponent
import com.example.usbankdemo.di.AppModule
import com.example.usbankdemo.di.DaggerAppComponent


class MyApplication : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        appComponent = initDaggerComponent()


    }

    public fun getMyComponent(): AppComponent {
        return appComponent
    }

    fun initDaggerComponent():AppComponent{
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).apiModule(
            ApiModule()
        ).build()

        return  appComponent

    }
}
