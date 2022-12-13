package com.internjava.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internjava.task1.model.FineStatistics;
import com.internjava.task1.model.FineStatisticsList;
import com.internjava.task1.model.Violator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    private static final Path inputPath = Path.of("input/task1/violators_of_traffic_rules");
    private static final Path outputPath = Path.of("output/task1/output.xml");
    private static final ExecutorService executorService = Executors.newFixedThreadPool(8);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<CompletableFuture<List<Violator>>> futureList = new ArrayList<>();
        List<File> listOfFiles = getListOfFiles(inputPath);
        for (File file : listOfFiles) {
            futureList.add(CompletableFuture.supplyAsync(() -> readFile(file), executorService));
        }

        List<FineStatistics> fineStatistics = allOf(futureList)
                .thenApply(s -> s.stream().flatMap(List::stream).collect(Collectors.toList()))
                .thenApply(Main::mapToFineStatistics)
                .thenApply(List::stream)
                .thenApply(s -> s.collect(Collectors.groupingBy(
                        FineStatistics::getType,
                        Collectors.reducing((x, y) -> new FineStatistics(
                                x.getType(),
                                Long.sum(x.getFineAmount(), y.getFineAmount())
                        )))
                )
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((x, y) -> Double.compare(y.getFineAmount(), x.getFineAmount()))
                .collect(Collectors.toList()))
                .get();

        writeOutputFile(new FineStatisticsList(fineStatistics), outputPath);
        System.out.println(System.currentTimeMillis() - start);
        executorService.shutdown();
    }

    private static List<Violator> readFile(File file) {
        for (int i = 0; i < 100; i++) {
            try {
                Arrays.asList(mapper.readValue(file, Violator[].class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return Arrays.asList(mapper.readValue(file, Violator[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static<T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
        CompletableFuture<Void> allFuturesResult = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]));
        return allFuturesResult.thenApply(v -> futuresList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.<T>toList())
        );
    }
    private static List<File> getListOfFiles(Path path) throws IOException {
        return Files.list(path)
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(p -> {
                    try {
                        mapper.readValue(p, Violator[].class);
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    private static List<FineStatistics> mapToFineStatistics(List<Violator> violators) {
        return violators.stream()
                .map(s -> new FineStatistics(s.getType(), s.getFineAmount()))
                .collect(Collectors.toList());
    }

    private static void writeOutputFile(FineStatisticsList fineStatisticsList, Path outputPath){
        try {
            JAXBContext context = JAXBContext.newInstance(FineStatisticsList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(fineStatisticsList, outputPath.toFile());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}

