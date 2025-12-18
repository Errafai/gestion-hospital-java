import React from 'react'

export const Alert = ({ type, message }) => {
  const handleColor = (type)=>{
    switch (type) {
      case 'success':
          return 'bg-green-200';
      case 'failed':
          return 'bg-red-100';
      default:
        return 'bg-red-50';
    }
  }
  return (
    <div className={`py-4 w-2xl mb-4 text-center text-lg font-semibold text-gray-700 rounded-lg ${handleColor(type)}`}>
      {message}
    </div>
  )
}

export default Alert
