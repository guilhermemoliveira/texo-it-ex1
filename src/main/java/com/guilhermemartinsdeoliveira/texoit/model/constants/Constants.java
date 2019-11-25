package com.guilhermemartinsdeoliveira.texoit.model.constants;

public class Constants {
	
	private Constants() {
		
	}

	public static final class CsvCustomConfigurations {
		
		private CsvCustomConfigurations() {
			
		}

		public static final Character COLUMN_SEPARATOR = ';';
		public static final boolean WITH_HEADERS = true;

		public static final String YEAR_COLUMN = "year";
		public static final String TITLE_COLUMN = "title";
		public static final String STUDIOS_COLUMN = "studios";
		public static final String PRODUCERS_COLUMN = "producers";
		public static final String WINNER_COLUMN = "winner";
		public static final String WINNER_COLUMN_VALUE = "yes";

		public static final String REGEX_SEPARATOR = ",|\\sand\\s";

	}
	
	public static final class ProducerRoutes {
		
		public static final String ROUTE_MIN_AND_MAX_INTERVAL_PRODUCERS = "/producers/intervals";
	}

}
