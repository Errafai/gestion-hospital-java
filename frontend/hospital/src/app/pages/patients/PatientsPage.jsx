import React, { useState, useEffect } from 'react'

export const PatientsPage = () => {
  const [patients, setPatients] = useState([])

  useEffect(() => {
    // Fetch patients
  }, [])

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold">Patients</h1>
      {/* Patients list */}
    </div>
  )
}

export default PatientsPage
