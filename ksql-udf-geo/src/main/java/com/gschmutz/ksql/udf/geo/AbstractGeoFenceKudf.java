package com.gschmutz.ksql.udf.geo;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public abstract class AbstractGeoFenceKudf {

	private static final String OUTSIDE = "OUTSIDE";
	private static final String INSIDE = "INSIDE";
	private GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	private WKTReader wktReader = new WKTReader(geometryFactory);
	
	protected GeometryFactory getGeometryFactory() {
		return geometryFactory;
	}
	
	protected WKTReader getWKTReader() {
		return wktReader;
	}

	public String geofenceImpl(final double latitude, final double longitude, String geometryWKT) {
		String status = "";
		
		Polygon polygon = null;
		try {
			polygon = (Polygon) wktReader.read(geometryWKT);
	
			// However, an important point to note is that the longitude is the X value 
			// and the latitude the Y value. So we say "lat/long", 
			// but JTS will expect it in the order "long/lat". 
			Coordinate coord = new Coordinate(longitude, latitude);
			Point point = geometryFactory.createPoint(coord);
			
			if (point.within(polygon)) {
				status = INSIDE;
			} else {
				status = OUTSIDE;
			}
				
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
		return status;
	}

}