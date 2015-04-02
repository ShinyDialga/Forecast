package dialga.shiny.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinyDialga45 on 3/31/2015.
 */
public class TeamUtils {

    public static List<String> getMembers(String team) {
        team = team.toLowerCase();
        List<String> members = new ArrayList<String>();
        int totalMembers = getTotalMembers(team);
        int totalPages = getTotalPages(totalMembers);

        for (int i = 1; i <= totalPages; i++) {
            members.addAll(totalMembers > 20
                    ? getMembers(team, i, 20)
                    : getMembers(team, i, totalMembers));
            totalMembers = totalMembers - 20;
        }
        return members;
    }

    public static int getTotalMembers(String team) {
        int totalMembers = 0;
        try {
            String urlString = "https://oc.tc/teams/" + team;
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            int i = 0;
            int isMapProtoLine = 0;
            while ((inputLine = in.readLine()) != null) {
                i++;
                if (inputLine.equals("Members")) {
                    isMapProtoLine = i + 1;
                }
                if (i == isMapProtoLine) {
                    totalMembers = Integer.parseInt(inputLine.replaceAll("\\<[^>]*>", ""));
                }
            }
        } catch (Exception e) {

        }
        return totalMembers;
    }

    public static int getTotalPages(int totalMembers) {
        return totalMembers % 20 == 0 ? totalMembers/20 : (totalMembers/20) + 1;
    }

    public static List<String> getMembers(String team, int page, int max) {
        max = max > 20 ? 20 : max;
        List<String> members = new ArrayList<String>();
        String pageString = page > 1 ? "?page=" + page : "";
        int currentMember = 0;
        try {
            String urlString = "https://oc.tc/teams/" + team + pageString;
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            int i = 0;
            int isPlayer = 0;
            while ((inputLine = in.readLine()) != null) {
                i++;
                if (inputLine.startsWith("<table")) {
                    isPlayer = i + 12;
                }
                if (i == isPlayer && currentMember < max) {
                    if (inputLine.startsWith(" ") || inputLine.startsWith("<")) {
                        return members;
                    }
                    members.add(inputLine.replaceAll("\\<[^>]*>",""));
                    currentMember++;
                    isPlayer = i + 13;
                }
            }
        } catch (Exception e) {

        }
        return members;
    }

}
