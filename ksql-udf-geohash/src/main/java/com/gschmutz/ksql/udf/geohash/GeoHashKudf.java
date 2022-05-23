package com.gschmutz.ksql.udf.geohash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import com.github.davidmoten.geo.Coverage;
import com.github.davidmoten.geo.Direction;
import com.github.davidmoten.geo.GeoHash;
import com.github.davidmoten.geo.LatLong;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "geo_hash", description = "returns the geohash of a coordinate")
public class GeoHashKudf {
	private GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	private WKTReader reader = new WKTReader(geometryFactory);
	
	@Udf(description = "Returns the adjacent hash in given Direction. Direction should be one of TOP, BOTTOM, LEFT, RIGHT")
	public String adjacentHash(@UdfParameter(value="geohash", description = "the geohash to create adjacent geeohash for") String geohash, 
							@UdfParameter(value="directionString", description = "the direction") String directionString) {
		Direction direction = Direction.valueOf(directionString.toUpperCase());
		if (direction == null) {
			throw new RuntimeException("Invalid direction parameter, needs to be either TOP, BOTTON, RIGHT or LEFT");
		}
		return GeoHash.adjacentHash(geohash, direction);
	}
	
	@Udf(description = "Returns a list of the 8 surrounding hashes for a given hash in order " + 
			           "left,right,top,bottom,left-top,left-bottom,right-top,right-bottom")
	public List<String> neighbours(@UdfParameter(value="geohash", description = "the geohash to create neighbouring geohashes for") String geohash) {
		return GeoHash.neighbours(geohash);
	}

	@Udf(description = "encode lat/long to geohash of specified length.")
	public String geohash(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
			@UdfParameter(value="longitude", description = "longitude in decimal degreees") final double longitude, 
			@UdfParameter(value="length", description = "length of hash") int length) {
		return GeoHash.encodeHash(latitude, longitude, length);
	}

	@Udf(description = "encode lat/long to geohash.")
	public String geohash(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
						@UdfParameter(value="longitude", description = "longitude in decimal degreees") final double longitude) {
		return GeoHash.encodeHash(latitude, longitude);
	}
/*
	@Udf(description = "Returns a latitude,longitude pair as the centre of the given geohash. " + 
				"Latitude will be between -90 and 90 and longitude between -180 and 180.")
	public Map<String, Double> decodeHash(@UdfParameter(value="geohash", description = "the geohash to create adjacent geeohash for") String geohash){
		Map<String, Double> latLongMap = new HashMap<>();
		
		LatLong latLong = GeoHash.decodeHash(geohash);
		latLongMap.put("longitude", latLong.getLat());
		latLongMap.put("latitude", latLong.getLon());
		
		return latLongMap;
	}
*/
	@Udf(description = "Returns the maximum length of hash that covers the bounding box. If no " + 
					"hash can enclose the bounding box then 0 is returned.")
	public int hashLengthToCoverBoundingBox(@UdfParameter(value="topLeftLatitude", description = "latitude of top left corner in decimal degreees") double topLeftLatitude, 
											@UdfParameter(value="topLeftLongitude", description = "longitude of top left corner in decimal degreees") double topLeftLongitude, 
											@UdfParameter(value="bottomRightLatitude", description = "latitude of bottom right corner in decimal degreees") double bottomRightLatitude, 
											@UdfParameter(value="bottomRightLongitude", description = "longitude of bottom right corner in decimal degreees") double bottomRightLongitude) {
		return GeoHash.hashLengthToCoverBoundingBox(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude);
	}
	
	
	@Udf(description = "Returns true if and only if the bounding box corresponding to the hash" + 
					" contains the given latitude and longitude.")
	public boolean hashContains (@UdfParameter(value="geohash", description = "the geohash")  String geoHash, 
								@UdfParameter(value="latitude", description = "latitude in decimal degreees") double latitude, 
								@UdfParameter(value="longitude", description = "longitude in decimal degreees") double longitude) {
		return GeoHash.hashContains(geoHash, latitude, longitude);
	}
	
	@Udf(description = "Encode geohases which cover bounding box around geometrys.")
	public List<String> coverBoundingBox(@UdfParameter(value="geometryWKT", description = "the geometry in WKT")  String geometryWKT, 
										@UdfParameter(value="length", description = "length of hash")  int length) {
		List<String> list = new ArrayList<>();
		Geometry geometry;
		try {
			geometry = reader.read(geometryWKT);

			// returns the bounding box
			Envelope e = geometry.getEnvelopeInternal();
			Coverage c = GeoHash.coverBoundingBox(e.getMaxY(), e.getMinX(), e.getMinY(), e.getMaxX(), length);
			list = new ArrayList<String>(c.getHashes());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
