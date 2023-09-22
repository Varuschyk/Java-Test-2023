package com.alexvar.e2e.test.util;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

  @Nonnull
  public static List<File> readFiles(@Nonnull final String root) {
    final var innerFiles = new File(root).listFiles();
    final var allFilesFromDirectories = new ArrayList<File>();

    if (innerFiles == null) {
        throw new RuntimeException(String.format("Directory %s doesn't exist!", root));
    }

    for (final var file : innerFiles) {
      if (file.isDirectory()) {
        allFilesFromDirectories.addAll(
            readFiles(file.getAbsolutePath())
        );
      } else {
        allFilesFromDirectories.add(file);
      }
    }

    return allFilesFromDirectories;
  }

}
