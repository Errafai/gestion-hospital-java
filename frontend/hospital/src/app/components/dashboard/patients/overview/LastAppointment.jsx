import React from 'react'

export const LastAppointment = () => {
  return (
    <div className="rounded-lg shadow-lg p-6 bg-linear-to-br from-red-50 to-pink-100 min-h-48 hover:shadow-xl transition-shadow">
      <div className="flex justify-between items-start mb-4">
        <h3 className="text-2xl font-bold text-red-700">Last Appointment</h3>
        <span className="bg-red-200 text-red-700 px-3 py-1 rounded-full text-xs font-semibold">Completed</span>
      </div>
      <div className="space-y-3">
        <div>
          <p className="text-xs uppercase text-gray-600 font-semibold">Date & Time</p>
          <p className="text-lg font-semibold text-gray-800">December 15, 2024 - 2:30 PM</p>
        </div>
        <div>
          <p className="text-xs uppercase text-gray-600 font-semibold">Doctor</p>
          <p className="text-gray-700">Dr. Ahmed Hassan - General Practitioner</p>
        </div>
        <div>
          <p className="text-xs uppercase text-gray-600 font-semibold">Notes</p>
          <p className="text-gray-600 text-sm leading-relaxed">Patient shows good recovery progress. Continue prescribed treatment. Next checkup recommended in 2 weeks.</p>
        </div>
      </div>
    </div>
  )
}