package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll(); //select * from Book : JPQL -> SQL 전송
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);  // delete from Book b where b.id=?1
    }

    public Book save(Book book){
        return bookRepository.save(book); // insert into Book ~~
    }

    public Book findById(Long id){
        Optional<Book> optional =bookRepository.findById(id);
        if(optional.isPresent()){
            return optional.get(); //
        }else{
            throw new RuntimeException("Book not found with id:"+id);
        }

    }
    @Transactional
    public Book update(Long id, Book book){
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()) {
            Book dbbook = optional.get();  //DB에서 가져온 Book
            dbbook.setTitle(book.getTitle());
            dbbook.setPrice(book.getPrice());
            dbbook.setAuthor(book.getAuthor());
            dbbook.setPage(book.getPage());
            bookRepository.save(dbbook); // update SQL : 설명 다음주
            return dbbook;  //update 수정이된다 ( 더티체킹JPA : 바로업데이트되는게아니라 수정이도면 업데이트한다 ) -성능저하
        }else{
                throw new RuntimeException("Book not found with id:"+id);
            }
        }
    }

