package com.tree;

import java.util.Scanner;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-14 18:41
 **/
public class BinarySearchTree {

    public static void main(String[] args) {
/*        BinaryNode root = null;
        Scanner scanner = new Scanner(System.in);
        int t = 1;
        System.out.println("请输入根节点:");
        int rootData = scanner.nextInt();
        root = new BinaryNode(rootData);
        System.out.println("请输入第" + t + "个点:输入-1表示结束");
        while (true) {
            int data = scanner.nextInt();
            if (data == -1) {
                break;
            }
            insert(root, data);
            t++;
            System.out.println("请输入第" + t + "个点:输入-1表示结束");
        }
        show(root);*/

        BinaryNode root = new BinaryNode(4);
        insert(root, 2);
        insert(root, 6);
        insert(root, 1);
        insert(root, 3);
        insert(root, 5);
        insert(root, 7);
        show(root);
        System.out.println("删除节点1");
        show(remove(root, 1));
        System.out.println("删除节点2");
        show(remove(root, 2));
        System.out.println("删除节点6");
        show(remove(root, 6));

/*        System.out.println("删除测试:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要删除的点：-1表示结束");
            int key = scanner.nextInt();
            if (key == -1) {
                break;
            }
            if (root == null) {
                System.out.println("树已经没有数据了~~");
                break;
            }
            show(remove(root, key));
        }*/
    }

    //中序遍历
    public static void inOrder(BinaryNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    //查找
    public static BinaryNode find(BinaryNode root, int data) {
        BinaryNode curr = root;
        while (curr != null) {
            if (curr.data > data) {
                curr = curr.left;
            } else if (curr.data < data) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    //插入
    public static void insert(BinaryNode root, int data) {
        if (root.data > data) {
            if (root.left == null) {
                root.left = new BinaryNode(data);
                root.left.parent = root;
            } else {
                insert(root.left, data);
            }
        } else {
            if (root.right == null) {
                root.right = new BinaryNode(data);
                root.right.parent = root;
            } else {
                insert(root.right, data);
            }
        }
    }

    //删除节点
    public static BinaryNode remove(BinaryNode root, int data) {
        BinaryNode delNode = find(root, data);
        if (delNode == null) {
            System.out.println("要删除的值不在树中");
            return root;
        }

        // 1、删除的点没有左右子树
        if (delNode.left == null && delNode.right == null) {
            if (delNode == root) {
                root = null;
            } else if (delNode.data < delNode.parent.data) {
                delNode.parent.left = null;
                delNode.parent = null;
            } else {
                delNode.parent.right = null;
                delNode.parent = null;
            }
        //2、删除的点有左右两个子树
        } else if (delNode.left != null && delNode.right != null) {
            BinaryNode successor = findSuccessor(delNode);
            int successorData = successor.data;
            remove(root, successorData);
            delNode.data = successorData;
        //3、删除的点有一个子树
        } else {
            if (delNode.left != null) {
                if (delNode == root) {
                    root = delNode.left;
                    root.parent = null;
                    delNode.left = null;
                    return root;
                }
                if (delNode.data < delNode.parent.data) {
                    delNode.parent.left = delNode.left;
                } else {
                    delNode.parent.right = delNode.left;
                }
                delNode.parent = null;
            } else {
                if (delNode == root) {
                    root = delNode.right;
                    root.parent = null;
                    delNode.right = null;
                    return root;
                }
                if (delNode.data < delNode.parent.data) {
                    delNode.parent.left = delNode.right;
                } else {
                    delNode.parent.right = delNode.right;
                }
                delNode.parent = null;
            }
        }
        return root;
    }

    //寻找后继节点
    public static BinaryNode findSuccessor(BinaryNode node) {
        if (node.right == null) {
            return node;
        }
        BinaryNode curr = node.right;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    //打印树
    public static void show(BinaryNode root) {
        if (root == null) {
            System.out.println("EMPTY!");
            return ;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                //如果line[i].length() > 1，说明不是空格，是节点的值
                if (line[i].length() > 1 && i <= line.length - 1) {
                    //节点值位数大于4，则至少打印一个空格和下一个节点值隔开，否则按照位数跳过空格的打印
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    //将树写入数组
    private static void writeArray(BinaryNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null)
            return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.data);

        // 计算当前位于树的第几层
        int currLevel = rowIndex / 2 + 1;
        // 若到了最后一层，则返回
        if (currLevel == treeDepth)
            return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    // 用于获得树的层数
    public static int getTreeDepth(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }




}

class BinaryNode {
    int data;
    BinaryNode left;
    BinaryNode right;
    BinaryNode parent;

    public BinaryNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}