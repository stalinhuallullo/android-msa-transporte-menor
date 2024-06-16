package gob.pe.munisantanita.transportemenor.presentation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gob.pe.munisantanita.transportemenor.BuildConfig;
import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Conductor;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Empresa;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Foto;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Propietario;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Seguro;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Tipo;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;
import gob.pe.munisantanita.transportemenor.presentation.utils.Tools;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BuscarActivity extends AppCompatActivity implements View.OnClickListener {

    String apiBuscar = BuildConfig.base_url + BuildConfig._api + "unidades/";
    Toolbar toolbar;
    EditText buscar;
    ProgressDialog pd;
    Context ctx;
    FloatingActionButton btn_buscar;
    private SharedPreferences sharedPref;
    private String STRING_PREFERENCES = "transporteMenor_sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        ctx = this;
        buscar = findViewById(R.id.buscar);
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(this);

        //buscar.setText("NM10392");
        //buscar.setText("4433GA");
        sharedPref = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        initToolbar();
    }


    private void initToolbar() {
        /*toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);*/
        Tools.setSystemBarColor(this, R.color.green_900);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buscar:
                validarBusqueda();
                break;
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

    private void validarBusqueda(){

        String _buscar = buscar.getText().toString().trim();
        String msj = "";

        if(_buscar.length() <= 0) msj = "Ingrese una placa para buscar";

        if(msj.length() <= 0) mostrarResultado(_buscar);
        else mostrarDialogo(msj, false);

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

    private void mostrarResultado(String buscar) {
        showProgress();
        OkHttpClient client = new OkHttpClient();
        String url = apiBuscar+buscar+"/full";
        Log.e("url-url-url", url);
        String token = sharedPref.getString("token", "");
        Log.e("tokeeeeeen", token);
        Request request = new Request.Builder()
                .url(url)
                //.header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", (token!=null) ? token : "")
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

    public void parse(String response) {
        try {
            hiddenProgress();

            System.out.println("=============> OBJ: "+response);

            JSONObject Jobject = new JSONObject(response);

            String message = Jobject.get("message").toString();
            String status = Jobject.get("status").toString();

            if( status.equals("notFound") ){
                mostrarDialogo(message, false);
                hiddenProgress();
            } else {
                Log.e("DATA ==> ", Jobject.toString());

                if( Jobject.has("data") && !Jobject.isNull("data") ) {

                    Log.e("DATA", Jobject.getJSONObject("data").toString());
                    JSONObject obj = Jobject.getJSONObject("data");
                    Empresa empresa = new Empresa();
                    //empresa = null;
                    Tipo tipo = new Tipo();
                    tipo.setId("");
                    tipo.setNombre("");

                    if(  obj.has("empresa") && !obj.isNull("empresa") ) {
                        JSONObject objEmpresa  = obj.getJSONObject("empresa");
                        Log.e("gggggggggggggggggggg ", "fffffffffffffff");
                        Log.e("tipo ", tipo.toString());
                        if (  objEmpresa.has("tipo") && !objEmpresa.isNull("tipo") ) {
                            JSONObject tipoObjEmpresa = objEmpresa.getJSONObject("tipo");
                            tipo.setId(tipoObjEmpresa.getString("id"));
                            tipo.setNombre(tipoObjEmpresa.getString("nombre"));
                        }

                        // Empresa

                        Log.e("objEmpresa ", objEmpresa.toString());
                        Log.e("aaaaaaaaaaaaaa ", objEmpresa.getString("id"));
                        empresa.setId((objEmpresa.getString("id") != null) ? objEmpresa.getString("id") : "");
                        empresa.setTipo(tipo);
                        empresa.setSiglaComercial(objEmpresa.getString("siglaComercial"));
                        empresa.setRazonSocial(objEmpresa.getString("razonSocial"));
                        empresa.setDireccion(objEmpresa.getString("direccion"));
                        empresa.setNumeroRuc(objEmpresa.getString("numeroRuc"));
                        empresa.setTelefono(objEmpresa.getString("telefono"));
                        empresa.setEstadoEmpadronado(objEmpresa.getString("estadoEmpadronado"));
                        empresa.setAutorizacion(objEmpresa.getString("autorizacion"));
                        empresa.setFechaInscripcion(objEmpresa.getString("fechaInscripcion"));
                        empresa.setCodigoAnterior(objEmpresa.getString("codigoAnterior"));
                        empresa.setMaximoUnidades(objEmpresa.getString("maximoUnidades"));
                    }
                    Seguro seguro = new Seguro();
                    //seguro = null;


                    if ( obj.has("seguro") && !obj.isNull("seguro")  ) {
                        JSONObject seguroObj = obj.getJSONObject("seguro");
                        seguro.setId(seguroObj.getString("id"));
                        seguro.setNombre(seguroObj.getString("nombre"));
                    }

                    List<Propietario> listPropietario = new ArrayList<>();
                    JSONArray arrayPropietario = obj.getJSONArray("propietarios");
                    for (int i = 0; i < arrayPropietario.length(); i++) {
                        Propietario propietario = new Propietario();
                        propietario.setId(arrayPropietario.getJSONObject(i).getString("id"));
                        propietario.setNombreCompleto(arrayPropietario.getJSONObject(i).getString("nombreCompleto"));
                        propietario.setDireccion(arrayPropietario.getJSONObject(i).getString("direccion"));
                        propietario.setNumeroDni(arrayPropietario.getJSONObject(i).getString("numeroDni"));
                        propietario.setTelefonos(arrayPropietario.getJSONObject(i).getString("telefonos"));
                        propietario.setPlacaTemporal(arrayPropietario.getJSONObject(i).getString("placaTemporal"));
                        propietario.setTipoDocumento(arrayPropietario.getJSONObject(i).getString("tipoDocumento"));
                        propietario.setUnidades(arrayPropietario.getJSONObject(i).getString("unidades"));
                        listPropietario.add(propietario);
                    }

                    List<Conductor> listConductor = new ArrayList<>();
                    JSONArray arrayConductor = obj.getJSONArray("conductores");
                    for (int i = 0; i < arrayConductor.length(); i++) {
                        Conductor conductor = new Conductor();
                        conductor.setId(arrayConductor.getJSONObject(i).getString("id"));
                        conductor.setNumeroPlaca((arrayConductor.getJSONObject(i).getString("numeroPlaca").length() > 0) ? arrayConductor.getJSONObject(i).getString("numeroPlaca") : "");
                        conductor.setNombres(arrayConductor.getJSONObject(i).getString("nombres"));
                        conductor.setApellidos(arrayConductor.getJSONObject(i).getString("apellidos"));
                        conductor.setDireccion(arrayConductor.getJSONObject(i).getString("direccion"));
                        conductor.setNumeroDni(arrayConductor.getJSONObject(i).getString("numeroDni"));
                        conductor.setTelefonos(arrayConductor.getJSONObject(i).getString("telefonos"));
                        conductor.setNumeroLicencia(arrayConductor.getJSONObject(i).getString("numeroLicencia"));
                        conductor.setClase(arrayConductor.getJSONObject(i).getString("clase"));
                        conductor.setCategoria(arrayConductor.getJSONObject(i).getString("categoria"));
                        conductor.setAutorExpide(arrayConductor.getJSONObject(i).getString("autorExpide"));
                        conductor.setEmpadronado(arrayConductor.getJSONObject(i).getString("empadronado"));
                        conductor.setNumeroCredencial(arrayConductor.getJSONObject(i).getString("numeroCredencial"));
                        conductor.setPermisoOperacion(arrayConductor.getJSONObject(i).getString("permisoOperacion"));
                        conductor.setFechaAlta(arrayConductor.getJSONObject(i).getString("fechaAlta"));
                        conductor.setFechaCaducidad(arrayConductor.getJSONObject(i).getString("fechaCaducidad"));
                        conductor.setFechaRevalidacion(arrayConductor.getJSONObject(i).getString("fechaRevalidacion"));
                        conductor.setFechaBaja(arrayConductor.getJSONObject(i).getString("fechaBaja"));
                        conductor.setNumeroFlota(arrayConductor.getJSONObject(i).getString("numeroFlota"));
                        conductor.setVerificacion(arrayConductor.getJSONObject(i).getString("verificacion"));
                        conductor.setCertificadoO(arrayConductor.getJSONObject(i).getString("certificadoO"));
                        conductor.setCaducidad(arrayConductor.getJSONObject(i).getString("caducidad"));
                        conductor.setFechaEmision(arrayConductor.getJSONObject(i).getString("fechaEmision"));
                        conductor.setSeguroVial(arrayConductor.getJSONObject(i).getString("seguroVial"));
                        conductor.setCodigoEmpresaTemporal(arrayConductor.getJSONObject(i).getString("codigoEmpresaTemporal"));
                        conductor.setTipoDocumento(arrayConductor.getJSONObject(i).getString("tipoDocumento"));
                        listConductor.add(conductor);
                    }

                    List<Foto> listFoto = new ArrayList<>();
                    JSONArray arrayFoto = obj.getJSONArray("fotos");

                /*for (int i = 0; i < arrayFoto.length(); i++){
                    Foto foto = new Foto();
                    foto.setId(arrayConductor.getJSONObject(i).getString("id"));
                    foto.setNombre(arrayConductor.getJSONObject(i).getString("nombre"));
                    listConductor.add(conductor);
                }*/
                    Unidad unidad = new Unidad();

                    // Unidad
                    unidad.setId(obj.getString("id"));
                    unidad.setEmpresa(empresa);
                    unidad.setNumeroTituloPropierdad(obj.getString("numeroTituloPropierdad"));
                    unidad.setNumeroPlaca(obj.getString("numeroPlaca"));
                    unidad.setClase(obj.getString("clase"));
                    //unidad.setMarca(obj.getString("marca"));
                    if(  obj.has("marca") && !obj.isNull("marca") ) {
                        JSONObject marca = obj.getJSONObject("marca");
                        System.out.println("aaaa" + marca);
                        unidad.setMarca(marca.getString("nombre"));
                    }
                    unidad.setAnio(obj.getString("anio"));
                    unidad.setModelo(obj.getString("modelo"));
                    unidad.setColor(obj.getString("color"));
                    unidad.setNumeroMotor(obj.getString("numeroMotor"));
                    unidad.setNumeroSerie(obj.getString("numeroSerie"));
                    unidad.setCondicion(obj.getString("condicion"));
                    unidad.setCia(obj.getString("cia"));
                    unidad.setFechaVencimiento(obj.getString("fechaVencimiento"));
                    unidad.setFechaRegistro(obj.getString("fechaRegistro"));
                    unidad.setCodigoEstado(obj.getString("codigoEstado"));
                    unidad.setPermisoOperacion(obj.getString("permisoOperacion"));
                    unidad.setFechaCaducidad(obj.getString("fechaCaducidad"));
                    unidad.setCertificadoOperacion(obj.getString("certificadoOperacion"));
                    unidad.setFechaCaduca(obj.getString("fechaCaduca"));
                    unidad.setEntregado(obj.getString("entregado"));
                    unidad.setObservaciones(obj.getString("observaciones"));
                    unidad.setAltaBaja(obj.getString("altaBaja"));
                    unidad.setNumeroFlota(obj.getString("numeroFlota"));
                    unidad.setSeguro(seguro);
                    unidad.setNumeroPoliza(obj.getString("numeroPoliza"));
                    unidad.setPropietarios(listPropietario);
                    unidad.setConductores(listConductor);
                    unidad.setFotos(listFoto);

                    Intent intentView = new Intent(ctx, UnidadActivity.class);
                    intentView.putExtra("unidad", unidad);
                    hiddenProgress();
                    startActivity(intentView);

                } else {
                    mostrarDialogo("Ha ocurrido un error, por favor comuníquese al área de SGTIC", false);
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
            mostrarDialogo("Ocurrio un error, por favor intente nuevamente.", false);
            hiddenProgress();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        hiddenProgress();
    }
}
