package dataclass;

public class SmhiData {

    private String t;
    private String ws;
    private String tstm;
    private String pcat;



    public SmhiData(String t, String ws, String tstm, String pcat){
        this.t=t;
        this.ws=ws;
        this.tstm=tstm;
        this.pcat=pcat;
    }

    public String toString(){
        return "C: " + t + "\n" + "WS: " + ws + "\n" + "TP: " + tstm + "\n" + "DP: " + pcat;
    }
}
