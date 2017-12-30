package de.unidue.inf.is.domain;

public class Block {
    private String reason;
    private boolean state;

    public Block(){

    }

    public String getReason() {
        return reason;
    }

    public boolean getBlockState() {
        return state;
    }

    public void setReason(String reason) {
        if(reason == null){
            this.reason="no reason";
        }else {
            this.reason = reason;
        }
    }


    public void setState(boolean state) {
        this.state = state;
    }
}
