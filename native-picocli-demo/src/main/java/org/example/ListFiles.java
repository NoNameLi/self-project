package org.example;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "fileutils", subcommands = ListFiles.class)
class FileUtils {

    @CommandLine.Option(names = {"-d", "--directory"},
            description = "this option applies to all subcommands")
    File baseDirectory;
}

@CommandLine.Command(name = "list")
public class ListFiles implements Runnable {

    @CommandLine.ParentCommand
    private FileUtils parent; // picocli injects reference to parent command

    @CommandLine.Option(names = {"-r", "--recursive"},
            description = "Recursively list subdirectories")
    private boolean recursive;

    @Override
    public void run() {
        list(new File(parent.baseDirectory, "."));
    }

    private void list(File dir) {
        System.out.println(dir.getAbsolutePath());
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (f.isDirectory() && recursive) {
                    list(f);
                } else {
                    System.out.println(f.getAbsolutePath());
                }
            }
        }
    }
}