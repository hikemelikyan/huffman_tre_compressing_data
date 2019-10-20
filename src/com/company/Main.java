package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        String newString = user.next();

        HuffmanTree tree = new HuffmanTree(newString);


        System.out.println("This is our string  ->" + tree.getData());
        System.out.println("This is our string before compression ->" + tree.getBinaryData());
        System.out.println(tree.getData().length() + " bit");
        System.out.println("This is our map ->" + tree.getMap());


        List<Map.Entry<Character, Integer>> list = new ArrayList<>(tree.getMap().entrySet());
        int n = list.size();
        char[] charArray = new char[n];
        int[] charFreq = new int[n];

        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i).getKey();
            charFreq[i] = list.get(i).getValue();
        }

        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>(n, new HuffmanComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.mChar = charArray[i];
            hn.mData = charFreq[i];
            hn.left = null;
            hn.right = null;
            nodes.add(hn);
        }

        HuffmanNode root = null;

        while (nodes.size() > 1) {
            HuffmanNode x = nodes.peek();
            nodes.poll();

            HuffmanNode y = nodes.peek();
            nodes.poll();

            HuffmanNode node = new HuffmanNode();

            assert y != null;
            node.mData = x.mData + y.mData;
            node.mChar = '-';
            node.left = x;
            node.right = y;
            root = node;
            nodes.add(node);
        }

        assert root != null;
        tree.compressCode(root, "");

        StringBuilder compressed = tree.getCompressedData(tree.getData());
        System.out.println(compressed);
        if (compressed.length() % 8 == 0) {
            System.out.println(((compressed.length() / 8)) + " bit");
        } else {
            System.out.println(((compressed.length() / 8) + 1) + " bit");
        }
        System.out.println();
    }
}
