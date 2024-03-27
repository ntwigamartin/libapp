import React from 'react'

interface BookEditProps {
    isOpen: boolean;
    onClose: any;
    children: any;
}
const BookEdit: React.FC<BookEditProps> = ({ isOpen, onClose, children }) => {
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
                    {children}
                </div>
            </div>
        )}
      </>
    );
  };
  
  export default BookEdit;