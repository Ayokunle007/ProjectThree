package com.UserDemo.UserDemo.Controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/message")

public class UserDemoController {

    File file = new File("messages.txt");
    File file1 = new File("log.txt");

    @GetMapping("/messages")
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        String x;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((x = bufferedReader.readLine()) != null) {
                stringBuilder.append(x);

            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return stringBuilder.toString();
    }

    public long getMessageCount() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))) {
            return bufferedReader.lines().count();
        }
    }

    @PostMapping("/count")
    public String logMessage(@RequestBody String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, true))) {
            bufferedWriter.write(message + "#");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return message;  //logActivity();

    }

    public String logActivity(long count) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, true))) {
            bufferedWriter.write("New message created, count: " + count);
            bufferedWriter.newLine();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return "done";
    }

    @PostMapping("/messages1")
    public String postMessage(@RequestBody String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(message + "#");
            long count = this.getMessageCount();
            logActivity(count);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return " Message SuccessfulLy Saved ";
    }
}









