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
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Empresa;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Propietario;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmpresaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmpresaFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout layout_dots;
    View mView;

    public EmpresaFragment(){

    }

    public static EmpresaFragment newInstance() {
        EmpresaFragment fragment = new EmpresaFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_empresa, container, false);
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
            TextView razonSocial = mView.findViewById(R.id.razonSocial);
            TextView numeroRuc = mView.findViewById(R.id.numeroRuc);
            TextView tipoEmpresa = mView.findViewById(R.id.tipoEmpresa);
            TextView direccion = mView.findViewById(R.id.direccion);
            TextView telefono = mView.findViewById(R.id.telefono);
            TextView maximoUnidades = mView.findViewById(R.id.maximoUnidades);

            Unidad unidad = (Unidad) bundle.getSerializable("unidad");
            Empresa empresa = unidad.getEmpresa();
            //System.out.println("empresa " + empresa);
            if(empresa != null && !empresa.equals("null")) {
                razonSocial.setText((empresa.getRazonSocial() != null) ? empresa.getRazonSocial() : "");
                numeroRuc.setText((empresa.getNumeroRuc() != null) ? empresa.getNumeroRuc() : "");
                //if(empresa.getTipo() != null) tipoEmpresa.setText((empresa.getTipo().getNombre() != null) ? empresa.getTipo().getNombre() : "");
                direccion.setText((empresa.getDireccion() != null) ? empresa.getDireccion() : "");
                telefono.setText((empresa.getTelefono() != null) ? empresa.getTelefono() : "");
                maximoUnidades.setText((empresa.getMaximoUnidades() != null) ? empresa.getMaximoUnidades() : "");
            }


        }
    }
}
