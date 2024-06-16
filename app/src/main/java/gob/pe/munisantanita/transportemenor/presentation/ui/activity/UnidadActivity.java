package gob.pe.munisantanita.transportemenor.presentation.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;
import gob.pe.munisantanita.transportemenor.presentation.ui.fragment.ConductorFragment;
import gob.pe.munisantanita.transportemenor.presentation.ui.fragment.EmpresaFragment;
import gob.pe.munisantanita.transportemenor.presentation.ui.fragment.PropietarioFragment;
import gob.pe.munisantanita.transportemenor.presentation.ui.fragment.UnidadFragment;
import gob.pe.munisantanita.transportemenor.presentation.utils.Tools;

public class UnidadActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager view_pager;
    private SectionsPagerAdapter viewPagerAdapter;
    private TabLayout tab_layout;
    private String STRING_PREFERENCES = "transporteMenor_sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidad);

        initComponent();
        initToolbar();
    }


    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        setupViewPager(view_pager);

        tab_layout.setupWithViewPager(view_pager);

        tab_layout.getTabAt(0).setIcon(R.drawable.ic_directions_car);
        tab_layout.getTabAt(1).setIcon(R.drawable.ic_person);
        tab_layout.getTabAt(2).setIcon(R.drawable.ic_airline_seat);
        tab_layout.getTabAt(3).setIcon(R.drawable.ic_business);

        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        Tools.setSystemBarColor(this, R.color.green_900);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.nav_buscar:
                super.onBackPressed();
                return true;
            case R.id.nav_cerrar_sesion:
                preguntarCerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle parametros = this.getIntent().getExtras();
        Bundle bundle = new Bundle();


        if(parametros != null){
            Unidad unidad = (Unidad) parametros.getSerializable("unidad");
            bundle.putSerializable("unidad", unidad);

            UnidadFragment unidadFragment = new UnidadFragment();
            unidadFragment.setArguments(bundle);
            unidadFragment.newInstance();


            PropietarioFragment propietarioFragment = new PropietarioFragment();
            propietarioFragment.setArguments(bundle);
            propietarioFragment.newInstance();

            ConductorFragment conductorFragment = new ConductorFragment();
            conductorFragment.setArguments(bundle);
            conductorFragment.newInstance();

            EmpresaFragment empresaFragment = new EmpresaFragment();
            empresaFragment.setArguments(bundle);
            empresaFragment.newInstance();

            viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            viewPagerAdapter.addFragment(unidadFragment, "UNIDAD");
            viewPagerAdapter.addFragment(propietarioFragment, "PROPIETARIO");
            viewPagerAdapter.addFragment(conductorFragment, "CONDUCTOR");
            viewPagerAdapter.addFragment(empresaFragment, "EMPRESA");

            /*viewPagerAdapter.addFragment(UnidadFragment.newInstance(), "UNIDAD");
            viewPagerAdapter.addFragment(PropietarioFragment.newInstance(), "PROPIETARIO");
            viewPagerAdapter.addFragment(ConductorFragment.newInstance(), "CONDUCTOR");*/

            viewPager.setAdapter(viewPagerAdapter);
        }
        else{
            Toast.makeText(this, "No se encontro resultados", Toast.LENGTH_SHORT).show();
        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_header, menu);
        return true;
    }

    private void preguntarCerrarSesion() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Cerrar sesión");
        alertDialog.setMessage("¿Está seguro que desea cerrar la sesión?");

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrarSesion();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
        alertDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        alertDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    void cerrarSesion(){
        SharedPreferences sharedPreferences = getSharedPreferences( STRING_PREFERENCES, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

}
