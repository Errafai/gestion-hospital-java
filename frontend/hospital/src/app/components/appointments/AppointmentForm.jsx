import React, { useState, useEffect } from 'react'
import { mockDoctors, appointmentTypes, getAvailableSlots } from '../../services/mockData'
import Button from '../shared/Button'

export const AppointmentForm = ({ onSubmit, defaultValues = {} }) => {
  const [formData, setFormData] = useState({
    patientName: '',
    email: '',
    phone: '',
    doctorId: '',
    appointmentType: '',
    appointmentDate: '',
    time: '',
    notes: '',
    ...defaultValues,
  })

  const [availableSlots, setAvailableSlots] = useState([])
  const [errors, setErrors] = useState({})

  // Update available slots when doctor or date changes
  useEffect(() => {
    if (formData.doctorId && formData.appointmentDate) {
      const slots = getAvailableSlots(parseInt(formData.doctorId), formData.appointmentDate)
      setAvailableSlots(slots)
      setFormData((prev) => ({ ...prev, time: '' }))
    }
  }, [formData.doctorId, formData.appointmentDate])

  const validateForm = () => {
    const newErrors = {}
    if (!formData.patientName.trim()) newErrors.patientName = 'Patient name is required'
    if (!formData.email.trim()) newErrors.email = 'Email is required'
    else if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = 'Invalid email format'
    if (!formData.phone.trim()) newErrors.phone = 'Phone number is required'
    if (!formData.doctorId) newErrors.doctorId = 'Please select a doctor'
    if (!formData.appointmentType) newErrors.appointmentType = 'Please select appointment type'
    if (!formData.appointmentDate) newErrors.appointmentDate = 'Please select a date'
    if (!formData.time) newErrors.time = 'Please select a time'
    
    setErrors(newErrors)
    return Object.keys(newErrors).length === 0
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))
    if (errors[name]) {
      setErrors((prev) => ({ ...prev, [name]: '' }))
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    if (validateForm()) {
      onSubmit(formData)
    }
  }

  const getMinDate = () => {
    const today = new Date()
    today.setDate(today.getDate() + 1)
    return today.toISOString().split('T')[0]
  }

  return (
    <form onSubmit={handleSubmit} className="bg-white p-8 rounded-lg shadow-md max-w-2xl mx-auto">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">Book an Appointment</h2>

      {/* Patient Information */}
      <div className="mb-6">
        <h3 className="text-lg font-semibold text-gray-700 mb-4">Patient Information</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
          <div>
            <label className="block text-gray-700 font-medium mb-2">Full Name *</label>
            <input
              type="text"
              name="patientName"
              value={formData.patientName}
              onChange={handleChange}
              placeholder="Enter your full name"
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.patientName ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              }`}
            />
            {errors.patientName && <p className="text-red-500 text-sm mt-1">{errors.patientName}</p>}
          </div>

          <div>
            <label className="block text-gray-700 font-medium mb-2">Email *</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Enter your email"
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.email ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              }`}
            />
            {errors.email && <p className="text-red-500 text-sm mt-1">{errors.email}</p>}
          </div>
        </div>

        <div>
          <label className="block text-gray-700 font-medium mb-2">Phone Number *</label>
          <input
            type="tel"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            placeholder="Enter your phone number"
            className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
              errors.phone ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
            }`}
          />
          {errors.phone && <p className="text-red-500 text-sm mt-1">{errors.phone}</p>}
        </div>
      </div>

      <hr className="my-6" />

      {/* Appointment Details */}
      <div className="mb-6">
        <h3 className="text-lg font-semibold text-gray-700 mb-4">Appointment Details</h3>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
          <div>
            <label className="block text-gray-700 font-medium mb-2">Select Doctor *</label>
            <select
              name="doctorId"
              value={formData.doctorId}
              onChange={handleChange}
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.doctorId ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              }`}
            >
              <option value="">Choose a doctor</option>
              {mockDoctors.map((doctor) => (
                <option key={doctor.id} value={doctor.id}>
                  {doctor.name} - {doctor.specialty}
                </option>
              ))}
            </select>
            {errors.doctorId && <p className="text-red-500 text-sm mt-1">{errors.doctorId}</p>}
          </div>

          <div>
            <label className="block text-gray-700 font-medium mb-2">Appointment Type *</label>
            <select
              name="appointmentType"
              value={formData.appointmentType}
              onChange={handleChange}
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.appointmentType ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              }`}
            >
              <option value="">Choose appointment type</option>
              {appointmentTypes.map((type) => (
                <option key={type.id} value={type.name}>
                  {type.name} ({type.duration} min)
                </option>
              ))}
            </select>
            {errors.appointmentType && <p className="text-red-500 text-sm mt-1">{errors.appointmentType}</p>}
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
          <div>
            <label className="block text-gray-700 font-medium mb-2">Appointment Date *</label>
            <input
              type="date"
              name="appointmentDate"
              value={formData.appointmentDate}
              onChange={handleChange}
              min={getMinDate()}
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.appointmentDate ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              }`}
            />
            {errors.appointmentDate && <p className="text-red-500 text-sm mt-1">{errors.appointmentDate}</p>}
          </div>

          <div>
            <label className="block text-gray-700 font-medium mb-2">Time *</label>
            <select
              name="time"
              value={formData.time}
              onChange={handleChange}
              disabled={!formData.doctorId || !formData.appointmentDate}
              className={`w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ${
                errors.time ? 'border-red-500 focus:ring-red-500' : 'border-gray-300 focus:ring-blue-500'
              } ${!formData.doctorId || !formData.appointmentDate ? 'bg-gray-100 cursor-not-allowed' : ''}`}
            >
              <option value="">
                {!formData.doctorId || !formData.appointmentDate
                  ? 'Select doctor & date first'
                  : 'Choose available time'}
              </option>
              {availableSlots.map((slot) => (
                <option key={slot} value={slot}>
                  {slot}
                </option>
              ))}
            </select>
            {errors.time && <p className="text-red-500 text-sm mt-1">{errors.time}</p>}
            {availableSlots.length === 0 && formData.doctorId && formData.appointmentDate && (
              <p className="text-yellow-600 text-sm mt-1">No available slots for this date</p>
            )}
          </div>
        </div>

        <div>
          <label className="block text-gray-700 font-medium mb-2">Additional Notes</label>
          <textarea
            name="notes"
            value={formData.notes}
            onChange={handleChange}
            placeholder="Any additional information about your condition or concerns?"
            rows="4"
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
      </div>

      <div className="flex gap-4">
        <Button
          type="submit"
          className="flex-1 bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-lg transition"
        >
          Book Appointment
        </Button>
        <button
          type="reset"
          className="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded-lg transition"
        >
          Clear Form
        </button>
      </div>
    </form>
  )
}

export default AppointmentForm
