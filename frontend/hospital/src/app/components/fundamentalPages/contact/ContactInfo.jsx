import React from 'react'
import { Mail, Phone, MapPin } from 'lucide-react'

export const ContactInfo = () => {
  const contactInfo = [
    {
      icon: MapPin,
      title: 'Address',
      content: '123 Hospital Street, Medical City, MC 12345'
    },
    {
      icon: Phone,
      title: 'Phone',
      content: '+1 (555) 123-4567'
    },
    {
      icon: Mail,
      title: 'Email',
      content: 'info@hospital.com'
    }
  ]

  return (
    <div className="w-full">
      <h2 className="text-3xl font-bold text-gray-800 mb-2">Get in Touch</h2>
      <p className="text-gray-600 mb-8">Reach us through any of these channels. We're available to assist you.</p>
          <div className="space-y-6">
            {contactInfo.map((info, index) => {
              const Icon = info.icon
              return (
                <div key={index} className="flex gap-4 p-4 rounded-lg hover:bg-blue-50 transition">
                  <Icon className="w-8 h-8 text-blue-600 flex-shrink-0 mt-1" />
                  <div>
                    <h3 className="text-lg font-semibold text-gray-800">{info.title}</h3>
                    <p className="text-gray-600 mt-1">{info.content}</p>
                  </div>
                </div>
              )
            })}
          </div>

          <div className="mt-12">
            <h3 className="text-2xl font-bold text-gray-800 mb-6">Hours of Operation</h3>
            <div className="space-y-3 bg-gradient-to-br from-blue-50 to-blue-100 p-6 rounded-lg border border-blue-200">
              <div className="flex justify-between items-center pb-3 border-b border-blue-200">
                <span className="font-semibold text-gray-800">Monday - Friday:</span>
                <span className="text-gray-700">8:00 AM - 6:00 PM</span>
              </div>
              <div className="flex justify-between items-center pb-3 border-b border-blue-200">
                <span className="font-semibold text-gray-800">Saturday:</span>
                <span className="text-gray-700">9:00 AM - 4:00 PM</span>
              </div>
              <div className="flex justify-between items-center pb-3 border-b border-blue-200">
                <span className="font-semibold text-gray-800">Sunday:</span>
                <span className="text-gray-700">10:00 AM - 2:00 PM</span>
              </div>
              <div className="flex justify-between items-center pt-3">
                <span className="font-semibold text-red-600">Emergency Service:</span>
                <span className="text-red-600 font-semibold">24/7 Available</span>
              </div>
            </div>
          </div>
        </div>
      )
    }

export default ContactInfo
