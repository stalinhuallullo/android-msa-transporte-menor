package gob.pe.munisantanita.transportemenor.presentation.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

import gob.pe.munisantanita.transportemenor.R;
import gob.pe.munisantanita.transportemenor.data.model.Image;
import gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel.Unidad;
import gob.pe.munisantanita.transportemenor.presentation.utils.Tools;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnidadFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private AdapterImageSlider adapterImageSlider;
    View mView;


    private static int[] array_image_product = {
            R.drawable.mototaxi,
            R.drawable.mototaxis_2,
    };

    public UnidadFragment() {
        // Required empty public constructor
    }

    public static UnidadFragment newInstance() {
        UnidadFragment fragment = new UnidadFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_unidad, container, false);

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
        adapterImageSlider = new AdapterImageSlider(getActivity(), new ArrayList<Image>());

        List<Image> items = new ArrayList<>();
        for (int i : array_image_product) {
            Image obj = new Image();
            obj.image = i;
            obj.imageDrw = getResources().getDrawable(obj.image);
            items.add(obj);
        }

        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        Bundle bundle = getArguments();
        if (bundle != null) {

            TextView observaciones = mView.findViewById(R.id.observaciones);
            TextView numeroTituloPropierdad = mView.findViewById(R.id.numeroTituloPropierdad);
            TextView numeroPlaca = mView.findViewById(R.id.numeroPlaca);
            TextView clase = mView.findViewById(R.id.clase);
            TextView marca = mView.findViewById(R.id.marca);
            TextView anio = mView.findViewById(R.id.anio);
            TextView modelo = mView.findViewById(R.id.modelo);
            TextView color = mView.findViewById(R.id.color);
            TextView numeroMotor = mView.findViewById(R.id.numeroMotor);
            TextView numeroSerie = mView.findViewById(R.id.numeroSerie);
            TextView numeroPoliza = mView.findViewById(R.id.numeroPoliza);
            TextView seguro = mView.findViewById(R.id.seguro);
            TextView fechaVencimiento = mView.findViewById(R.id.fechaVencimiento);
            TextView numeroFlota = mView.findViewById(R.id.numeroFlota);
            TextView altaBaja = mView.findViewById(R.id.altaBaja);


            Unidad unidad = (Unidad) bundle.getSerializable("unidad");

            observaciones.setText(unidad.getObservaciones());
            numeroTituloPropierdad.setText(unidad.getNumeroTituloPropierdad());
            numeroPlaca.setText(unidad.getNumeroPlaca());
            clase.setText(unidad.getClase());
            marca.setText(unidad.getMarca());
            anio.setText(unidad.getAnio());
            modelo.setText(unidad.getModelo());
            color.setText(unidad.getColor());
            numeroMotor.setText(unidad.getNumeroMotor());
            numeroSerie.setText(unidad.getNumeroSerie());
            numeroPoliza.setText(unidad.getNumeroPoliza());
            if(unidad.getSeguro() != null) seguro.setText(unidad.getSeguro().getNombre());
            fechaVencimiento.setText(unidad.getFechaVencimiento());
            numeroFlota.setText(unidad.getNumeroFlota());
            altaBaja.setText( (unidad.getAltaBaja().equals("1")) ? "ALTA" : "BAJA");
        }
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.ic_menu);
            dots[i].setColorFilter(ContextCompat.getColor(getActivity(), R.color.overlay_dark_10), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_50), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private List<Image> items;

        private OnItemClickListener onItemClickListener;

        private interface OnItemClickListener {
            void onItemClick(View view, Image obj);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSlider(Activity activity, List<Image> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public Image getItem(int pos) {
            return items.get(pos);
        }

        public void setItems(List<Image> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Image o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(act, image, o.image);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, o);
                    }
                }
            });

            ((ViewPager) container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }

    }
}
