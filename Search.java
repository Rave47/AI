import java.util.List;
import java.util.Stack;

public class Search extends SearchAbstract{

    @Override
    public void process() {
        Node root = new Node(START_STATE, null, null);
        uniqueStates.put(root.hashCode(), Boolean.TRUE);
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        int count = 0;
        while (!stack.empty()){

            Node crt = stack.pop();
            count++;

            if (!skipProcess){
                nextStep();
                if (!skipProcess) System.out.println("Parent:\n" + crt.toString() + "\n\n------------------\n");
            }


            // if final state reached
            if (isFinal(crt)) {
                List<Node> path = findPath(crt);
                for (int i=path.size()-1; i>=0; --i) {
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
                if (isUnique(possibleChild)){
                    uniqueStates.put(possibleChild.hashCode(), Boolean.TRUE);

                    stack.push(possibleChild);

                    if (!skipProcess) System.out.println(possibleChild.toString() + "\n");
                }
            }
        }
    }


}
