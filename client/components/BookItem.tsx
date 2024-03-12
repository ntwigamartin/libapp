import React from 'react';

interface Book {
    id: number;
    title: string;
    price: number;
    isbn: string;
}

interface BookItemProps {
    book: Book;
}

const BookItem: React.FC<BookItemProps> = ( {book}) => {
  return (
    <tr className='hover'>
        <td>{book.id}</td>
        <td>{book.isbn}</td>
        <td>{book.title}</td>
        <td>{book.price}</td>
    </tr>
  )
}

export default BookItem;