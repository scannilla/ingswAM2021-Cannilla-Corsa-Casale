package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Locale;

public class RequiredClientActions {
    private final Command command;
    private final Socket clientSocket;
    private final Player player;

    public RequiredClientActions(Command command, Socket clientSocket, Player player) {
        this.command = command;
        this.clientSocket = clientSocket;
        this.player = player;
    }

    public void execute(String cmd) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String[] parameters = command.getParameters();
            switch(cmd) {
                case "buy":
                    Resource[] costArray = player.getConnectedGame().getCardsMarket().getCard(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])).getCostArray();
                    out.println("you can buy the selected card, please choose the resources to use");
                    do {
                        String chosenResource = in.readLine();

                        switch(chosenResource.split(" ")[1].toLowerCase()) {
                            case "warehousedepot":
                                useWarehouseResource(out, costArray, chosenResource);
                                break;

                            case "extradepot":
                                Resource readResource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                                LeaderOfDepots card;
                                boolean found=false;
                                for (int i=0; i<2; i++){
                                    if (player.getActiveLeaderCards()[i].getAbility() == 1){
                                        card = ((LeaderOfDepots)player.getActiveLeaderCards()[i]);
                                        if(readResource.equals(card.getResource()))
                                            found = true;
                                    }
                                }
                                if (!found) {
                                    out.println("You don't have an extra depot for the selected resource");
                                    break;
                                }
                                for (Resource res : costArray) {
                                    if (res.equals(readResource)) {
                                        for (LeaderOfDepots leaderCard : ((LeaderOfDepots[])player.getActiveLeaderCards())) {
                                            if (leaderCard.getResource().equals(res)) {
                                                try {
                                                    leaderCard.useResource(res);
                                                    res = null;
                                                } catch (IllegalArgumentException e) {
                                                    out.println("You don't have enough resources of the selected type in your extra depot");
                                                }
                                            }
                                        }
                                    }
                                }
                                break;

                            case "strongbox":
                                Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                                for (Resource res : costArray) {
                                    if (res.equals(resource)) {
                                        try {
                                            player.getPersonalBoard().getStrongbox().useResourceStrongbox(res);
                                            res = null;
                                        } catch (IllegalArgumentException e) {
                                            out.println("This resource isn't available in your Strongbox");
                                        }
                                        break; //break for each
                                    }
                                    out.println("This resource isn't required");
                                }
                                break; //break case "strongbox"
                            default:
                                out.println("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox");
                                break;
                        }
                    }
                    while (Arrays.equals(costArray, new Resource[costArray.length]));
                    int position;
                    do {
                        out.println("Select a position to insert the card in");
                        position = Integer.parseInt(in.readLine());
                    }
                    while (position<1 || position > 3);
                    player.buyProductionCard(Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]), position);
                    break;

                case "production":
                    out.println("You can activate this production, select where you want to take the resources from");
                    Resource[] requiredRes = player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getRequiredRes();
                    do {
                        String chosenResource = in.readLine();
                        switch (chosenResource.split(" ")[1].toLowerCase()) {
                            case "warehousedepot":
                                useWarehouseResource(out, requiredRes, chosenResource);
                                break;

                            case "extradepot":
                                Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase());
                                boolean done=false;

                                for (Resource test : requiredRes) {
                                    if (resExtra.equals(test)) {
                                        if (player.getActiveLeaderCards()[0].getAbility() == 1) {
                                            if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getResource().equals(test)) {
                                                ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(test);
                                                test = null;
                                                done = true;
                                            }
                                                //TODO
                                        }
                                        if (player.getActiveLeaderCards()[1].getAbility() == 1 && !done) {
                                            if(((LeaderOfDepots)player.getActiveLeaderCards()[1]).getResource().equals(test)) {
                                                ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(test);
                                                test = null;
                                            }
                                        }
                                    }
                                }

                                break;

                            default:
                                out.println("Select a valid resource location (Warehouse Depot or Extra Depot)");
                                break;
                        }


                    } while(Arrays.equals(requiredRes, new Resource[requiredRes.length]));

                    break;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void useWarehouseResource(PrintWriter out, Resource[] requiredRes, String chosenResource) {
        try {
            Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
            for (Resource res : requiredRes) {
                if (res.equals(resource)) {
                    try {
                        player.getPersonalBoard().getWarehouseDepot().useResource(res);
                        res = null;
                    } catch (IllegalArgumentException e) {
                        out.println("This resource isn't available in your warehouse depot");
                    }
                    break;
                }
                out.println("This resource isn't required");
            }

        } catch (IllegalArgumentException e) {
            out.println("Select a valid resource (Coin, Stone, Servant or Shield)");
        }
    }

    /**
     * this method checks if the selected resource is valid and creates a new resource
     * @param resource String
     * @return new Resource(resource) Resource
     */
    private Resource checkResource(String resource) {
        Resource res;
        if(resource.equals("coin") || resource.equals("stone") || resource.equals("servant") || resource.equals("shield") )
            return new Resource(resource);
        else
            throw new IllegalArgumentException();
    }
}
