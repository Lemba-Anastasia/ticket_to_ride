package View;

import Controllers.ClavaListener;
import Controllers.Controller;
import ElementsOfGraph.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemba on 13.09.2018.
 */
public class MissioncCreator {
    //private JDialog dialog;
    //private View view;
    private List<String> missionList;
    private Controller controller;

    public MissioncCreator(Controller controller){
        this.controller=controller;
        missionList=new ArrayList<>();
        createMission();
    }

    public JButton createButton(){
        return new JButton(getMissionFromDeck());
    }

    private void createMission(){
        for(Node n1: controller.getNodeList()){
            for(Node n2: controller.getNodeList()){
                if(controller.searchEdgeByNodes(n1,n2)==null && n1!=n2){
                    if(!checkSovpadenijeinList(n1,n2))
                        missionList.add(n1.getName()+" - "+n2.getName());
                }
            }
        }
    }

    private boolean checkSovpadenijeinList(Node n1, Node n2) {//
        for(String s:missionList){
            if((s.split(" - ")[0].equals(n1.getName()) && s.split(" - ")[1].equals(n2.getName()))
                    ||(s.split(" - ")[1].equals(n1.getName()) && s.split(" - ")[0].equals(n2.getName())))
                    return true;
        }
        return false;
    }

    private synchronized String getMissionFromDeck(){
        int i=(int) (Math.random()*missionList.size()-5)+5;
        String missionFromDeck= missionList.get(i);
        missionList.remove(i);
        return missionFromDeck;
    }
}
