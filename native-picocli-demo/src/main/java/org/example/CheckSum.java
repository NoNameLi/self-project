package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.jansi.graalvm.AnsiConsole;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(
        name = "checksum",
        mixinStandardHelpOptions = true,
        version = "checksum 4.0",
        description = "Prints the checksum (MD5 by default) of a file to STDOUT."
)
public class CheckSum implements Callable<Integer> {

    @Parameters(
            index = "0",
            arity = "1",
            description = "The file whose checksum to calculate."
    )
    private File file;

    @Option(
            names = {"-a", "--algorithm"},
            description = "MD5, SHA-1, SHA-256, ..."
    )
    private String algorithm = "MD5";

    public static void main(String... args) {

        int exitCode = 0;
        // enable colors on Windows
        try (AnsiConsole ansi = AnsiConsole.windowsInstall()) {
            exitCode = new CommandLine(new CheckSum()).execute(args);
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // the business logic...
        byte[] data = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(data);
        String format = "%0" + (digest.length * 2) + "x%n";
        System.out.printf(format, new BigInteger(1, digest));
        return 0;
    }
}

