import React, { useState } from 'react'

export const ConsultationForm = ({ onSubmit }) => {
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

export default ConsultationForm
