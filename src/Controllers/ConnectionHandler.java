package Controllers;

import javax.swing.*;
import java.io.BufferedReader;

import static java.lang.Integer.parseInt;


public class ConnectionHandler {
    private Client client;
    private Controller controller;

    public ConnectionHandler(Client client) {
        this.client = client;
        controller = client.getController();
    }

    public void startListening() {
        final BufferedReader bufferedReaderFromServer = client.getIn();
        ReaderRunnable readerFromServer = new ReaderRunnable(bufferedReaderFromServer, new ReaderRunnable.Callback() {
            public void onDataReceived(String data) {
                if (!(data.charAt(0) == 'C')) {
                    if (data.substring(0, 7).equals("server:")) {
                        System.out.println(data);
                        client.getView().updaitServerMessages(data);
                    } else if (data.matches("/doActiveInzidentNode: (\\w+)")) {
                        String nameOfNode = data.split("/doActiveInzidentNode: ")[1];
                        controller.doActivaitInzidentNode(nameOfNode);
                    } else if (data.matches("/doUpdaitGraphForActivationNode: (\\w+),(\\w+)")) {
                        System.out.println("DATASUBSTRING:(0,32): " + data.substring(0, 14));
                        String activeNodeIncidentToStartnodeName = data.split("/doUpdaitGraphForActivationNode: ")[1].split(",")[0];
                        String startNodeName = data.split("/doUpdaitGraphForActivationNode: ")[1].split(",")[1];
                        System.out.println("STARTNODENAME: " + startNodeName);
                        System.out.println("activeNodeIncident: " + activeNodeIncidentToStartnodeName);
                        controller.updaitGraphForActivationNode(activeNodeIncidentToStartnodeName, startNodeName);
                    } else if (data.matches("BUDGET:(\\d+),POINTS:(\\d+)")) {
                        controller.setBudget(parseInt(data.split(",")[0].split("BUDGET:")[1]));
                        controller.setPoinsForDoingRoad(parseInt(data.split(",")[1].split("POINTS:")[1]));
                        client.getView().updaitInfoOfGame();
                    } else if (data.substring(0, 17).equals("/doNotActiveNode:")) {
                        String nameOfNode = data.split("/doNotActiveNode: ")[1];
                        client.getController().getNodeByName(nameOfNode).setActive(false);
                    } else if (data.equals("/showFinishMission")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Миссия выполнена!");
                    } else if (data.substring(0, 15).equals("/updaitMission:")) {
                        String mission = data.split("/updaitMission: ")[1];
                        System.out.println("updaitMission: " + mission);
                        client.getView().updateMission(mission);
                    } else if (data.equals("/clearNodesOfIncidentNode")) {
                        System.out.println("CREARNODESOFINZIDENTNODE");
                        controller.clearNodesOfIncidentNode();
                    } else if (data.substring(0, 11).equals("/buildRoad:")) {
                        String startPunkt = data.split("/buildRoad: ")[1].split(",")[0];
                        String punktB = data.split("/buildRoad: ")[1].split(",")[1];
                        System.out.println("/buildRoad" + startPunkt + "," + punktB);
                        controller.buildRailWay(startPunkt, punktB);
                    } else {
                        System.out.println("!!!!!!!!!!!" + data);
                    }
                } else {
                    if (data.matches("C:/doActiveNode: (\\w+)")) {
                        String activNodeNameCompanion=data.split("C:/doActiveNode: ")[1];
                        controller.getNodeByName(activNodeNameCompanion).setActive(true);
                    }else if(data.matches("C:/doNotActiveNode: (\\w+)")){
                        String nodeName=data.split("C:/doNotActiveNode: ")[1];
                        controller.getNodeByName(nodeName).setActive(false);
                    }else if(data.equals("C:/clearNodesOfIncidentNode")){

                    }else if(data.substring(0, 13).equals("C:/buildRoad:")) {
                        String startPunkt = data.split("/buildRoad: ")[1].split(",")[0];
                        String punktB = data.split("/buildRoad: ")[1].split(",")[1];
                        System.out.println("/buildRoad" + startPunkt + "," + punktB);
                        controller.buildRailWay(startPunkt, punktB);
                    }
                }
            }
        });
        Thread thread = new Thread(readerFromServer);
        thread.start();
    }

}
