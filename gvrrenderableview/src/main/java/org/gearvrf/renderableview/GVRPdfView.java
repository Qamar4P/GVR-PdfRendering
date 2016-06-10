package org.gearvrf.renderableview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.github.barteksc.pdfviewer.PDFView;

import org.gearvrf.GVRActivity;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.scene_objects.view.GVRView;

/**
 * Created by Qamar on 6/10/2016.
 */
public class GVRPdfView extends PDFView implements GVRView {
    private final LinearLayout mViewContainer;
    private GVRViewSceneObject mSceneObject;

    /**
     * Construct the initial view
     *
     * @param context
     * @param set
     */
    public GVRPdfView(GVRActivity context, AttributeSet set) {
        super(context, set);
        mViewContainer = new LinearLayout(context);
        mViewContainer.addView(this);

        mViewContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        context.registerView(mViewContainer);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSceneObject == null)
            return;

        // Canvas attached to GVRViewSceneObject to draw on
        Canvas attachedCanvas = mSceneObject.lockCanvas();
        // Clear the canvas to avoid overlapping text when
        // TextView's background is transparent.
        attachedCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        // draw the view to provided canvas
        super.draw(attachedCanvas);

        mSceneObject.unlockCanvasAndPost(attachedCanvas);
    }

    @Override
    public void setSceneObject(GVRViewSceneObject sceneObject) {
        mSceneObject = sceneObject;
    }

    @Override
    public View getView() {
        return this;
    }
}
