import java.util.List;
import java.util.PriorityQueue;



public class SearchA extends SearchAbstract {

    @Override
    public void process() {
        Node root = new Node(START_STATE, null, null);
        uniqueStates.put(root.hashCode(), Boolean.TRUE);
        PriorityQueue<Node> queueA = new PriorityQueue<>(CAPACITY, new NodeComparator());
        root.setParam(compareA(root.getState()));
        queueA.add(root);

        int count = 0;
        while (!queueA.isEmpty()) {

            Node crt = queueA.poll();
            count++;

            if (!skipProcess) {
                nextStep();
                System.out.println("Parent:\n" + crt.toString() + "\n");
                System.out.println("Amount of unmatching chips = " + crt.getParam() + "\n------------------\n");
            }


            // if final state reached
            if (isFinal(crt)) {
                List<Node> path = findPath(crt);
                for (int i = path.size() - 1; i >= 0; --i) {
                    System.out.println(path.get(i).getBirth() + "\n");
                    System.out.println(path.get(i).toString() + "\n");
                    System.out.println("Amount of unmathing chips = " + path.get(i).getParam() + "\n");
//                        System.out.println(path.get(i).getLevel() + "\n");
                }
                System.out.println("Amount of steps = " + count);
                System.out.println("Depth = " + path.size());
                System.out.println("Amount created nodes = " + getCreatedNodesAmount());
                break;

            }

            // processing children
            List<Node> possibleChildren = createChildren(crt);
            for (Node possibleChild : possibleChildren) {
                if (isUnique(possibleChild)) {
                    uniqueStates.put(possibleChild.hashCode(), Boolean.TRUE);

                    possibleChild.setParam(compareA(possibleChild.getState()));
                    queueA.add(possibleChild);

                    if (!skipProcess) {
                        System.out.println(possibleChild.toString() + "\n");
                        System.out.println("Amount of unmatching chips = " + possibleChild.getParam() + "\n");
                    }
                }
            }
        }
    }

    public int compareA(int[][] state){
        int counter = 0;

        for (int i = 0; i < state.length; ++i){
            for (int j = 0; j < state.length; ++j){
                if ((state[i][j] != FINAL_STATE[i][j]) && (state[i][j] != EMPTY)) counter++;
            }
        }
        return counter;
    }


}






