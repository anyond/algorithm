package com.tree;

import java.util.*;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-17 19:56
 **/
public class HfmTree {

    HfmNode root;//根节点
    List<HfmNode> leafs;// 叶子节点
    Map<Character, Integer> weights;//权重
    Map<Character, String> decodes;//生产的编码表
    Map<String, Character> encodes;//生产的解码表
    int maxCodeSize;//最大编码的长度

    //初始化，输入权重map，直接生成树和编码表
    public HfmTree(Map<Character, Integer> weights) {
        this.weights = weights;
        this.leafs = new ArrayList<>();
        create();
        this.decodes = new HashMap<>();
        this.encodes = new HashMap<>();
        code();
    }

    //创建树
    public HfmNode create() {
        // 获取weights的所有的key
        Character[] keys = weights.keySet().toArray(new Character[0]);
        // 定义优先队列，HfmNode必须实现Comparable<T>的compartTo接口方法，
        // 优先队列会根据compartTo方法，将“小”值放在前面，实现自动排序
        Queue<HfmNode> queue = new PriorityQueue<>();
        for (Character c : keys) {
            HfmNode node = new HfmNode(c.toString(), weights.get(c));
            queue.add(node);
            // 将所有叶子节点放入leafs,用于构建编码表，由于树从叶子节点开始构建，所以没办法再构建过程中完成编码表，
            // 所以这里先记录，最后再循环构建编码表
            leafs.add(node);
        }
        // 只有队列中还有超过两个node，就说明没构建完成，构建完成时，队列只会留下一个根节点
        while (queue.size() > 1) {
            // 每次取最前面，也就是最小的两个node
            HfmNode n1 = queue.poll();
            HfmNode n2 = queue.poll();

            // 将当前最小的两个node合并成一个新node并放入优先队列
            HfmNode newNode = new HfmNode(n1.data + n2.data, n1.fre + n2.fre);
            newNode.left = n1.fre < n2.fre ? n1 : n2;
            newNode.right = n1.fre > n2.fre ? n1 : n2;
            n1.parent = newNode;
            n2.parent = newNode;

            queue.add(newNode);
        }
        // 返回根节点
        root = queue.poll();
        return root;
    }

    // 生成编码表
    public void code() {
        // 循环所有叶子节点
        for (int i = 0; i < leafs.size(); i++) {
            HfmNode curr = leafs.get(i);
            // 获取每个节点的第一个字符，因为每个字符都需要构建一个叶子节点，所以叶子节点只有一位
            Character c = new Character(curr.data.charAt(0));
            StringBuffer sb = new StringBuffer();
            // 如果叶子节点有parent，则继续循环，从下向上获取到字符的完整路径，也就是编码值
            while (curr.parent != null) {
                // 判断当前节点是父节点的左节点还是右节点，左节点拼接“0”，右节点拼接“1”
                if (curr.parent.left == curr) {
                    sb.insert(0, "0");
                } else {
                    sb.insert(0, "1");
                }
                curr = curr.parent;
            }
            // 最后将节点值和编码值存入map中
            decodes.put(c, sb.toString());
            encodes.put(sb.toString(), c);
            System.out.println(c + ":" + sb.toString());
            // 更新最大编码长度
            maxCodeSize = Math.max(maxCodeSize, sb.length());
            sb.setLength(0);
        }
    }

    // 加密
    public String decode(String str) {
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            sb.append(decodes.get(c));
        }
        return sb.toString();
    }

    // 解密
    public String encode(String str) {
        char[] chars = str.toCharArray();
        StringBuffer res = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        // 循环拼接字符数组，如果有匹配的解码值，则拼接在结果中，并将sb清空，重新拼接
        for (char c : chars) {
            sb.append(c);
            if (encodes.containsKey(sb.toString())) {
                res.append(encodes.get(sb.toString()));
                sb.setLength(0);
            }
            // 当拼接的编码值已经超出此哈夫曼树的最大编码值，则直接返回
            if (sb.length() == maxCodeSize) {
                System.out.println("解码失败");
                return null;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Map<Character, Integer> weights = new HashMap<Character, Integer>();
        weights.put('a', 3);
        weights.put('b', 24);
        weights.put('c', 6);
        weights.put('d', 1);
        weights.put('e', 34);
        weights.put('f', 4);
        weights.put('g', 12);

        HfmTree hfmTree = new HfmTree(weights);
        Map<Character, String> codes = hfmTree.decodes;
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("---------------------开始编码---------------------");
        String str = "cddefa";
        String decodeStr = hfmTree.decode(str);
        System.out.println(decodeStr);

        System.out.println("---------------------开始解码---------------------");
        System.out.println(hfmTree.encode(decodeStr));

    }
}

class HfmNode implements Comparable<HfmNode>{
    public String data; //节点的字符值，如果定义为char，则过程节点无法显示合并后的值
    public int fre; //权重
    public HfmNode left;
    public HfmNode right;
    public HfmNode parent;

    public HfmNode(String data, int fre) {
        this.data = data;
        this.fre = fre;
    }

//    这里需要实现Comparable<T>接口以放入优先队列PriorityQueue
    @Override
    public int compareTo(HfmNode o) {
        return this.fre - o.fre;
    }
}
