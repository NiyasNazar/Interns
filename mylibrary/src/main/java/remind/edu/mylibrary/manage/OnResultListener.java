package remind.edu.mylibrary.manage;

import android.graphics.Bitmap;

/**
 * Created by Vim on 1/31/2017.
 */

public interface OnResultListener  {
    void resultData(Bitmap data);
    void progressData(int progress);
    void failed(Throwable t);
}