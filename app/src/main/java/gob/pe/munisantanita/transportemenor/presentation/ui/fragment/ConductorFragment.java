package gob.pe.munisantanita.transportemenor.presentation.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Conductor;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConductorFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout layout_dots;
    View mView;

    public ConductorFragment() {
        // Required empty public constructor
    }

    public static ConductorFragment newInstance() {
        ConductorFragment fragment = new ConductorFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_conductor, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initComponent();
    }

    private void initComponent() {
        layout_dots = mView.findViewById(R.id.layout_dots);
        viewPager = mView.findViewById(R.id.pager);

        Bundle bundle = getArguments();
        if (bundle != null) {
            TextView numeroPlaca = mView.findViewById(R.id.numeroPlaca);
            TextView nombres = mView.findViewById(R.id.nombres);
            TextView apellidos = mView.findViewById(R.id.apellidos);
            TextView direccion = mView.findViewById(R.id.direccion);
            TextView numeroDni = mView.findViewById(R.id.numeroDni);
            TextView telefonos = mView.findViewById(R.id.telefonos);
            TextView numeroLicencia = mView.findViewById(R.id.numeroLicencia);
            TextView clase = mView.findViewById(R.id.clase);
            TextView categoria = mView.findViewById(R.id.categoria);

            Unidad unidad = (Unidad) bundle.getSerializable("unidad");
            List<Conductor> conductores = unidad.getConductores();


            if(!conductores.equals("null") && conductores.size() > 0) {
                numeroPlaca.setText(!(conductores.get(0).getNumeroPlaca().equals("null")) ? conductores.get(0).getNumeroPlaca() : "");

                nombres.setText((conductores.get(0).getNombres() != null) ? conductores.get(0).getNombres() : "");
                apellidos.setText((conductores.get(0).getApellidos() != null) ? conductores.get(0).getApellidos() : "");
                direccion.setText((conductores.get(0).getDireccion() != null) ? conductores.get(0).getDireccion() : "");
                numeroDni.setText((conductores.get(0).getNumeroDni() != null) ? conductores.get(0).getNumeroDni() : "");
                telefonos.setText((conductores.get(0).getTelefonos() != null) ? conductores.get(0).getTelefonos() : "");
                numeroLicencia.setText((conductores.get(0).getNumeroLicencia() != null) ? conductores.get(0).getNumeroLicencia() : "");
                clase.setText((conductores.get(0).getClase() != null) ? conductores.get(0).getClase() : "");
                categoria.setText((conductores.get(0).getCategoria() != null) ? conductores.get(0).getCategoria() : "");
            }
        }

    }
}
