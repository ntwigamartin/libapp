import Link from 'next/link';
import { useState } from 'react';
import BookEdit from './BookEdit';

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
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const handleOpenPopup = () => {
    setIsPopupOpen(true);
  };

  const handleClosePopup = () => {
    setIsPopupOpen(false);
  };
  return (
    <>
      <tr className='hover'>
      <td>{book.id}</td>
      <td>{book.isbn}</td>
      <td>{book.title}</td>
      <td>{book.price}</td>
      <td>
        <button onClick={handleOpenPopup}>Update</button>
        <BookEdit isOpen={isPopupOpen} onClose={handleClosePopup} >
          <h2>edit stuff here</h2>
        </BookEdit>
      </td>
      <td>Delete</td>
    </tr>
    

    </>
    
    
  )
}

export default BookItem;