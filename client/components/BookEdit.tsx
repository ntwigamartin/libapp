import React, { FormEvent } from 'react'

interface Book {
    id: string;
    title: string;
    price: string;
    isbn: string;
}
interface BookEditProps {
    isOpen: boolean;
    onClose: any;
    book: Book;
}
const BookEdit: React.FC<BookEditProps> = ({ isOpen, onClose, book }) => {
    const [editedBook, setEditedBook] = React.useState<Book>(book);

    const handleSubmit = async(event: FormEvent<HTMLFormElement>) => {
        // event.preventDefault();
        const token = sessionStorage.getItem('token');
        
        const response = await fetch(`/api/v1/books/${book.id}`, {
          method: 'PUT',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(editedBook),
        });
        const data = await response.json();
        if (data.error) {
          alert(data.error);
        } else {
          alert('Book updated successfully');
        }
        onClose();
      }
    return (
      <>
        {isOpen && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50" onClick={onClose}>
                <div className="bg-white p-8 rounded-lg shadow-lg relative" onClick={(e) => e.stopPropagation()}>
                    <button className="absolute top-0 right-0 p-2" onClick={onClose}>
                        <svg className="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                    <form onSubmit={handleSubmit}>
                        <div className='flex flex-col gap-2'>
                            <div>
                                <label htmlFor="id" className='font-bold text-black mr-7'>ID:</label>
                                <input 
                                type="tex" 
                                value={book.id} 
                                disabled
                                />
                            </div>
                            <div>
                                <label htmlFor="isbn" className='font-bold text-black mr-2'>ISBN:</label>
                                <input 
                                type='text' 
                                defaultValue={book.isbn} 
                                onChange={(e) => setEditedBook({...editedBook, isbn: e.target.value})}
                                />
                            </div>
                            <div>
                                <label htmlFor="title" className='font-bold text-black mr-3'>Title:</label>
                                <input 
                                type='text' 
                                defaultValue={book.title} 
                                onChange={(e) => setEditedBook({...editedBook, title: e.target.value})}
                                />
                            </div>
                            <div>
                                <label htmlFor="price" className='font-bold text-black mr-2'>Price:</label>
                                <input 
                                type='text' 
                                defaultValue={book.price} 
                                onChange={(e) => setEditedBook({...editedBook, price: e.target.value})}
                                />
                            </div>
                            <button className='bg-blue-500 text-white'>Save</button>
                            <button className='bg-red-500 text-white' onClick={onClose}>Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        )}
      </>
    );
  };
  
  export default BookEdit;