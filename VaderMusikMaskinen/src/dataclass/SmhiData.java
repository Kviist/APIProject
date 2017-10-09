package dataclass;

public class SmhiData {

    private WeatherData t;
    private WeatherData ws;
    private WeatherData tstm;
    private WeatherData pcat;



    private SmhiData(WeatherData t, WeatherData ws, WeatherData tstm, WeatherData pcat){
        this.t=t;
        this.ws=ws;
        this.tstm=tstm;
        this.pcat=pcat;
    }

    public String toString(){
        return "C: " + t + "\n" + "WS: " + ws + "\n" + "TP: " + tstm + "\n" + "DP: " + pcat;
    }

    private class WeatherData{

        private String name;
        private String values;

        public WeatherData(String[] weatherData){
            name = weatherData[0];
            values = weatherData[1];

        }

    }
}
