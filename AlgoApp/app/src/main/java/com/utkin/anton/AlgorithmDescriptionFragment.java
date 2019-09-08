package com.utkin.anton;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.utkin.anton.AlgorithmDescriptionActivity.EXTRA_ALGO_ID;


public class AlgorithmDescriptionFragment extends Fragment {

    private static final String TAG = "AlgorithmDescriptionFragment";
    private static final int NEGATIVE_VELOCITY_THRESHOLD = -1000;
    private static final int POSITIVE_VELOCITY_THRESHOLD = 1000;
    private ImageView mImageViewPdf;
    private int mPageIndex;
    private PdfRenderer mPdfRenderer;
    private PdfRenderer.Page mCurrentPage;
    private ParcelFileDescriptor mParcelFileDescriptor;
    private static String mFileName;

    public static AlgorithmDescriptionFragment createInstance(Bundle args) {
        AlgorithmDescriptionFragment fragment = new AlgorithmDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageIndex = 0;
        AlgorithmItem item = AlgoList.getItemByTitle(getArguments().getString(EXTRA_ALGO_ID));
        if(item != null) {
            mFileName = item.getFileName();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.algorithm_description, container, false);
        mImageViewPdf = view.findViewById(R.id.pdf_image);
        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        if(velocityY < NEGATIVE_VELOCITY_THRESHOLD){
                            onNextDocClicked();
                        }
                        else if(velocityY > POSITIVE_VELOCITY_THRESHOLD){
                            onPreviousDocClicked();
                        }
                        return true;
                    }

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }
                });

        mImageViewPdf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart() {
        super.onStart();
        try {
            openRenderer(getActivity());
            showPage(mPageIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
        try {
            closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onPreviousDocClicked(){
        Log.d(TAG, "onPreviousDocClicked");
        showPage(mCurrentPage.getIndex() - 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onNextDocClicked(){
        Log.d(TAG, "onNextDocClicked");
        showPage(mCurrentPage.getIndex() + 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer(Context context) throws IOException {
        Log.d(TAG, "openRenderer " + mFileName);
        File file = new File(context.getCacheDir(), mFileName);
        if(file.exists()){
            file.delete();
        }
        InputStream asset = context.getAssets().open(mFileName);
        FileOutputStream output = new FileOutputStream(file);
        final byte[] buffer = new byte[1024];
        int size;
        while ((size = asset.read(buffer)) != -1) {
            output.write(buffer, 0, size);
        }
        asset.close();
        output.close();
        mParcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (mParcelFileDescriptor != null) {
            mPdfRenderer = new PdfRenderer(mParcelFileDescriptor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeRenderer() throws IOException {
        if (null != mCurrentPage) {
            mCurrentPage.close();
            mCurrentPage = null;
        }
        mPdfRenderer.close();
        mParcelFileDescriptor.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showPage(int index) {
        Log.i(TAG, "showPage index=" + index + "pageCount=" + mPdfRenderer.getPageCount());
        if(index >= 0 && index < mPdfRenderer.getPageCount()) {
            if (null != mCurrentPage) {
                mCurrentPage.close();
            }
            mCurrentPage = mPdfRenderer.openPage(index);
            Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                    Bitmap.Config.ARGB_8888);
            // Here, we render the page onto the Bitmap.
            // To render a portion of the page, use the second and third parameter. Pass nulls to get
            // the default result.
            // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
            mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            // We are ready to show the Bitmap to user.
            mImageViewPdf.setImageBitmap(bitmap);
            mPageIndex = mCurrentPage.getIndex();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getPageCount() {
        return mPdfRenderer.getPageCount();
    }
}