import React, { useState } from 'react'

export const LoginPage = () => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault()
    // Handle login
  }

  return (
    <div className="flex items-center justify-center min-h-screen">
      <form onSubmit={handleSubmit} className="w-96 border rounded-lg p-6">
        {/* Form fields */}
      </form>
    </div>
  )
}

export default LoginPage
