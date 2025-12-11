import React, { useState } from 'react'

export const RegisterPage = () => {
  const [formData, setFormData] = useState({})

  const handleSubmit = (e) => {
    e.preventDefault()
    // Handle registration
  }

  return (
    <div className="flex items-center justify-center min-h-screen">
      <form onSubmit={handleSubmit} className="w-96 border rounded-lg p-6">
        {/* Form fields */}
      </form>
    </div>
  )
}

export default RegisterPage
