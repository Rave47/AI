import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SearchIterative extends SearchAbstract{

        private static final int MAX_POSSIBLE_STATES = 362880 / 2;

        @Override
        public void process() {

            int depth = 0;
            int count = 0;

            while (uniqueStates.size() != MAX_POSSIBLE_STATES) {
                Node root = new Node(START_STATE, null, null);
                uniqueStates.put(root.hashCode(), Boolean.TRUE);
                Stack<Node> stack = new Stack<>();
                stack.push(root);

                List<Node> path = new ArrayList<>();
                while (!stack.empty()) {

                    Node crt = stack.pop();
                    count++;

                    if (!skipProcess) {
                        nextStep();
                        if (!skipProcess) System.out.println("Parent:\n" + crt.toString() + "\n\n------------------\n");
                    }


                    // if final state reached
                    if (isFinal(crt)) {
                        path = findPath(crt);
                        for (int i = path.size() - 1; i >= 0; --i) {
                            System.out.println(path.get(i).getBirth() + "\n");
                            System.out.println(path.get(i).toString() + "\n");
//                        System.out.println(path.get(i).getLevel() + "\n");

                        }
                        System.out.println("Operations number = " + count);
                        System.out.println("Depth = " + path.size());
                        break;

                    }

                    // processing children
                    List<Node> possibleChildren = createChildren(crt);
                    for (Node possibleChild : possibleChildren) {
                        if (isUnique(possibleChild) && possibleChild.getLevel() < depth) {
                            uniqueStates.put(possibleChild.hashCode(), Boolean.TRUE);

                            stack.push(possibleChild);

                            if (!skipProcess) System.out.println(possibleChild.toString() + "\n");
                        }
                    }
                }
                if (path.isEmpty()) {
                    uniqueStates.clear();
                    ++depth;
                } else return;
            }
            System.out.println("No solutions found!");
        }

    }