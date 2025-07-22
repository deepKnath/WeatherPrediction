export interface Forecast {
  date: string;
  tempHigh: number;
  tempLow: number;
  message: string;
}

export interface WeatherResponse {
  city: string;
  forecast: Forecast[];
  _links?: {
    self: {
      href: string;
    };
  };
}
