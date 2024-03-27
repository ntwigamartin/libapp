import React, { FormEvent, useState } from 'react';


interface NewBookProps {
  isPopupOpen:boolean;
  setIsPopupOpen: any;
}
const NewBook: React.FC<NewBookProps> = ({ isPopupOpen, setIsPopupOpen }) => {
    const [book, setBook] = useState({
        isbn: "",
        title: "",
        price: ""
    });

    const handleSubmit = async(event: FormEvent<HTMLFormElement>) => {
        // event.preventDefault();
        const token = sessionStorage.getItem('token');
        
        const response = await fetch(`/api/v1/books`, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(book),
        });
        const data = await response.json();
        if (data.error) {
          alert(data.error);
        } else {
          alert('Book added successfully');
        }
        setIsPopupOpen(false);
      }
  return (
    <>
        {isPopupOpen && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50" onClick={()=>setIsPopupOpen(false)}>
                <div className="bg-white p-8 rounded-lg shadow-lg relative" onClick={(e) => e.stopPropagation()}>
                    <button className="absolute top-0 right-0 p-2" onClick={()=>setIsPopupOpen(false)}>
                        <svg className="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                    <form onSubmit={handleSubmit}>
                        <div className='flex flex-col gap-2'>
                            <div>
                                <label htmlFor="isbn" className='font-bold text-black mr-2'>ISBN:</label>
                                <input 
                                type='text' 
                                placeholder='Enter ISBN here...' 
                                onChange={(e) => setBook({...book, isbn: e.target.value})}
                                />
                            </div>
                            <div>
                                <label htmlFor="title" className='font-bold text-black mr-3'>Title:</label>
                                <input 
                                type='text' 
                                placeholder='Enter Title here...' 
                                onChange={(e) => setBook({...book, title: e.target.value})}
                                />
                            </div>
                            <div>
                                <label htmlFor="price" className='font-bold text-black mr-2'>Price:</label>
                                <input 
                                type='text' 
                                placeholder='Enter Price here...' 
                                onChange={(e) => setBook({...book, price: e.target.value})}
                                />
                            </div>
                            <button className='bg-blue-500 text-white'>Save</button>
                            <button className='bg-red-500 text-white' onClick={()=> setIsPopupOpen(false)}>Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        )}
      </>
  )
}

export default NewBook;