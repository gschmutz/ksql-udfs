package com.gschmutz.ksql.udf.geo;

import java.util.HashMap;
import java.util.Map;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "geo_centroid", description = "Computes the centroid of this Geometry.")
public class GeoCentroidKudf {

	private GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	private WKTReader reader = new WKTReader(geometryFactory);
	private WKTWriter writer = new WKTWriter();
	
	//@Udf(description = "determines if a lat/long is inside or outside the geometry passed as the third parameter as a WKT coded string.")
	public String centroidWKT(String geometryWKT) {
		String pointWKT = "";
		
		try {
			Geometry geometry = reader.read(geometryWKT);
			Point point = geometry.getCentroid();
			pointWKT = writer.write(point);
			
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
		return pointWKT;
	}
	
	@Udf(description = "determines if a lat/long is inside or outside the geometry passed as the third parameter as a WKT coded string.")
	public Map<String, Double> centroid(@UdfParameter(value="geometryWKT", description = "the geofence in WKT format") String geometryWKT) {
		Map<String, Double> pointMap = new HashMap<>();

		try {
			Geometry geometry = reader.read(geometryWKT);
			Point point = geometry.getCentroid();
			pointMap.put("latitude", point.getY());
			pointMap.put("longitude", point.getX());
			
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
		return pointMap;
		
	}
	
}
