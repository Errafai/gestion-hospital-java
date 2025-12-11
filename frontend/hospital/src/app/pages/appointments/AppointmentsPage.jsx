import React, { useState, useEffect } from 'react'

export const AppointmentsPage = () => {
  const [appointments, setAppointments] = useState([])

  useEffect(() => {
    // Fetch appointments
  }, [])

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold">Appointments</h1>
      {/* Appointments list */}
    </div>
  )
}

export default AppointmentsPage
