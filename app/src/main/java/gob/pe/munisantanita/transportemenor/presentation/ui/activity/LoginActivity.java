package gob.pe.munisantanita.transportemenor.presentation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.content.ContextCompat;

//import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import gob.pe.munisantanita.transportemenor.BuildConfig;
import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.presentation.utils.Tools;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private String apiSesion = BuildConfig.base_url + "ApiUsuarios/usuarios/autenticacion";
    private TextInputEditText usuario;
    private TextInputEditText contrasena;
    private AppCompatCheckBox cbxSesion;
    private Button btn_iniciar_sesion;
    private Button btn_clear;
    private Context ctx;
    private ProgressDialog pd;
    private SharedPreferences sharedPref;
    private String STRING_PREFERENCES = "transporteMenor_sesion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        /*View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);*/

        ctx = this;
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
        cbxSesion = findViewById(R.id.cbxSesion);
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        btn_iniciar_sesion.setOnClickListener(this);

        sharedPref = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);

        initToolbar();
        //usuario.setText("admin");
        //contrasena.setText("12345678");

        validarSesionAutomatica();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, R.color.mdtp_white);
    }

    private void mostrarVistaMain(){
        Intent intentView = new Intent(ctx, BuscarActivity.class);
        startActivity(intentView);
        finish();
    }

    private void validarSesionAutomatica(){
        String id = sharedPref.getString("id", "");
        String sesionActiva = sharedPref.getString("sesionActiva", "");

        if(id != null && sesionActiva.equals("1")) mostrarVistaMain();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_iniciar_sesion:
                validarIniciarSesion();
                break;
        }
    }

    private void showProgress() {
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.setCancelable(false);
        pd.show();
    }

    public void hiddenProgress() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
            pd = null;
        }
    }

    public void mostrarDialogo(final String msj, final boolean type) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setTitle("¡Mensaje!");
                    builder.setMessage(msj);
                    if (type){
                        builder.setPositiveButton(
                                "Aceptar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });
                    }
                    else builder.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() { // define the 'Cancel' button
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                }
            }
        });
    }

    private void parse(String response) {

        try {
            Log.e("RESPONSE", response);

            JSONObject Jobject = new JSONObject(response);
            String message = Jobject.get("message").toString();
            String status = Jobject.get("status").toString();

            if( status.equals("error") ){
                mostrarDialogo(message, false);
                hiddenProgress();
            } else {
                JSONObject object = Jobject.getJSONObject("data");

                sharedPref
                        .edit()
                        .putString("id", object.getString("id"))
                        .putString("sesionActiva", (cbxSesion.isChecked()) ? "1" : "0" )
                        .putString("token", object.getString("tokenType") + " " + object.getString("token"))
                        .putString("cuenta", object.getJSONObject("perfil").getString("cuenta"))
                        .putString("nombre", object.getJSONObject("perfil").getString("nombres"))
                        .putString("apellido", object.getJSONObject("perfil").getString("ape_paterno") + " " + object.getJSONObject("perfil").getString("ape_materno"))
                        .apply();
                hiddenProgress();
                mostrarVistaMain();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            mostrarDialogo("Ocurrio un error, por favor intente nuevamente.", false);
            hiddenProgress();
        }
    }


    private void validarIniciarSesion(){

        String _usuario = usuario.getText().toString().trim();
        String _contrasena = contrasena.getText().toString().trim();
        String msj = "";

        if(_usuario.length() <= 0) msj = "Usuario incorrecto";
        else if(_contrasena.length() <= 0) msj = "Contraseña incorrecta";

        if(msj.length() <= 0) mostrarResultado(_usuario, _contrasena);
        else mostrarDialogo(msj, false);

    }

    private void mostrarResultado(String usuario, String contrasena) {
        showProgress();
        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("cuenta", usuario);
            postdata.put("contrasena", contrasena);
        } catch(JSONException e){
            e.printStackTrace();
        }

        Log.e("AAAAAA", apiSesion);
        RequestBody formBody = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Log.e("BBBBBBB", formBody.toString());
        Request request = new Request.Builder()
                .url(apiSesion)
                .post(formBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                //mostrarDialogo("onFailure", false);
                hiddenProgress();
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                parse(data);
            }
        });
    }
}
