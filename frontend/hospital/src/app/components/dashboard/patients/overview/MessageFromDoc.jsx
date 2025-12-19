import React from 'react'

export const MessageFromDoc = ({doctorInfo}) => {
  return (
    <div className="rounded-lg shadow-lg p-6 bg-linear-to-br from-purple-50 to-blue-100 min-h-48 hover:shadow-xl transition-shadow">
      <div className="flex items-center mb-4 pb-4 border-b border-purple-200">
        <div className="w-12 h-12 bg-linear-to-br from-purple-400 to-blue-500 rounded-full flex items-center justify-center text-white font-bold mr-3">
          {doctorInfo.name.charAt(0)}
        </div>
        <div>
          <p className="text-xs uppercase text-gray-600 font-semibold">From Doctor</p>
          <p className='font-bold text-lg text-purple-700'>{doctorInfo.name}</p>
        </div>
      </div>
      <div className="space-y-2">
        <p className="text-gray-600 text-sm leading-relaxed bg-white bg-opacity-50 p-3 rounded italic border-l-4 border-purple-400">
          "{doctorInfo.message}"
        </p>
        <div className="flex justify-between items-center mt-4 pt-4 border-t border-purple-200">
          <p className="text-xs text-gray-500">Received: Today at 10:30 AM</p>
          <button className="text-sm text-purple-600 hover:text-purple-700 font-semibold">Reply →</button>
        </div>
      </div>
    </div>
  )
}

export default MessageFromDoc