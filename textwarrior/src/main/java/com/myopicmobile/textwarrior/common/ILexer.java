/*
 * Copyright (c) 2013 Tah Wei Hoon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License Version 2.0,
 * with full text available at http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * This software is provided "as is". Use at your own risk.
 */
package com.myopicmobile.textwarrior.common;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Does lexical analysis of a text for C-like languages.
 * The programming language syntax used is set as a static class variable.
 */
public interface ILexer
{
    //private final static int MAX_KEYWORD_LENGTH = 127;

    public final static int UNKNOWN = -1;
    public final static int NORMAL = 0;
    public final static int KEYWORD = 1;
    public final static int OPERATOR = 2;
    public final static int NAME = 3;
    public final static int NUMBER = 4;
    
    /** A word that starts with a special symbol, inclusive.
     * Examples:
     * :ruby_symbol
     * */
    public final static int SINGLE_SYMBOL_WORD = 10;

    /** Tokens that extend from a single start symbol, inclusive, until the end of line.
     * Up to 2 types of symbols are supported per language, denoted by A and B
     * Examples:
     * #include "myCppFile"
     * #this is a comment in Python
     * %this is a comment in Prolog
     * */
    public final static int SINGLE_SYMBOL_LINE_A = 20;
    public final static int SINGLE_SYMBOL_LINE_B = 21;

    /** Tokens that extend from a two start symbols, inclusive, until the end of line.
     * Examples:
     * //this is a comment in C
     * */
    public final static int DOUBLE_SYMBOL_LINE = 30;

    /** Tokens that are enclosed between a start and end sequence, inclusive,
     * that can span multiple lines. The start and end sequences contain exactly
     * 2 symbols.
     * Examples:
     * {- this is a...
     *  ...multi-line comment in Haskell -}
     * */
    public final static int DOUBLE_SYMBOL_DELIMITED_MULTILINE = 40;

    /** Tokens that are enclosed by the same single symbol, inclusive, and
     * do not span over more than one line.
     * Examples: 'c', "hello world"
     * */
    public final static int SINGLE_SYMBOL_DELIMITED_A = 50;
    public final static int SINGLE_SYMBOL_DELIMITED_B = 51;

    public void tokenize(DocumentProvider hDoc,LexCallback cb);
    
    public void cancelTokenize();
    
    public interface LexCallback
    {
        public void lexDone(List<Pair> results);
    }
}
