package com.nicolasperussi.scriptburger.utils;

import java.text.Normalizer;

public class Slugify {
  public static String slugify(String str) {
    // Remove leading and trailing whitespace
    str = str.trim();

    // Make the string lowercase
    str = str.toLowerCase();

    // Remove accents, swap ñ for n, etc
    str = Normalizer.normalize(str, Normalizer.Form.NFD)
        .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

    // Remove invalid characters
    str = str.replaceAll("[^a-z0-9 -]", "");

    // Replace whitespace with a hyphen
    str = str.replaceAll("\\s+", "-");

    // Collapse consecutive hyphens
    str = str.replaceAll("-+", "-");

    return str;
  }
}
