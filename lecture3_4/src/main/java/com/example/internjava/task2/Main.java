package com.example.internjava.task2;

import com.example.internjava.task2.model.Violation;
import com.example.internjava.task2.model.ViolationList;
import com.example.internjava.task2.model.Violator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Path inputPath = Path.of("input/task2/violators_of_traffic_rules");
    private static final Path outputPath = Path.of("output/task2/output.xml");

    public static void main(String[] args) {
        List<Violation> output = listFilesStream(inputPath, pathStream ->
                pathStream
                        .map(Main::readInputFile)
                        .map(Main::
                                mapToViolations)
                        .flatMap(List::stream)
                        .collect(Collectors.groupingBy(
                                Violation::getType,
                                Collectors.reducing((x, y) -> new Violation(
                                        x.getType(),
                                        Double.sum(x.getFineAmount(), y.getFineAmount())
                                )))
                        )
                        .values()
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .sorted((x, y) -> Double.compare(y.getFineAmount(), x.getFineAmount()))
                        .collect(Collectors.toList())
        );
        writeOutput(new ViolationList(output), outputPath);
    }

    private static <T> T listFilesStream(Path path, Function<Stream<Path>, T> function) {
        try (Stream<Path> filesStream = Files.list(path).filter(s -> s.toFile().isFile())) {
            return function.apply(filesStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Violator> readInputFile(Path path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(path.toFile(), Violator[].class));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static List<Violation> mapToViolations(List<Violator> violators) {
        return violators.stream()
                .map(s -> new Violation(s.getType(), s.getFineAmount()))
                .collect(Collectors.toList());
    }

    private static void writeOutput(ViolationList violationList, Path outputPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ViolationList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(violationList, outputPath.toFile());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }


}
