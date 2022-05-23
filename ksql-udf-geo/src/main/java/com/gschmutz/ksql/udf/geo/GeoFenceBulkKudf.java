package com.gschmutz.ksql.udf.geo;

import java.util.ArrayList;
import java.util.List;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "geo_fence_bulk", description = "Determines if a geo position is inside or outside a geofence.")
public class GeoFenceBulkKudf extends AbstractGeoFenceKudf {

	@Udf(description = "encode lat/long to geohash of specified length.")
	public List<String> geofence(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
								@UdfParameter(value="logitude", description = "longitude in decimal degreees") final double longitude, 
								@UdfParameter(value="idGeometryListWKT", description = "list of ID and geometry WKT") List<String> idGeometryListWKT) {
		List<String> list = new ArrayList<>();

		if (idGeometryListWKT != null) {
			for (String idGeometryWKT : idGeometryListWKT) {
				String id = idGeometryWKT.split(":")[0];
				String geometryWKT = idGeometryWKT.split(":")[1];
				list.add(id + ":" + geofenceImpl(latitude, longitude, geometryWKT));
			}
		}
		return list;
	}

	@Udf(description = "encode lat/long to geohash of specified length.")
	public List<String> geofence(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
								@UdfParameter(value="longitude", description = "longitude in decimal degreees") final double longitude, 
								@UdfParameter(value="geometryListId", description = "list of geometry ID") List<String> geometryListId, 
								@UdfParameter(value="geometryListWKT", description = "list of geometry WKT") List<String> geometryListWKT) {
		List<String> list = new ArrayList<>();

		if (geometryListId != null || geometryListWKT != null) { 
			int i = 0;
			for (String geometryWKT : geometryListWKT) {
				list.add(geometryListId.get(i) + ":" + geofenceImpl(latitude, longitude, geometryWKT));
				i++;
			}
		}
		return list;
	}

	
}
