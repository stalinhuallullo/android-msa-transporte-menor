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

import java.util.List;

import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Propietario;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropietarioFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout layout_dots;
    View mView;

    public PropietarioFragment() {
        // Required empty public constructor
    }

    public static PropietarioFragment newInstance() {
        PropietarioFragment fragment = new PropietarioFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_propietario, container, false);
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
            TextView nombreCompleto = mView.findViewById(R.id.nombreCompleto);
            TextView direccion = mView.findViewById(R.id.direccion);
            TextView dni = mView.findViewById(R.id.dni);
            TextView telefonos = mView.findViewById(R.id.telefonos);
            TextView placaTemporal = mView.findViewById(R.id.placaTemporal);

            Unidad unidad = (Unidad) bundle.getSerializable("unidad");
            List<Propietario> propietarios = unidad.getPropietarios();
            if(!propietarios.equals("null") && propietarios.size() > 0) {
                nombreCompleto.setText((propietarios.get(0).getNombreCompleto() != null) ? propietarios.get(0).getNombreCompleto() : "");
                direccion.setText((propietarios.get(0).getDireccion() != null) ? propietarios.get(0).getDireccion() : "");
                dni.setText(!(propietarios.get(0).getNumeroDni().equals("null")) ? propietarios.get(0).getNumeroDni() : "");
                telefonos.setText(!(propietarios.get(0).getTelefonos().equals("null")) ? propietarios.get(0).getTelefonos() : "");
                placaTemporal.setText((propietarios.get(0).getPlacaTemporal() != null) ? propietarios.get(0).getPlacaTemporal() : "");
            }

        }

    }
}
