package DemoOfExam.Dictionary_Tree;

/**
 * @ClassName dic_tree
 * @Description 字典树
 *
 * 树中有"abc","ab","ab","abd","bc","bd","cd","cde","ce"总共9个字符串
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:31
 * @Version 1.0
 */
public class dic_tree {
    // Trie
    static class Trie {

        private class Node {
            int path;
            int end;
            Node[] next;//使用整数表示字符　c - 'a'

            public Node() {
                path = 0;
                end = 0;
                next = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        //插入一个字符串
        public void insert(String word) {
            if (word == null)
                return;
            Node cur = root;
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (cur.next[index] == null) { //没有就新建
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
                cur.path++; //经过这里
            }
            cur.end++;
        }

        //统计某个字符串的数量
        public int count(String word) {
            if (word == null)
                return 0;
            Node cur = root;
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (cur.next[index] == null)
                    return 0;
                cur = cur.next[index];
            }
            return cur.end;
        }

        public boolean search(String word) {
            return count(word) > 0;
        }

        // 求前缀是prefix的数量
        public int prefixNum(String prefix) {
            if (prefix == null)
                return 0;
            Node cur = root;
            int index = 0;
            for (int i = 0; i < prefix.length(); i++) {
                index = prefix.charAt(i) - 'a';
                if (cur.next[index] == null)
                    return 0;
                cur = cur.next[index];
            }
            return cur.path; //返回这个经过的　　也就是以这个为前驱的
        }

        public boolean startsWith(String prefix) {
            return prefixNum(prefix) > 0;
        }

        // 在trie中删除word
        public void remove(String word) {
            if (word == null)
                return;
            if (!search(word)) //不包含这个字符串
                return;
            Node cur = root;
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (--cur.next[index].path == 0) {
                    cur.next[index] = null; //释放掉下面的这棵树
                    return;
                }
                cur = cur.next[index];
            }
            cur.end--; //最后这个字符串也要--
        }
    }

    public static void main(String[] args) {

        // 简单测试

        Trie trie = new Trie();

        trie.insert("abc");
        trie.insert("ab");
        trie.insert("ab");
        trie.insert("abd");
        trie.insert("bc");
        trie.insert("bd");
        trie.insert("cd");
        trie.insert("cde");
        trie.insert("ce");

        System.out.println(trie.count("ab"));
        trie.remove("ab");
        System.out.println(trie.count("ab"));

        System.out.println(trie.count("abd"));
        trie.remove("ab");
        System.out.println(trie.count("ab"));
        System.out.println(trie.count("abd"));

        trie.remove("abd");
        System.out.println(trie.count("abd"));
    }
}
