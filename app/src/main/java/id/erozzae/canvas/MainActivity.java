package id.erozzae.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Canvas mcanvas;
    private Paint mpaint = new Paint(); // mewarnai kotak
    private Paint mpaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;

    private final static int OFFSET = 80;
    private int mOffset = OFFSET;
    private Rect mRect = new Rect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.myImageView);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(), R.color.orangeRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);

        mpaintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black,null));
        mpaintText.setTextSize(70);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vWidth = view.getWidth();
                int vHeight = view.getHeight();
                int halfWidth = vWidth/2;
                int halfHeight = vHeight/2;

                if (mOffset == OFFSET){
                    mBitmap = Bitmap.createBitmap(vWidth, vHeight,Bitmap.Config.ARGB_8888);
                    mImageView.setImageBitmap(mBitmap);
                    mcanvas = new Canvas(mBitmap);
                    mcanvas.drawColor(mColorBackground);
                    mcanvas.drawText("keep Tapping", 100, 100, mpaintText);
                    mOffset += OFFSET;
                }

                else{
                    if (mOffset < halfWidth && mOffset < halfHeight){
                        mpaint.setColor(mColorRectangle - 100*mOffset);
                        mRect.set(mOffset, mOffset, vWidth-mOffset, vHeight-mOffset);
                        mcanvas.drawRect(mRect,mpaint);
                        mOffset += OFFSET;
                    }
                    else{
                        mpaint.setColor(mColorAccent);
                        mcanvas.drawCircle(halfWidth, halfHeight, halfWidth/3, mpaint);

                        String text ="Done";
                        Rect mBounds = new Rect();
                        mpaintText.getTextBounds(text, 0, text.length(), mBounds);

                        int x = mBounds.centerX();
                        int y = mBounds.centerY();

                        mcanvas.drawText(text, halfWidth-x, halfHeight-y, mpaintText);
                    }
                }
                view.invalidate();
            }
        });
    }
}