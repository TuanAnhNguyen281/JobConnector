/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuananh
 */
public class ApplyWay {
    int wayID;
    String wayTitle;

    public ApplyWay(int wayID, String wayTitle) {
        this.wayID = wayID;
        this.wayTitle = wayTitle;
    }
    
    

    public int getWayID() {
        return wayID;
    }

    public void setWayID(int wayID) {
        this.wayID = wayID;
    }

    public String getWayTitle() {
        return wayTitle;
    }

    public void setWayTitle(String wayTitle) {
        this.wayTitle = wayTitle;
    }
    
}
