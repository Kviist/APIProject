package dataclasses;

import java.util.HashMap;

/**
 * @author Petter Månsson
 * Dataclass for handeling forecast data from SMHI forecast API
 */

public class SmhiData {

    private String wsymb;
    private String ws;
    private String pcat;



    public SmhiData(HashMap weatherValues){
        this.pcat=weatherValues.get("pcat").toString();
        this.ws=weatherValues.get("ws").toString();
        this.wsymb=weatherValues.get("Wsymb2").toString();

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
