package com.ga.employeeSalary.services;

import com.ga.employeeSalary.models.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

@Service
public class CSVProcessor {
    private final ReentrantLock lock = new ReentrantLock();

    public File UpdateSalaries(MultipartFile input, String fileName){
        File file = new File(fileName);
        try (InputStream inputStream = input.getInputStream();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // Cached thread pool
            List<java.util.concurrent.Future<?>> futures = new ArrayList<>();
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            Map<Integer, String> result = new HashMap<>();

            String line;

            while ((line = reader.readLine()) != null) {
                String finalLine = line;
                futures.add(cachedThreadPool.submit(() -> {
                    List<String> lineValues = Arrays.stream(finalLine.split(",")).map(String::trim).toList();
                    Employee object = new Employee(
                            Integer.parseInt(lineValues.get(0)),
                            lineValues.get(1),
                            Double.parseDouble(lineValues.get(2)),
                            LocalDate.parse(lineValues.get(3)),
                            lineValues.get(4),
                            Double.parseDouble(lineValues.get(5))
                    );
                    object.updateSalary();
                    lock.lock();
                    try {
                        result.put(object.getId(), object.employeeToCSVLine());
                    } finally {
                        lock.unlock();
                    }
                    return null;
                }));
            }

            for (var f : futures) {
                f.get();
            }

            cachedThreadPool.shutdown();

            for(int i =1; i<= result.size(); i++){
                writer.write(result.get(i));
                // for testing
                System.out.println(result.get(i));
            }

        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return file;
    }
}
