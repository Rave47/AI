import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;



public class SearchManhattan extends SearchAbstract {

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
                if (!skipProcess) {
                    System.out.println("Parent:\n" + crt.toString() + "\n");
                    System.out.println("Distance = " + crt.getParam() + "\n------------------\n");
                }
            }


            // if final state reached
            if (isFinal(crt)) {
                List<Node> path = findPath(crt);
                for (int i = path.size() - 1; i >= 0; --i) {
                    System.out.println(path.get(i).getBirth() + "\n");
                    System.out.println(path.get(i).toString() + "\n");
                    System.out.println("Distance = " + path.get(i).getParam() + "\n"); // TODO: correct out
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
                        System.out.println("Distance = " + possibleChild.getParam() + "\n"); // TODO: correct out
                    }
                }
            }
        }
    }



    public int compareA(int[][] state){
        int counter = 0;
        for (int q = 1; q <= 8; q++){

            int iSTATE = 0;
            int jSTATE = 0;
            int iFINAL_STATE = 0;
            int jFINAL_STATE = 0;

            for (int i = 0; i<=2; i++){
                for (int j = 0; j<=2; j++){

                    if (state[i][j] == q){
                        iSTATE = i;
                        jSTATE = j;
                    }
                    if (FINAL_STATE[i][j] == q) {
                        iFINAL_STATE = i;
                        jFINAL_STATE = j;
                    }
                }
            }
            counter += Math.abs(iSTATE - iFINAL_STATE)  + Math.abs(jSTATE - jFINAL_STATE);
        }
        return counter;
    }


}





