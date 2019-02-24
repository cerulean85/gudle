package techpda.net.cliplearning

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger

import kotlinx.android.synthetic.main.activity_login.*
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.ApiErrorCode
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.OptionalBoolean



open class LoginActivity : AppCompatActivity() {

    private var callback: SessionCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginKakaobb.setOnClickListener {

        }

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback!!)
        Session.getCurrentSession().checkAndImplicitOpen()

        btnBackbb.setOnClickListener {  onBackPressed() }

        btnJoinKakao.setOnClickListener {

            val properties = HashMap<String, String>()
//            properties["nickname"] = "leo"
//            properties["age"] = "33"


            UserManagement.getInstance().requestSignup(object : ApiResponseCallback<Long>() {
                override fun onNotSignedUp() {
                    println("Navit 11")
                }

                override fun onSuccess(result: Long?) {
                    println("Navit 22")
                    requestMe()
                }

                override fun onFailure(errorResult: ErrorResult?) {
                    println("Navit 33")
                    val message = "UsermgmtResponseCallback : failure : " + errorResult!!
                    com.kakao.util.helper.log.Logger.w(message)
//                    KakaoToast.makeToast(applicationContext, message, Toast.LENGTH_LONG).show()
                    finish()
                }

                override fun onSessionClosed(errorResult: ErrorResult) {
                    println("Navit 44")

                }
            }, properties)

        }


    }

    protected fun requestMe() {
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                val message = "failed to get user info. msg=" + errorResult!!
                Logger.d(message)

                val result = errorResult.errorCode
                if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
//                    Toast.makeToast(
//                        applicationContext,
//                        getString(R.string.error_message_for_service_unavailable),
//                        Toast.LENGTH_SHORT
//                    ).show()
                    finish()
                } else {
                    redirectLoginActivity()
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult) {
                Toast.makeText(applicationContext, "세션 닫힘", Toast.LENGTH_SHORT)
                redirectLoginActivity()
            }

            override fun onSuccess(result: MeV2Response) {
                if (result.hasSignedUp() == OptionalBoolean.FALSE) {
//                    showSignup()
                } else {
                    redirectMainActivity()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            onBackPressed()
//            redirectSignupActivity()
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
//            if (exception != null) {
//                Logger.e(exception)
//            }
        }
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
