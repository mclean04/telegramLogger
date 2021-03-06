package vn.ai.aihtelegramloggerlib

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import vn.ai.aihtelegramlogger.Services.BuildEnv
import vn.ai.aihtelegramlogger.Services.SendMessage
import vn.ai.aihtelegramlogger.TelegramLogger
import vn.ai.aihtelegramloggerlib.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        TelegramLogger
            .Builder(
                "bot2130700463:AAH_XbAK0e_zYVefgJ_bvPsH12Fj51xzNOw",
                -693513093,
                "Anh Tú",
                userPhone = "+840000000567",
                allowSendMessage = true
            )
            .initialize()

        binding.fab.setOnClickListener { view ->
            try {
                throw RuntimeException("Run to this line and crash app");
            } catch (e: Exception) {
                TelegramLogger.sendMessage(
                    SendMessage(
                        functionName = "onCreate",
                        className = "MainActivity",
                        appName = applicationInfo.loadLabel(packageManager).toString(),
                        appVersion = "1.1.1",
                        errorText = e.message
                    )
                )
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}