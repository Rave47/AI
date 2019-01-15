import java.util.*;

public abstract class SearchAbstract {


    protected static final int EMPTY = 0;

    protected static final int[][] START_STATE = {{0, 4, 3},
                                                  {6, 2, 1},
                                                  {7, 5, 8}};

    protected static final int[][] FINAL_STATE = {{1, 2, 3},
                                                  {4, 0, 5},
                                                  {6, 7, 8}};

    protected static boolean skipProcess = false;

    protected static HashMap<Integer, Boolean> uniqueStates = new HashMap<>();


    protected static List<Node> createChildren(Node crt){
            int[][] state = crt.getState();

            // finding empty cell
            int[] emptyPos = findEmptyCell(state);

            List<Node> res = new LinkedList<>();

            switch (Arrays.toString(emptyPos)) {
                // center
                case "[1, 1]":
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][0] + "\" move right ([1][0] -> [1][1])"));
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][1] + "\" move down ([0][1] -> [1][1])"));
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][2] + "\" move left ([1][2] -> [1][1])"));
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt, "\"" + state[2][1] + "\" move up ([2][1] -> [1][1])"));
                    break;

                // corners
                case "[0, 0]":
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][0] + "\" move up ([1][0] -> [0][0])"));
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][1] + "\" move left ([0][1] -> [0][0])"));
                    break;

                case "[0, 2]":
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][1] + "\" move right ([0][1] -> [0][2])"));
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][2] + "\" move up ([1][2] -> [0][2])"));
                    break;

                case "[2, 2]":
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][2] + "\" move down ([1][2] -> [2][2])"));
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][1] + "\" move right ([2][1] -> [2][2])"));
                    break;

                case "[2, 0]":
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][1] + "\" move left ([2][1] -> [2][0])"));
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][0] + "\" move down ([1][0] -> [2][0])"));
                    break;

                // sides
                case "[0, 1]":
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][0] + "\" move right ([0][0] -> [0][1])"));
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][1] + "\" move up ([1][1] -> [0][1])"));
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][2] + "\" move left ([0][2] -> [0][1])"));
                    break;

                case "[1, 2]":
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][2] + "\" move down ([0][2] -> [1][2])"));
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][1] + "\" move right ([1][1] -> [1][2])"));
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][2] + "\" move up ([2][2] -> [1][2])"));
                    break;

                case "[2, 1]":
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][2] + "\" move left ([2][2] -> [2][1])"));
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][1] + "\" move down ([1][1] -> [2][1])"));
                    res.add(new Node(shiftRight(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][0] + "\" move right ([2][0] -> [2][1])"));
                    break;

                case "[1, 0]":
                    res.add(new Node(shiftUp(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[2][0] + "\" move up ([2][0] -> [1][0])"));
                    res.add(new Node(shiftLeft(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[1][1] + "\" move left ([1][1] -> [1][0])"));
                    res.add(new Node(shiftDown(state, emptyPos[0], emptyPos[1]), crt,"\"" + state[0][0] + "\" move down ([0][0] -> [1][0])"));
                    break;


            }
        CreatedNodesAmount += res.size();
            return res;
        }

    protected static int[][] shiftUp(int[][] state, int i, int j) {
            int[][] res = copy(state);
            res[i][j] = res[i+1][j];
            res[i+1][j] = EMPTY;
            return res;
        }
    protected static int[][] shiftDown(int[][] state, int i, int j) {
            int[][] res = copy(state);
            res[i][j] = res[i-1][j];
            res[i-1][j] = EMPTY;
            return res;
        }
    protected static int[][] shiftLeft(int[][] state, int i, int j) {
            int[][] res = copy(state);
            res[i][j] = res[i][j+1];
            res[i][j+1] = EMPTY;
            return res;
        }
    protected static int[][] shiftRight(int[][] state, int i, int j) {
            int[][] res = copy(state);
            res[i][j] = res[i][j-1];
            res[i][j-1] = EMPTY;
            return res;
        }


    protected static boolean isUnique(Node crt){
        return !uniqueStates.containsKey(crt.hashCode());
    }
    protected static int[] findEmptyCell(int[][] state) {
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if(state[i][j] == EMPTY) {
                        int[] res = {i, j};
                        return res;
                    }
                }
            }
            return null;
        }
    protected static int[][] copy(int[][] toCopy){
            int[][] res = new int[toCopy.length][toCopy.length];
            for (int i = 0; i < toCopy.length; ++i) {
                res[i] = Arrays.copyOf(toCopy[i], toCopy.length);
            }
            return res;
        }
    protected static boolean isFinal (Node crt){

            return (Arrays.equals(crt.getState()[0], FINAL_STATE[0]) &&
                    Arrays.equals(crt.getState()[1], FINAL_STATE[1]) &&
                    Arrays.equals(crt.getState()[2], FINAL_STATE[2]));
        }
    protected static List<Node> findPath(Node crt){
            List<Node> res = new ArrayList<>();
            res.add(crt);

            while (crt.getParent() != null) {
                res.add(crt.getParent());
                crt = crt.getParent();
            }
            return res;
        }
    protected static void nextStep(){
        System.out.println("Press 0 to skip process\nPress any for the next step\n");
        Scanner in = new Scanner(System.in);
        int crt = in.nextInt();

        if (crt == 0) {
            skipProcess = true;
        }
    }



    // for overriding
    public abstract void process();


    // for SearchA & Manhattan
    protected static final int CAPACITY = 100000;
    protected static int CreatedNodesAmount = 0;
    protected  static int getCreatedNodesAmount(){ return CreatedNodesAmount;}
    class NodeComparator implements Comparator<Node> {

        // Overriding compare()method of Comparator
        // for descending order of param
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.getParam() + n1.getLevel() > n2.getParam() + n2.getLevel())
                return 1;
            else if (n1.getParam() + n1.getLevel() < n2.getParam() + n2.getLevel())
                return -1;
            return 0;
        }
    }


}
