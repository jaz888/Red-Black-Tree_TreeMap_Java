import main.*;
import java.io.*;
import java.util.*;
public class bbst {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("please specify input file");
            return;
        }


        RedBlackTreeInteger rbt = new RedBlackTreeInteger();
        String textPath = args[0];
        InputStream is = null;
        try {
            is = new FileInputStream(textPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
            Long linesRead = 0L;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if(linesRead % 10000000L == 0){
                    System.out.print(10*linesRead/10000000L);
                    System.out.println(" million lines done.");
                }
                line = line.trim();
                String[] as = line.split(" ");
                if(as.length >= 2){
                    Integer id = new Integer(as[0]);
                    Integer count = new Integer(as[1]);
                    rbt.insert(id,count);
                }
                linesRead++;
            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("finish reading file, please input commands:");
        Scanner input = new Scanner(System.in);
        //System.out.print("input command: ");
        String command = input.nextLine();
        while(command.length() >= 1){

            String[] commands = command.split(" ");
            if(commands.length < 1){
                System.out.println("invalid command1: "+command);
            }else{
                // commands.length >= 2
                if(commands[0].equals("increase") && commands.length >= 3){
                    // Increase(theID, m)
                    Integer theId = new Integer(commands[1]);
                    Integer m = new Integer(commands[2]);
                    System.out.println(rbt.increase(theId, m));

                }else if(commands[0].equals("reduce") && commands.length >= 3){
                    // Reduce(theID, m)
                    Integer theId = new Integer(commands[1]);
                    Integer m = new Integer(commands[2]);
                    System.out.println(rbt.reduce(theId, m));

                }else if(commands[0].equals("count") && commands.length >= 2){
                    // Count(theID)
                    Integer theId = new Integer(commands[1]);
                    System.out.println(rbt.count(theId));

                }else if(commands[0].equals("inrange") && commands.length >= 3){
                    // InRange(ID1, ID2)
                    Integer id1 = new Integer(commands[1]);
                    Integer id2 = new Integer(commands[2]);
                    System.out.println(rbt.inRange(id1, id2));

                }else if(commands[0].equals("next") && commands.length >= 2){
                    // Next(theID)
                    Integer theId = new Integer(commands[1]);
                    TreeNode<Integer, Integer> res = rbt.next(theId);
                    System.out.println(res.key+" "+res.val);

                }else if(commands[0].equals("previous") && commands.length >= 2){
                    // Previous(theID)
                    Integer theId = new Integer(commands[1]);
                    TreeNode<Integer, Integer> res = rbt.previous(theId);
                    System.out.println(res.key+" "+res.val);

                }else if(commands[0].equals("quit")){
                    // quit
                    return;

                }else{
                    System.out.println("invalid command: "+command);
                }
            }

            //System.out.print("input command: ");
            command = input.nextLine();
        }
    }
}
