'use client'
import BookItem from '@/components/BookItem';
import NewBook from '@/components/NewBook';
import { log } from 'console';
import React, { useEffect, useState } from 'react';

interface Book {
    id: string;
    title: string;
    price: string;
    isbn: string;
}

const Book = () => {

    const [books, setBooks] = useState([]);
    const [error, setError] = useState(false);
    const [loading, setLoading] = useState(true);
    const [isPopupOpen, setIsPopupOpen] = React.useState(false);

    useEffect(() => {
        const token = sessionStorage.getItem("token");
       
        const fetchBooks = async () => {
            const response = await fetch("/api/v1/books", {
                method: "GET",
                headers: {"Authorization": `Bearer ${token}`}
                
            });
            const data = await response.json();
            if(response.status === 200 && data.length > 0 ) {
                setBooks(data);
            } else {
                setError(true);
            }
            setLoading(false);
        }

        fetchBooks();
    }, []);
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
        <div className="overflow-x-auto">
            <div className='flex justify-end'>
                <button onClick={()=>setIsPopupOpen(true)}>Add New Book</button> 
            </div>
            <table className='table'>
                <thead>
                    <tr>
                        <th><NewBook isPopupOpen={isPopupOpen} setIsPopupOpen={setIsPopupOpen}/></th>
                    </tr>
                    <tr>
                        <th>Id</th>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    { loading? (<tr><td>Loading...</td></tr>): error? (<tr><td>Error Fetching books</td></tr>): (books.map((book: Book) => (
                        <BookItem 
                            key={book.id}
                            book={book}
                        />
                    )))}
                </tbody>
            </table>
        </div>
    </main>
  )
}

export default Book;