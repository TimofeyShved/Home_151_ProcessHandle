package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // запустили программу в консоли (сон на 1h)
        Process process = Runtime.getRuntime().exec("cmd sleep 1h");

        System.out.println("У текущей программы id "+ProcessHandle.current().pid());
        System.out.println("У консольной id "+process.pid());

        //Получим чужие процессы в нашей программе
        ProcessHandle processHandle = ProcessHandle.of(process.pid()).orElseThrow(IllegalAccessError::new);

        // А дальше добавим дополнительные действия, например при закрытии программы, сообщить
        processHandle.onExit().thenRun(()-> System.out.println("Консоль закрылась"));
        // так же узнать пользователя
        System.out.println(processHandle.info().user().orElse("no user"));
        // Посмотреть команду в командной строке
        System.out.println(processHandle.info().commandLine().orElse("Нет"));
        // уничтожить процесс
        processHandle.destroy();

        Thread.sleep(100);

    }
}
