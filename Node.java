import java.util.Arrays;

public class Node {
    private final int[][] state;
    private final Node parent;
    private int level;
    private int param;

    private final String birth;


    public Node(int[][] state, Node parent, String birth) {
        this.state = state;
        this.parent = parent;
        this.birth = birth;
        if (parent != null) {this.level = parent.level+1;}
        else {this.level = 1;}
    }


    public int[][] getState() {
        return state;
    }
    public int getLevel(){
        return level;
    }
    public Node getParent() {
        return parent;
    }
    public String getBirth() {
        return birth;
    }
    public int getParam() { return param; }

    public void setParam(int param) { this.param = param; }


    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                hash = hash * 10 + state[i][j];
            }

        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.equals(state, node.state);
    }

    @Override
    public String toString(){
        return Arrays.toString(state[0]) + "\n" + Arrays.toString(state[1]) + "\n" + Arrays.toString(state[2]);
    }
}
