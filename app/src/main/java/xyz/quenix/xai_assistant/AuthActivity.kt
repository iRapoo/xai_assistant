package xyz.quenix.xai_assistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton

class AuthActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var googleApi: GoogleApiClient
    private lateinit var signInButton: SignInButton

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        signInButton = findViewById(R.id.activity_button_sign_in)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApi = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signIn()

        signInButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                signIn()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SING_IN) {
            val result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account: GoogleSignInAccount? = result.signInAccount

            val intent = Intent(this, ControlActivity::class.java)
            intent.putExtra("account", account)
            startActivity(intent)
            this.finish()
        } else {
            Snackbar.make(signInButton, R.string.error_login_failed, Snackbar.LENGTH_SHORT).show()
        }
    }

    fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApi)
        startActivityForResult(signInIntent, RC_SING_IN)
    }

    companion object {
        const val RC_SING_IN: Int = 9001
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    //Sign out
    /*
    btnLogout?.setOnClickListener(View.OnClickListener {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                object : ResultCallback<Status> {
                    override fun onResult(status: Status) {
                        updateUI(status.isSuccess)
                    }

                }
        )
    })*/

}
