package dataclasses;

public class SmhiData {

    private String t;
    private String ws;
    private String tstm;
    private String pcat;



    public SmhiData(String[] weatherValues){
        this.pcat=weatherValues[0];
        this.t=weatherValues[1];
        this.ws=weatherValues[2];
        this.tstm=weatherValues[3];

//        System.out.println(this.toString());
    }

    public String toString(){
        return "C: " + t + "\n" + "WS: " + ws + "\n" + "TP: " + tstm + "\n" + "DP: " + pcat;
    }

    public String getWs() {
        return ws;
    }

    public String getTstm() {

        return tstm;
    }


    public String getT() {

        return t;
    }

    public String getPcat() {
        return pcat;
    }
}
