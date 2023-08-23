package com.example.spring6app.service;

import com.example.spring6app.domain.Book;

public interface BookService {

    Iterable<Book> getBooks();
}
