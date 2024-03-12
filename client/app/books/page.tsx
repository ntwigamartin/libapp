'use client'
import BookItem from '@/components/BookItem';
import React, { useEffect, useState } from 'react';

interface Book {
    id: number;
    title: string;
    price: number;
    isbn: string;
}

const Book = () => {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        const token = sessionStorage.getItem("token");
        const fetchBooks = async () => {
            const response = await fetch("http://localhost:8080/api/v1/books", {
                method: "GET",
                headers: {"Authorization": `Bearer ${token}`}
                
            });
            const data = await response.json();
            setBooks(data);
        }

        fetchBooks();
    }, []);
    console.log(books);
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
        <div className="overflow-x-auto">
            <table className='table'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    {books.map((book: Book) => (
                        <BookItem 
                            key={book.id}
                            book={book}
                        />
                    ))}
                </tbody>
            </table>
        </div>
    </main>
  )
}

export default Book;