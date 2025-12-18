import React, { useState, useEffect } from 'react'
import AppointmentsList from '../../components/appointments/AppointmentsList'
import { mockAppointments, mockDoctors } from '../../services/mockData'
import Button from '../../components/shared/Button'
import { useNavigate } from 'react-router-dom'

export const AppointmentsPage = () => {
  const [appointments, setAppointments] = useState([])
  const [filteredAppointments, setFilteredAppointments] = useState([])
  const [filterStatus, setFilterStatus] = useState('all')
  const navigate = useNavigate()

  useEffect(() => {
    // Simulate fetching appointments with doctor names
    const appointmentsWithDoctors = mockAppointments.map((apt) => ({
      ...apt,
      doctorName: mockDoctors.find((d) => d.id === apt.doctorId)?.name || 'Unknown',
    }))

    setAppointments(appointmentsWithDoctors)
    setFilteredAppointments(appointmentsWithDoctors)
  }, [])

  const handleFilterChange = (status) => {
    setFilterStatus(status)
    if (status === 'all') {
      setFilteredAppointments(appointments)
    } else {
      setFilteredAppointments(
        appointments.filter((apt) => apt.status.toLowerCase() === status.toLowerCase())
      )
    }
  }

  const handleBookNew = () => {
    navigate('/reserve')
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="container mx-auto px-4">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 mb-2">My Appointments</h1>
          <p className="text-gray-600">Manage and track all your medical appointments</p>
        </div>

        {/* Action Buttons */}
        <div className="mb-8 flex flex-col sm:flex-row gap-4 justify-between items-start sm:items-center">
          <div className="flex gap-2">
            {['all', 'confirmed', 'pending', 'completed'].map((status) => (
              <button
                key={status}
                onClick={() => handleFilterChange(status)}
                className={`px-4 py-2 rounded-lg font-medium transition ${
                  filterStatus === status
                    ? 'bg-blue-600 text-white'
                    : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'
                }`}
              >
                {status.charAt(0).toUpperCase() + status.slice(1)}
              </button>
            ))}
          </div>
          <Button
            onClick={handleBookNew}
            className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-6 rounded-lg transition"
          >
            Book New Appointment
          </Button>
        </div>

        {/* Statistics */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-8">
          <div className="bg-white rounded-lg shadow-md p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Total</p>
                <p className="text-3xl font-bold text-gray-900">{appointments.length}</p>
              </div>
              <div className="bg-blue-100 rounded-full p-3">
                <svg className="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7V3m8 4V3m-9 8h18M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                </svg>
              </div>
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-md p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Confirmed</p>
                <p className="text-3xl font-bold text-green-600">
                  {appointments.filter((a) => a.status === 'confirmed').length}
                </p>
              </div>
              <div className="bg-green-100 rounded-full p-3">
                <svg className="h-6 w-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-md p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Pending</p>
                <p className="text-3xl font-bold text-yellow-600">
                  {appointments.filter((a) => a.status === 'pending').length}
                </p>
              </div>
              <div className="bg-yellow-100 rounded-full p-3">
                <svg className="h-6 w-6 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-md p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Completed</p>
                <p className="text-3xl font-bold text-blue-600">
                  {appointments.filter((a) => a.status === 'completed').length}
                </p>
              </div>
              <div className="bg-blue-100 rounded-full p-3">
                <svg className="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                </svg>
              </div>
            </div>
          </div>
        </div>

        {/* Appointments List */}
        <AppointmentsList appointments={filteredAppointments} />
      </div>
    </div>
  )
}

export default AppointmentsPage
