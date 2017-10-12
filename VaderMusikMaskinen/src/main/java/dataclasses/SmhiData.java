package dataclasses;

/**
 * @author Petter Månsson
 * Dataclass for handeling forecast data from SMHI forecast API
 */

public class SmhiData {

    private String wsymb;
    private String ws;
    private String pcat;



    public SmhiData(String[] weatherValues){
        this.pcat=weatherValues[0];
        this.ws=weatherValues[1];
        this.wsymb=weatherValues[2];

    }

    public String toString(){
        return "WSymbol: " + wsymb + "\n" + "WS: " + ws + "\n" + "DP: " + pcat;
    }

    public String getWs() {
        return ws;
    }


    public String getWsymb() {

        return wsymb;
    }

    public Boolean getDrizzle() {

        if(Integer.parseInt(pcat) == 4) {
            return true;
        }else{
            return false;
        }
    }
}
