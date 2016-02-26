package com.LingduoKong.app;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    final String GET = "GET";
    final String SET = "SET";
    final String UNSET = "UNSET";
    final String NUMEQUALTO = "NUMEQUALTO";
    final String END = "END";
    final String BEGIN = "BEGIN";
    final String ROLLBACK = "ROLLBACK";
    final String COMMIT = "COMMIT";

    HashMap<String, Integer> commandMap;

    public App() {
        commandMap = new HashMap<>();

        commandMap.put(GET, 1);
        commandMap.put(SET, 2);
        commandMap.put(UNSET, 1);
        commandMap.put(NUMEQUALTO, 1);
        commandMap.put(END, 0);
        commandMap.put(BEGIN, 0);
        commandMap.put(ROLLBACK, 0);
        commandMap.put(COMMIT, 0);
    }

    public void start() {
        DataControl dataControl = new DataControl();
        Scanner scanner = new Scanner(System.in);

        boolean keepGetInput = true;

        while (keepGetInput) {

            String input = scanner.nextLine().trim();

            String[] arguments = input.split(" ");

            if (arguments == null || arguments.length == 0) {
                System.out.println("Illegal input, please try again!");
                continue;
            }

            if (!commandMap.containsKey(arguments[0].toUpperCase())) {
                System.out.println("No such a commend! Please try again!");
                continue;
            }

            String commend = arguments[0].toUpperCase();

            if (arguments.length != commandMap.get(commend)) {
                System.out.println("Illeal Argument number. Expect "
                        + commandMap.get(commend) + " arguments");
                continue;
            }

            switch (commend) {
                case BEGIN:
                    dataControl.beginNewTransaction();
                    break;
                case END:
                    keepGetInput = false;
                    break;
                case GET:
                    System.out.println(dataControl.get(arguments[1]));
                    break;
                case SET:
                    dataControl.set(arguments[1], arguments[2]);
                    break;
                case UNSET:
                    dataControl.unset(arguments[1]);
                    break;
                case ROLLBACK:
                    dataControl.rollBack();
                    break;
                case COMMIT:
                    dataControl.commit();
                    break;
                case NUMEQUALTO:
                    System.out.println(dataControl.numberEqualsTo(arguments[1]));
                    break;
                default:
                    System.out.println("NO such commend: " + commend);
            }
        }
    }


//    public static void main( String[] args )
//    {
//
//        DataControl dataControl = new DataControl();
//        Scanner scanner = new Scanner(System.in);
//
//        String commend = null;
//
//        while ((commend = scanner.nextLine()).equals("END")) {
//
//            String[] arguments = commend.split(" ");
//
//            if (arguments == null || arguments.length == 0) {
//                System.out.println("Illegal input, please try again!");
//                continue;
//            }
//
//            if (arguments[0].toUpperCase().)
//
//
//        }

}
