import javax.swing.JTextArea;

public class PriorityQueue<T extends Comparable<? super T>> implements IPriorityQueue<T>{

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public PriorityQueue(){
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()){
            firstNode = newNode;
        }
        else
            lastNode.setNextNode(newNode);

        lastNode = newNode;
        numberOfEntries++;
    }

    @Override

    public T remove() {
        if (!isEmpty()){
            T data = peek();
            assert firstNode != null;
            firstNode.setData(null);
            firstNode = firstNode.getNextNode();
            if (firstNode == null){
                lastNode = null;
            }
            numberOfEntries--;
            return data;
        }
        else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    @Override
    public T peek() {
        if (!isEmpty()){
            return firstNode.getData();
        }
        else {
            throw new IndexOutOfBoundsException( "Illegal position given to peek operation.");
        }
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null && numberOfEntries == 0;
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            remove();
        }
    }

    @Override
    public void displayItems(JTextArea statusLabel) {
        Node node = firstNode;
        StringBuffer pData = new StringBuffer();
        for (int index = 0; index < numberOfEntries; index++) {
            System.out.println(firstNode.getData());
        	pData.append(String.valueOf(firstNode.getData())+"\n");
            firstNode = firstNode.getNextNode();
        }
        statusLabel.setText(pData.toString());
        System.out.println("output"+pData.toString());
        firstNode = node;
    }

    private class Node{
        private T data;
        private Node nextNode;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            this.nextNode = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            this.data = newData;
        }

        private Node getNextNode() {
            return nextNode;
        }

        private void setNextNode(Node newNode) {
            this.nextNode = newNode;
        }
    }



}
