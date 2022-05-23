package com.gschmutz.ksql.udf.geo;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "geo_fence", description = "Determines if a geo position is inside or outside a geofence.")
public class GeoFenceKudf extends AbstractGeoFenceKudf {
		
	
	@Udf(description = "determines if a lat/long is inside or outside the geometry passed as the third parameter as a WKT coded string. "
			+ "Returns either INSIDE or OUSIDE.")
	public String geofence(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
							@UdfParameter(value="longitude", description = "longitude in decimal degreees") final double longitude, 
							@UdfParameter(value="geometryWKT", description = "geometry in WKT format") String geometryWKT) {
		return geofenceImpl(latitude, longitude, geometryWKT);
	}

		
	@Udf(description = "encode lat/long to geohash of specified length.")
	public String geofence(@UdfParameter(value="latitude", description = "latitude in decimal degreees") final double latitude, 
							@UdfParameter(value="longitude", description = "longitude in decimal degreees") final double longitude, 
							@UdfParameter(value="statusBefore", description = "status before") final String statusBefore, 
							@UdfParameter(value="geometryWKT", description = "geometry in WKT format") String geometryWKT) {

		String status = geofenceImpl(latitude, longitude, geometryWKT);
		if (statusBefore.equals("INSIDE") && status.equals("OUTSIDE")) {
			status = "LEAVING";
		} else if (statusBefore.equals("OUTSIDE") && status.equals("INSIDE")) {
			status = "ENTERING";
		}
		
		return status;
	}

	@Udf(description = "encode lat/long to geohash of specified length.")
	public String geofence(@UdfParameter(value="statusBefore", description = "status before") final String statusBefore,
						   @UdfParameter(value="statusNow", description = "geometry in WKT format") String statusNow) {

		String status = statusNow;
		if (statusBefore.equals("INSIDE") && statusNow.equals("OUTSIDE")) {
			status = "LEAVING";
		} else if (statusBefore.equals("OUTSIDE") && statusNow.equals("INSIDE")) {
			status = "ENTERING";
		}

		return status;
	}
}
