package techpda.net.cliplearning

import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Base64.NO_WRAP
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.content.pm.PackageInstaller
import android.util.Base64
import android.util.Log
import android.view.inputmethod.InputMethod
import com.kakao.auth.Session
import com.kakao.util.helper.Utility.getPackageInfo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.kakao.auth. ISessionCallback
import com.kakao.util.exception.KakaoException
import android.content.Intent
import android.widget.Toast
import com.kakao.util.helper.log.Logger
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.UserManagement
import android.content.DialogInterface
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.callback.UnLinkResponseCallback






open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("HashKey: " + getKeyHash(applicationContext))

        btnDisconnectKakao.setOnClickListener {


            val appendMessage = getString(R.string.com_kakao_confirm_unlink)

            AlertDialog.Builder(this)
                .setMessage(appendMessage)
                .setPositiveButton(getString(R.string.com_kakao_ok_button)
                ) { dialog, _ ->
                    UserManagement.getInstance().requestUnlink(object : UnLinkResponseCallback() {
                        override fun onFailure(errorResult: ErrorResult?) {
                            Logger.e(errorResult!!.toString())
                        }

                        override fun onSessionClosed(errorResult: ErrorResult) {
                            redirectLoginActivity()
                        }

                        override fun onNotSignedUp() {
                            redirectSignupActivity()
                        }

                        override fun onSuccess(userId: Long?) {
                            Toast.makeText(applicationContext, "탈퇴완료", Toast.LENGTH_SHORT);
                        }
                    })
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.com_kakao_cancel_button)
                ) { dialog, _ -> dialog.dismiss() }.show()

        }

        btnLoginKakao.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }


    fun getKeyHash(context: Context): String? {
        val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

        for (signature in packageInfo!!.signatures) {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                println("Unable to get MessageDigest. signature=$signature")
            }

        }
        return null
    }


    protected fun redirectSignupActivity() {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)

    }

    protected fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    protected fun redirectMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
