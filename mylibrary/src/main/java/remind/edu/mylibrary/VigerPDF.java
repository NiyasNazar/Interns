package remind.edu.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;



import java.io.File;

import remind.edu.mylibrary.manage.OnResultListener;
import remind.edu.mylibrary.manage.RenderingPDF;
import remind.edu.mylibrary.manage.RenderingPDFNetwork;

/**
 * Created by Vim on 1/31/2017.
 */

public class VigerPDF {
    static OnResultListener onResultListener;
    static RenderingPDF renderingPDF;
    static RenderingPDFNetwork renderingPDFNetwork;
    private Context context;

    public VigerPDF(Context context) {
        this.context = context;
    }

    public static void cancle() {
        if (renderingPDF != null) {
            renderingPDF.onDestroy();
        }
        if (renderingPDFNetwork != null) {
            renderingPDFNetwork.onDestory();
        }
    }

    public static Bitmap setData(Bitmap itemData) {
        onResultListener.resultData(itemData);
        return itemData;
    }

    public static Throwable failedData(Throwable t) {
        onResultListener.failed(t);
        return t;
    }

    public static int progressData(int progress) {
        onResultListener.progressData(progress);
        return progress;
    }

    public void initFromNetwork(String endpoint, OnResultListener resultListener) {
        onResultListener = resultListener;
        renderingPDFNetwork = new RenderingPDFNetwork(context, endpoint);
    }

    public void initFromFile(File file, OnResultListener resultListener) {
        onResultListener = resultListener;
        renderingPDF = new RenderingPDF(context, file, 0);
    }
}