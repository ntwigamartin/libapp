import Link from 'next/link';
import { FormEvent, useState } from 'react';
import BookEdit from './BookEdit';

interface Book {
    id: string;
    title: string;
    price: string;
    isbn: string;
}

interface BookItemProps {
    book: Book;
}

const BookItem: React.FC<BookItemProps> = ( {book}) => {
      const [isPopupOpen, setIsPopupOpen] = useState(false);
      const token = sessionStorage.getItem("token");

      const handleOpenPopup = () => {
        setIsPopupOpen(true);
      };

      const handleClosePopup = () => {
        setIsPopupOpen(false);
      };

      const handleDelete = async () => {
        const response = await fetch(`/api/v1/books/${book.id}`, {
          method: 'DELETE',
          headers: {
            'Authorization': `Bearer ${token}`
          }
        })
        const data = await response.json();
        console.log(data);
        
        if (data.error) {
          alert('Error: ' + data.error)
        }else {
          alert("Book deleted successfully")
          window.location.reload();

        }
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
        <BookEdit isOpen={isPopupOpen} onClose={handleClosePopup} book={book} />
      </td>
      <td>
        <button onClick={handleDelete}>Delete</button>
      </td>
    </tr>
    </>
  )
}

export default BookItem;