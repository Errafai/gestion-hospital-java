import React, { useState, useEffect } from 'react'

export const PatientDetailPage = ({ patientId }) => {
  const [patient, setPatient] = useState(null)

  useEffect(() => {
    // Fetch patient details
  }, [patientId])

  return (
    <div className="p-6">
      {/* Patient details */}
    </div>
  )
}

export default PatientDetailPage
