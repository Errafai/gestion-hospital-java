import React, { useState } from 'react'
import ReserveHero from '../../components/fundamentalPages/reserve/ReserveHero'
import AppointmentForm from '../../components/appointments/AppointmentForm'
import Alert from '../../components/shared/Alert'

export default function Reserve() {
  const [appointmentSuccess, setAppointmentSuccess] = useState(false)
  const [appointmentData, setAppointmentData] = useState(null)

  const handleAppointmentSubmit = (formData) => {
    // Save the appointment data (in a real app, this would go to backend)
    console.log('Appointment booked:', formData)
    setAppointmentData(formData)
    setAppointmentSuccess(true)

    // Reset success message after 5 seconds
    setTimeout(() => setAppointmentSuccess(false), 5000)
  }

  return (
    <div>
      <ReserveHero />
      
      <div className="py-12 bg-gray-50">
        <div className="container mx-auto px-4">
          {appointmentSuccess && (
            <Alert
              type="success"
              message={`Appointment successfully booked for ${appointmentData?.patientName} on ${appointmentData?.appointmentDate} at ${appointmentData?.time}`}
              onClose={() => setAppointmentSuccess(false)}
            />
          )}
          
          <AppointmentForm onSubmit={handleAppointmentSubmit} />
        </div>
      </div>
    </div>
  )
}
