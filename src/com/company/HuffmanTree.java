package com.company;

import java.util.*;

class HuffmanTree {
    private char[] alphabet =
            {'a', 'b', 'c', 'd', 'e',
                    'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o',
                    'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z'};
    private HashMap<Character, Integer> frequencies = new HashMap<>();
    private HashMap<Character, String> map = new HashMap<>();
    private String data;
    private StringBuilder binaryData;
    private StringBuilder stringBuilder = new StringBuilder();

    HuffmanTree(String data) {
        this.data = data;
        binaryData = new StringBuilder();
    }

    String getData() {
        return data;
    }

    StringBuilder getBinaryData() {
        for (int i = 0; i < data.length(); i++) {
            binaryData.append(Integer.toBinaryString(data.charAt(i)));
            binaryData.append(" ");
        }
        return binaryData;
    }

    private int getFrequencies(int positionInAlphabet) {
        if (positionInAlphabet == alphabet.length) {
            return 1;
        }
        char character = (alphabet[positionInAlphabet]);
        if (data.contains(String.valueOf(character))) {
            int counter = 0;
            if (data.indexOf(character) < data.lastIndexOf(character)) {
                String subString = data.substring(data.indexOf(character), data.lastIndexOf(character) + 1);
                for (int i = 0; i < subString.length(); i++) {
                    if (subString.charAt(i) == character) {
                        counter++;
                    }
                }
                frequencies.put(character, counter);
            } else {
                frequencies.put(character, 1);
            }
        }
        return getFrequencies(positionInAlphabet + 1);
    }

    HashMap<Character, Integer> getMap() {
        sortMap();
        return frequencies;
    }

    private void sortMap() {
        getFrequencies(0);
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(frequencies.entrySet());
        list.sort(Map.Entry.comparingByValue());
//        Collections.reverse(list);
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> pair : list) {
            result.put(pair.getKey(), pair.getValue());
        }
        frequencies = result;
    }

    void compressCode(HuffmanNode root, String s) {

        if (root.left == null && root.right == null && Character.isLetter(root.mChar)) {
            map.put(root.mChar, s);
            return;
        }

        assert root.left != null;
        compressCode(root.left, s + "0");
        compressCode(root.right, s + "1");
    }


    StringBuilder getCompressedData(String data) {
        char[] chars = data.toCharArray();
        List<Map.Entry<Character, String>> list = new ArrayList<>(map.entrySet());
        for (char aChar : chars) {
            for (Map.Entry<Character, String> pair : list) {
                if (aChar == pair.getKey()) {
                    stringBuilder.append(pair.getValue());
                }
            }
        }
        return stringBuilder;
    }
}
