public class BinaryTree<K extends Comparable <K>,V> {
    private Node<K, V> root;

    public BinaryTree(){
        this.root = null;
    }

    public void insert(K key, V value){
        root = insert(root, key, value);
    }
    private Node<K, V> insert(Node<K, V> current, K key, V value){
        if (current == null) {
            return new Node<>(key, value);
        }
        if (key.compareTo(current.getKey())<0) {
            current.setLeft(insert(current.getLeft(), key, value));
        }else if (key.compareTo(current.getKey())>0) {
            current.setRight(insert(current.getRight(), key, value));
        }
        return current;
    }

    public V search(K key){
        return search(root, key);
    }

    private V search(Node<K, V> current, K key){
        if (current == null) {
            return null;
        }if (key.compareTo(current.getKey())== 0) {
            return current.getValue();
        }else if (key.compareTo(current.getKey())< 0) {
            return search(current.getLeft(), key);
        }else{
            return search(current.getRight(), key);
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node<K, V> current){
        if (current != null) {
            inOrder(current.getLeft());
            System.out.print(" "+ current.getValue());
            inOrder(current.getRight());
        }
    }
}
