/*
 * (C) Copyright 2007 Nuxeo SAS <http://nuxeo.com> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id: IdUtils.java 15538 2007-04-04 16:31:57Z sfermigier $
 */

package org.nuxeo.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Utils for identifier generation.
 * 
 * @author <a href="mailto:at@nuxeo.com">Anahide Tchertchian</a>
 */
public final class IdUtils {

    private static final String WORD_SPLITTING_REGEXP = "[^a-zA-Z0-9]+";

    // TODO AT: dummy random, does not ensure uniqueness
    private static final Random random = new Random(new Date().getTime());

    // This is an utility class.
    private IdUtils() {
    }

    /**
     * Generates an unique string identifier.
     * 
     * @return
     */
    public static String generateStringId() {
        return String.valueOf(random.nextLong());
    }

    /**
     * Generates an unique long identifier.
     * 
     * @return
     */
    public static long generateLongId() {
        return random.nextLong();
    }

    /**
     * Generates an id from a non-null String.
     * <p>
     * Replaces accented characters from a string by their ascii equivalent,
     * removes non alphanumerical characters and replaces spaces by the given
     * wordSeparator character.
     * 
     * @param s
     *            the original String
     * @param wordSeparator
     *            the word separator to use (usually '-')
     * @param lower
     *            if lower is true, remove upper case
     * @param maxChars
     *            maximum longer of identifier characters
     * @return the identifier String
     */
    public static String generateId(String s, String wordSeparator,
            boolean lower, int maxChars) {
        s = StringUtils.toAscii(s);
        s = s.trim();
        if (lower) {
            s = s.toLowerCase();
        }
        String[] words = s.split(WORD_SPLITTING_REGEXP);
        // remove blank chars from words, did not get why they're not filtered
        List<String> wordsList = new ArrayList<String>();
        for (String word : words) {
            if (word != null && word.length() > 0) {
                wordsList.add(word);
            }
        }
        if (wordsList.isEmpty()) {
            return generateStringId();
        }
        String id;
        if (maxChars > 0) {
            // be sure at least one word is used
            id = wordsList.get(0);
            for (int i = 1; i < wordsList.size(); i++) {
                String newWord = wordsList.get(i);
                if (id.length() + newWord.length() > maxChars) {
                    break;
                } else {
                    id += wordSeparator + newWord;
                }
            }
            id = id.substring(0, Math.min(id.length(), maxChars));
        } else {
            id = StringUtils.join(wordsList.toArray(), wordSeparator);
        }

        return id;
    }

    /**
     * Generates an id from a non-null String.
     * <p>
     * Uses default values for wordSeparator: '-', lower: true, maxChars: 24.
     * 
     * @param s
     * @return
     */
    public static String generateId(String s) {
        return generateId(s, "-", true, 24);
    }

}
