package com.LingduoKong.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

/**
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

    public void start() throws UnsupportedEncodingException {
        DataControl dataControl = new DataControl();
        Scanner scanner = new Scanner(System.in);

        boolean keepGetInput = true;

        while (keepGetInput) {

            String input = scanner.nextLine().trim();

            if (input.length() == 0) {
                continue;
            }

            String[] arguments = input.split(" ");

            if (!commandMap.containsKey(arguments[0].toUpperCase())) {
                System.out.println("No such a commend! Please try again!");
                continue;
            }

            String commend = arguments[0].toUpperCase();

            if (arguments.length - 1 != commandMap.get(commend)) {
                System.out.println("Illegal Argument number. Expect "
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
                    System.out.println("\t" + dataControl.get(arguments[1]));
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
            }
        }
    }

}
