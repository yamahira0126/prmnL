package com.example.license.data;

import java.io.Serializable;

public class Software implements Serializable {

    private final Integer softwareId;
    private final String softwareName;
    private final String softwareType;
    private final String totalNumber;
    private final String softwareRemarks;
    //private final boolean exist;

    public Software(Integer softwareId,String softwareName,String softwareType,String totalNumber,String softwareRemarks){
        this.softwareId = softwareId;
        this.softwareName = softwareName;
        this.softwareType = softwareType;
        this.totalNumber = totalNumber;
        this.softwareRemarks = softwareRemarks;
        //this.exist = exist;
    }

    public Integer getSoftwareId() {
        return softwareId;
    }
    public  String getSoftwareName(){
        return softwareName;
    }
    public String getSoftwareType(){
        return softwareType;
    }
    public  String getTotalNumber(){
        return totalNumber;
    }
    public String getSoftwareRemarks(){
        return softwareRemarks;
    }
    //public boolean getExist(){ return exist;}
}
