package com.learner.globochat


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val settingsPref = findPreference<Preference>("key_account_setting")
        settingsPref?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
//            val action = ChatListFragmentDirections.actionChatListToSettings()
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()

            navController.navigate(action)
            true
        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val autoReplyTime = sharedPreference?.getString("key_auto_reply_time", "")
        val autoDownload =  sharedPreference?.getBoolean("key_auto_download", false)

        val statusPreference = findPreference<EditTextPreference>("key_status")
//        statusPreference?.setOnPreferenceChangeListener { preference, newValue ->
//            val newStatus = newValue as String
//            if (newStatus.contains("bad")) {
//                Toast.makeText(context, "Inappropriate Status. Please maintain guidelines.", Toast.LENGTH_SHORT)
//                    .show()
//                false
//            } else {
//                true
//            }
//        }

        statusPreference?.setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
                Log.i("SettingsFragment", "Status Changed: $newValue")
                return true
            }
        })

        val notificationPreference = findPreference<SwitchPreference>("key_new_msg_notif")
        notificationPreference?.summaryProvider = Preference.SummaryProvider<SwitchPreference> {
            if(it?.isChecked!!) {
                "Status: On"
            }else {
                "Status: Off"
            }
            "switch summary"
        }
    }

}