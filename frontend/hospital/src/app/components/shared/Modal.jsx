import React from 'react'

export const Modal = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white rounded-lg p-6">
        {children}
        <button onClick={onClose} className="mt-4">Close</button>
      </div>
    </div>
  )
}

export default Modal
