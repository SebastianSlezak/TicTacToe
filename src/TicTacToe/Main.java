package TicTacToe;

import java.util.*;

public class Main {

    static ArrayList<Integer> player = new ArrayList<>();
    static ArrayList<Integer> cpu = new ArrayList<>();

    private static int i = 0;
    private static int j = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        char[][] board = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };

        while(true){

            printBoard(board);

            int choicePlayer = scanner.nextInt();

            while(choicePlayer > 9){
                System.out.println("Enter a number from 1 to 9");
                choicePlayer = scanner.nextInt();
                while(player.contains(choicePlayer) || cpu.contains(choicePlayer)){
                    System.out.println("Select the correct position:");
                    choicePlayer = scanner.nextInt();
                }
            }


            choice(choicePlayer,board,"player");

            String result = checkWinner();
            if (result.length() > 0){
                System.out.println(result);
                removeElements();
                clear(board);
                System.out.println("Player " + i + "-" + j + " CPU");
            }

            int choiceCPU = random.nextInt(9) + 1;

                while (player.contains(choiceCPU) || cpu.contains(choiceCPU)) {
                    choiceCPU = random.nextInt(9) + 1;
                }

            choice(choiceCPU,board,"cpu");

            result = checkWinner();
            if (result.length() > 0){
                System.out.println(result);
                removeElements();
                clear(board);
                System.out.println("Player " + i + "-" + j + " CPU");
            }
        }

    }

    public static void printBoard(char[][] board){
        for (char[] print : board) {
            System.out.println(print);
        }
    }

    public static void choice(int choice,char[][] board,String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            player.add(choice);
        }else if(user.equals("cpu")){
            symbol = 'O';
            cpu.add(choice);
        }

        switch(choice){
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
        }
    }

    public static String checkWinner(){
        List<Integer> rowTop = Arrays.asList(1,2,3);
        List<Integer> rowMid = Arrays.asList(4,5,6);
        List<Integer> rowBot = Arrays.asList(7,8,9);
        List<Integer> colLeft = Arrays.asList(1,4,7);
        List<Integer> colMid = Arrays.asList(2,5,8);
        List<Integer> colRight = Arrays.asList(3,6,9);
        List<Integer> cros1 = Arrays.asList(1,5,9);
        List<Integer> cros2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<>();

        winning.add(rowTop);
        winning.add(rowMid);
        winning.add(rowBot);
        winning.add(colLeft);
        winning.add(colMid);
        winning.add(colRight);
        winning.add(cros1);
        winning.add(cros2);

        for(List list : winning){
            if (player.containsAll(list)){
                i++;
                return "You won!";
            }else if(cpu.containsAll(list)){
                j++;
                return "CPU wins!";
            }else if(player.size() + cpu.size() == 9){
                return "Tie";
            }
        }
        return "";
    }

    public static void removeElements(){
        player.removeAll(player);
        cpu.removeAll(cpu);
    }

    public static void clear(char[][] board){
        board[0][0] = ' ';
        board[0][2] = ' ';
        board[0][4] = ' ';
        board[2][0] = ' ';
        board[2][2] = ' ';
        board[2][4] = ' ';
        board[4][0] = ' ';
        board[4][2] = ' ';
        board[4][4] = ' ';
    }
}
