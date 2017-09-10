
package com.bell.microswitch.test; 

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月31日 上午10:29:51 

 * 类说明 

 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//神奇词典
//用TrieTree 来解决本题
public class TrieTree {
    Word root = new Word('@');//总目录，它的子节点为词典所有单词的首字母
    public static void main(String args[]) {
    	TrieTree m = new TrieTree();
        m.getInput();
    }

    public void getInput() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int N = cin.nextInt();
            for (int i = 0; i < N; i++) {
                String option = cin.next();//操作，用String来保存
                String str = cin.nextLine();//单词，用String来保存
                Order(option, str);
            }
            break;
        }
        cin.close();
    }

    //根据输入，找到对应的命令：insert,delete,search
    public void Order(String opt, String str) {
        char[] charArray = str.toCharArray();//将单词字符串转化为字符数组
        switch (opt) {
        case "insert": {
            insertTrieTree(charArray);
            break;
        }
        case "delete": {
            delTrieTree(charArray);
            break;
        }
        case "search": {
            searchTrieTree(charArray);
            break;
        }
        default: {
//          System.out.println();
            break;
        }
        }
    }

    //删除操作 delete str:删除以str为前缀的“单词”
    public void delTrieTree(char[] cc) {
        int count = 0;
        Word pa = root;
        if (root.ch.containsKey(cc[0])) {
            Word w = root.ch.get(cc[0]);
            int i = 0;
            for (i = 1; i < cc.length; i++) {
                if (w.ch.containsKey(cc[i])) {
                    pa = w;
                    w = w.ch.get(cc[i]);
                } else
                    break;
            }
            if (i == cc.length) {
                count = w.num;
                pa.ch.remove(cc[i-1]);
                w.ch.clear();

                w = root.ch.get(cc[0]);
                for (i = 1; i < cc.length; i++) {
                        w.num -= count;
                        w = w.ch.get(cc[i]);
                }
            }
        }

    }

    //查找
    public void searchTrieTree(char[] cc) {
        if (root.ch.containsKey(cc[0])) {
            Word w = root.ch.get(cc[0]);
            int i = 0;
            for (i = 1; i < cc.length; i++) {
                if (w.ch.containsKey(cc[i])) {
                    w = w.ch.get(cc[i]);
                } else
                    break;
            }
            if (i == cc.length && w.num > 0)
                System.out.println("Yes");
            else
                System.out.println("No");
        } else
            System.out.println("No");
    }

    //插入
    public void insertTrieTree(char[] cc) {
        if (!root.ch.containsKey(cc[0])) {
            root.ch.put(cc[0], new Word(cc[0]));
        }
        root.ch.get(cc[0]).num++;
        Word w = root.ch.get(cc[0]);
        for (int i = 1; i < cc.length; i++) {
            if (!w.ch.containsKey(cc[i])) {
                w.ch.put(cc[i], new Word(cc[i]));
            }
            w = w.ch.get(cc[i]);
            w.num++;
        }
    }
}

class Word {
    char c;
    int num = 0;
    public Word(char c) {
        this.c = c;
    }
    Map<Character, Word> ch = new HashMap<Character, Word>();
}