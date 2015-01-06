package com.unknow.cpt.location;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.unknow.cpt.R;

public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;
	private Projection projection;
	
	public MyItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
		projection = mapView.getProjection();
	}

	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}
/*
 * over ride 
 * @see com.unknow.cpt.location.BalloonItemizedOverlay#onBalloonTap(int)
 */
	@Override
	protected boolean onBalloonTap(int index) {
		Toast.makeText(c, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();
		return true;
	}
	/*
	 * draw points method
	 * @see com.google.android.maps.ItemizedOverlay#draw(android.graphics.Canvas, com.google.android.maps.MapView, boolean)
	 */
	@Override
	public void draw(Canvas canvas, MapView arg1, boolean arg2) {
		// TODO Auto-generated method stub
		super.draw(canvas, arg1, arg2);
		Paint mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(android.graphics.Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(4);

        Path path = new Path();
        for(int i=0;i<m_overlays.size(); i++){
        	Point p = new Point();
        	GeoPoint gp = new GeoPoint(m_overlays.get(i).getPoint().getLatitudeE6(), m_overlays.get(i).getPoint().getLongitudeE6());
        	projection.toPixels(gp, p);
        	if(i==0){
        		path.moveTo(p.x, p.y);
        	}
        	else{
        		path.lineTo(p.x, p.y);
        	}
        	
        }
        
        canvas.drawPath(path, mPaint);
	}
	
}
