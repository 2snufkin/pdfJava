package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> libelle = new HashMap<Integer, String>();
        libelle.put(1, "Protocle");
        libelle.put(2, "Ex");
        ArrayList<String> files = new ArrayList<String>();
        files.add("blablapdf.pdf");
        files.add("22.pdf");

        Map<String, ArrayList<String>> mapOfFiles = new HashMap<>();
        int typeId = 1;
        String typeLibelle = libelle.get(typeId);
        //check if the key exist, if so add the file to the value -it's a list
        if (mapOfFiles.containsKey(typeLibelle)){
            ArrayList<String> exisiting  = mapOfFiles.get(typeLibelle);
            exisiting.add("secondFILE");
            mapOfFiles.put(typeLibelle, exisiting);
        }
        else{
            mapOfFiles.put(typeLibelle, files);

        }

        System.out.println(mapOfFiles);

    }


}
