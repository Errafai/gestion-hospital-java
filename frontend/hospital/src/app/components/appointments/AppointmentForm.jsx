import React, { useState } from 'react'

export const AppointmentForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({})

  const handleSubmit = (e) => {
    e.preventDefault()
    onSubmit(formData)
  }

  return (
    <form onSubmit={handleSubmit}>
      {/* Form fields */}
    </form>
  )
}

export default AppointmentForm
