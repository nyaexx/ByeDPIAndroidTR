package io.github.dovecoteescapee.byedpi.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.Preference
import io.github.dovecoteescapee.byedpi.R
import android.content.Context
import android.widget.Toast

class ByeDpiCommandLineSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.byedpi_cmd_settings, rootKey)

        // Kopyalanabilir öğelere tıklama işlevini ekleyelim
        val arg1 = findPreference<Preference>("copy_arg_example_1")
        val arg2 = findPreference<Preference>("copy_arg_example_2")
        val arg3 = findPreference<Preference>("copy_arg_example_3")

        arg1?.setOnPreferenceClickListener {
            copyToClipboard("Hafif Etkili (Her ISS de Çalışmayabilir)", "-Ku -a3 -An -Kt,h -s0 -o1 -d2 -r2+s -Ar -At -f-1 -As -b+700 --tls-sni=www.cloudflare.com --ttl=2")
            true
        }

        arg2?.setOnPreferenceClickListener {
            copyToClipboard("Zorlu DPI İçin Ağır Yöntem", "-Ku -a5 -An -Kt,h -s1 -o2 -d3 -r3+s -Ar -At -f-2 -As -b+1000 --tls-sni=www.google.com --ttl=3")
            true
        }
        arg3?.setOnPreferenceClickListener {
            copyToClipboard("Basit Ancak (Superonline,Bimcell) Çalışıyor", "ciadpi --proto=http,tls --disorder 1 --tlsrec 1+s")
            true
        }
    }

    // Kopyalama işlemi için yardımcı fonksiyon
    private fun copyToClipboard(label: String, text: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)

        // Kopyalandığına dair kullanıcıya bildirim
        Toast.makeText(context, "$label kopyalandı!", Toast.LENGTH_SHORT).show()
    }
}
