package com.example.jedgar.spcav10;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;

/**
 * Created by a on 3/31/2015.
 */
public class DetailsPageFragment extends Fragment implements View.OnClickListener {
    DBHelper dbh;
    SQLiteDatabase db;
    public static final int C_ANIMAL_ID = 0;
    public static final int C_ANIMAL_NAME = 2;
    public static final int C_ANIMAL_PHOTO1 = 14;
    public static final int C_ANIMAL_PHOTO2 = 15;
    public static final int C_ANIMAL_PHOTO3 = 16;

    PagerAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    LayoutInflater inflater;
    int positionx;

    Cursor c;
    boolean parSections;
    private ViewPager detailsPager;
    private DetailsPagerAdapter detailsAdapter;


    public DetailsPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // View rootView = inflater.inflate(R.layout.details_page_fragment, container, false);
        View rootView = inflater.inflate(R.layout.details, container, false);



        dbh = new DBHelper(getActivity());
        db = dbh.getWritableDatabase();

        c = dbh.getAnimalList(db);
        c.moveToPosition(1);
        String t = Integer.toString(c.getInt(C_ANIMAL_ID));
        String d = c.getString(C_ANIMAL_NAME);
        String p1 = c.getString(C_ANIMAL_PHOTO1);
        String p2 = c.getString(C_ANIMAL_PHOTO2);
        String p3 = c.getString(C_ANIMAL_PHOTO3);

        // TextView detail = (TextView)rootView.findViewById(R.id.textView2);
        //  detail.setText(t + d + " P1: " + p1 + " P2: " + p2 + " P3: " + p3);

        this.inflater = inflater;// =(LayoutInflater) getActivity().getSystemService(getActivity().getBaseContext().LAYOUT_INFLATER_SERVICE);//.getLayoutInflater();

        detailsAdapter = new DetailsPagerAdapter(3);
        detailsPager = (ViewPager) rootView.findViewById(R.id.detailsPager);
        detailsPager.setAdapter(detailsAdapter);
        detailsPager.setCurrentItem(1);

//        mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
//        mPager = (ViewPager) rootView.findViewById(R.id.awesomepager);
//        mPager.setAdapter(mAdapter);
//        mPager.setOnClickListener(this);

//        mPager.setOnTouchListener(
//                new View.OnTouchListener(){
//                    private boolean moved;
//
//
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if(event.getAction() == MotionEvent.ACTION_DOWN){
//                            moved = false;
//                        }
//                        if(event.getAction() == MotionEvent.ACTION_MOVE){
//                            moved = true;
//                        }
//                        if(event.getAction() == MotionEvent.ACTION_UP){
//                            if(!moved){
//                                v.performClick();
//                            }
//                        }
//                        return false;
//                    }
//                }
//        );
        //  setContentView(R.layout.detail);

        return rootView;
    }

    public static DetailsPageFragment newInstance() {
        DetailsPageFragment fragment = new DetailsPageFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(6);
    }

    @Override
    public void onClick(View v) {
 //       Toast.makeText(DetailsPageFragment.this.getActivity(), "clickedImage", Toast.LENGTH_SHORT).show();
//        Toast.makeText(DetailsPageFragment.this.getActivity(), "Count is: " + positionx, Toast.LENGTH_SHORT).show();
//
//
//        /////    mLargeAnimalImageFragment = (LargeAnimalImageFragment)
//        //////        getFragmentManager().findFragmentById(R.id.large_animal_image);
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack so the user can navigate back
//        transaction.replace(R.id.container, LargeAnimalImageFragment.newInstance(mAdapter, positionx));
//        transaction.addToBackStack(null);
//        transaction.commit();
//


//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.container,  mAdapter.getItem(positionx));
//        transaction.addToBackStack(null);
//        transaction.commit();

        // Fragment fg = LargeAnimalImageFragment.newInstance();
        // adding fragment to relative layout by using layout id
        //  getFragmentManager().beginTransaction().add(R.id.details_fragment, fg).commit();
    }

    private class DetailsPagerAdapter extends android.support.v4.view.PagerAdapter {

        String blah;

        private DetailsPagerAdapter(int i){ this.blah = ""+ i;}
        @Override
        public Object instantiateItem(View collection, int position) {
            LinearLayout detail = (LinearLayout) inflater.inflate(
                    R.layout.details_page_fragment /* date_page2 */, null);
            Log.d("pagerAdapter", "after linearlayout inflate");
            // Button button = (Button)detail.findViewById(R.id.button);
            //button.setText(blah + "29038572364578236945234");


            c = dbh.getAnimalList(db);
            c.moveToPosition(position);
            String t = Integer.toString(c.getInt(C_ANIMAL_ID));
            String d = c.getString(C_ANIMAL_NAME);
            String p1 = c.getString(C_ANIMAL_PHOTO1);
            String p2 = c.getString(C_ANIMAL_PHOTO2);
            String p3 = c.getString(C_ANIMAL_PHOTO3);

            ArrayList<String> imagesUrl = new ArrayList<String>();
            ArrayList<ImageView> imageCounterImages = new ArrayList<ImageView>();
            if(!p1.equals("")) {
                imagesUrl.add(p1);
                ImageView boule0 = (ImageView)detail.findViewById(R.id.boule0);
                //  boule0.setVisibility(View.VISIBLE);
                // imageCounterImages.add((ImageView)detail.findViewById(R.id.boule0));
            }
            if(!p2.equals("")){
                imagesUrl.add(p2);
                ImageView boule1 = (ImageView)detail.findViewById(R.id.boule1);
                // boule1.setVisibility(View.VISIBLE);
                //imageCounterImages.add((ImageView)detail.findViewById(R.id.boule1));
            }
            if(!p3.equals("")){
                imagesUrl.add(p3);
                ImageView boule2 = (ImageView)detail.findViewById(R.id.boule2);
                //  boule2.setVisibility(View.VISIBLE);
                // imageCounterImages.add((ImageView)detail.findViewById(R.id.boule2));
            }



            Log.d("IMAGE PAGER", "" + imageCounterImages.size());
            TextView detailsText = (TextView)detail.findViewById(R.id.detailsText);
            detailsText.setText(t + d + " P1: " + p1 + " P2: " + p2 + " P3: " + p3);

            ImagePagerAdapter imageAdapter = new ImagePagerAdapter(imagesUrl, imageCounterImages, detail);
            ViewPager imageViewPager = (ViewPager) detail.findViewById(R.id.imageViewPager);
            imageViewPager.setAdapter(imageAdapter);
            imageViewPager.setCurrentItem(0);


            CirclePageIndicator titleIndicator = (CirclePageIndicator)detail.findViewById(R.id.pageIndicator);
            titleIndicator.setViewPager(imageViewPager);

            ((ViewPager) collection).addView(detail, 0);

            return detail;
        }
        @Override
        public int getCount() {
            return c.getCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == ((LinearLayout) o);
        }

        public void destroyItem(View collection, int position, Object view) {
            Log.d("detail", "remove view @ " + position);
            ((ViewPager) collection).removeView((LinearLayout) view);
        }
    }


    private class ImagePagerAdapter extends android.support.v4.view.PagerAdapter {

        ArrayList<String> imagesUrl;
        ArrayList<ImageView>imageCounterImages;
        LinearLayout detailsPageFrag;
        boolean clicked = false;
        TextView detailsText;

        private ImagePagerAdapter(ArrayList<String> imagesUrl, ArrayList<ImageView> imageCounterImages, LinearLayout detailsPageFrag ){
            this.imagesUrl = imagesUrl;
            this.imageCounterImages = imageCounterImages;
            this.detailsPageFrag = detailsPageFrag;
            detailsText = (TextView) detailsPageFrag.findViewById(R.id.detailsText);


        }
        @Override
        public Object instantiateItem(View collection, int position) {



            LinearLayout detail = (LinearLayout) inflater.inflate(R.layout.images /* date_page2 */, null);

            detail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(!clicked) {
                        Log.d("BLAH", "clicklistener99999999999999999999");
                        detailsText.setVisibility(View.GONE);
                        clicked = true;
                    }
                    else{
                        detailsText.setVisibility(View.VISIBLE);
                        clicked = false;
                    }
                    // Toast.makeText(getActivity(), "clickedImage", Toast.LENGTH_SHORT).show();

                }
            });
            Log.d("pagerAdapter", "after linearlayout inflate");
            // Button button = (Button)detail.findViewById(R.id.button);
            //button.setText(blah + "29038572364578236945234");


//            c = dbh.getAnimalList(db);
//            c.moveToPosition(position);
//            String t = Integer.toString(c.getInt(C_ANIMAL_ID));
//            String d = c.getString(C_ANIMAL_NAME);
//            String p1 = c.getString(C_ANIMAL_PHOTO1);
//            String p2 = c.getString(C_ANIMAL_PHOTO2);
//            String p3 = c.getString(C_ANIMAL_PHOTO3);

            ImageView detailsText = (ImageView)detail.findViewById(R.id.animalImage);
            Picasso.with(getActivity().getApplicationContext()).load(imagesUrl.get(position)).into(detailsText);
            for (int i =0; i<imageCounterImages.size(); i++){
                imageCounterImages.get(i).setImageResource(android.R.drawable.radiobutton_off_background);
            }
            //  imageCounterImages.get(position).setImageResource(android.R.drawable.radiobutton_on_background);

            ((ViewPager) collection).addView(detail, 0);

            return detail;
        }
        @Override
        public int getCount() {
            return imagesUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == ((LinearLayout) o);
        }

        public void destroyItem(View collection, int position, Object view) {
            Log.d("detail", "remove view @ " + position);
            ((ViewPager) collection).removeView((LinearLayout) view);
        }
    }
}








