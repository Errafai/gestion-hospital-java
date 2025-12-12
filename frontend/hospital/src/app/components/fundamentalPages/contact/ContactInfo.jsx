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
    <div className="max-w-6xl mx-auto px-4 py-16">
      <div className="grid md:grid-cols-2 gap-12">
        {/* Contact Info */}
        <div>
          <h2 className="text-3xl font-bold text-gray-800 mb-8">Get in Touch</h2>
          <div className="space-y-6">
            {contactInfo.map((info, index) => {
              const Icon = info.icon
              return (
                <div key={index} className="flex gap-4">
                  <Icon className="w-8 h-8 text-blue-600 flex-shrink-0" />
                  <div>
                    <h3 className="text-lg font-semibold text-gray-800">{info.title}</h3>
                    <p className="text-gray-600">{info.content}</p>
                  </div>
                </div>
              )
            })}
          </div>

          <div className="mt-12">
            <h3 className="text-2xl font-bold text-gray-800 mb-4">Hours of Operation</h3>
            <div className="space-y-2 text-gray-600">
              <p><span className="font-semibold">Monday - Friday:</span> 8:00 AM - 6:00 PM</p>
              <p><span className="font-semibold">Saturday:</span> 9:00 AM - 4:00 PM</p>
              <p><span className="font-semibold">Sunday:</span> 10:00 AM - 2:00 PM</p>
              <p className="pt-4 text-red-600"><span className="font-semibold">Emergency:</span> 24/7 Available</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ContactInfo
