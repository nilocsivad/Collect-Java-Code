/**
 * 
 */
package com.defence.mp2017.rs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

/**
 * @author Colin
 */
public final class LocationUtil {

	private static final double EARTH_RADIUS = 6378.137;

	/**
	 * 
	 */
	public LocationUtil() {
	}


	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}


	/**
	 * 返回两座标点之间的公里数
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double distance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}



	public static final class Location {

		private int ic;
		private String txt;

		public int getIc() {
			return ic;
		}

		public void setIc(int ic) {
			this.ic = ic;
		}

		public String getTxt() {
			return txt;
		}

		public void setTxt(String txt) {
			this.txt = txt;
		}

	}

	private static final List<Location> LOCATIONS = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public static List<Location> getAllData() {

		if (LOCATIONS.isEmpty()) {

			String path = LocationUtil.class.getClassLoader().getResource("config/location.json").getPath();

			try {

				BufferedReader reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				reader.close();

				List<LinkedTreeMap<String, Object>> list = GsonUtil.fromJson(line, List.class);

				for (LinkedTreeMap<String, Object> l : list) {

					int ic = Integer.parseInt(l.get("ic") + "");

					Location loc = new Location();
					loc.ic = ic;
					loc.txt = l.get("txt") + "";
					LOCATIONS.add(loc);

				}

				System.out.println("==> Load location.json done!");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return LOCATIONS;
	}


	public static List<Location> getData(int ic) {

		int iVal = 100;
		if (ic > 100000) {
			iVal = 1000;
		}

		int start = ic * iVal, stop = start + iVal;


		List<Location> all = LocationUtil.getAllData();


		List<Location> list = new ArrayList<>(iVal);

		for (Location l : all) {

			if (l.ic >= start && l.ic < stop) {
				list.add(l);
			}

		}

		return list;
	}


	public static void main(String[] args) {
		System.out.println(distance(39.845709, 116.370836, 39.846486, 116.370729) * 1000);
	}

}
