package remind.edu.mylibrary.manage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import remind.edu.mylibrary.VigerPDF;
import vudroid.core.DecodeServiceBase;
import vudroid.core.codec.CodecPage;
import vudroid.pdfdroid.codec.PdfContext;


/**
 * Created by Vim on 1/31/2017.
 */

public class RenderingPDF {
    private File file;
    private Context context;
    private int pageCount, type;
    private boolean status = false;
    private CompositeDisposable disposable = new CompositeDisposable();

    public RenderingPDF(Context context, File file, int type) {
        this.file = file;
        this.context = context;
        this.type = type;
        disposable.clear();
        initRun();
    }

    private void initRun() {
        disposable.add(Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                try {
                    DecodeServiceBase decodeService = new DecodeServiceBase(new PdfContext());
                    decodeService.setContentResolver(context.getContentResolver());
                    decodeService.open(Uri.fromFile(file));
                    pageCount = decodeService.getPageCount();
                    for (int i = 0; i < pageCount; i++) {
                        if (status) {
                            e.onComplete();
                        }
                        CodecPage page = decodeService.getPage(i);
                        RectF rectF = new RectF(0, 0, 1, 1);
                        Bitmap bitmap = page.renderBitmap(decodeService.getPageWidth(i), decodeService.getPageHeight(i), rectF);
                        e.onNext(bitmap);
                    }
                    if (type == 1) {
                        file.delete();
                    }
                    e.onComplete();

                } catch (Exception ee) {
                    e.onError(ee);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Bitmap>() {
                    @Override
                    public void onNext(Bitmap bitmap) {
                        VigerPDF.setData(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        VigerPDF.failedData(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void onDestroy() {
        status = true;
    }
}
